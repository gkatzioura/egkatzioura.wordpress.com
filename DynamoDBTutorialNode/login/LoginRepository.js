/**
 * http://usejsdoc.org/
 */

var AWS = require("aws-sdk");

AWS.config.update({
	  region: "us-west-2",
	  endpoint: "http://localhost:8000"
});

var dynamodb = new AWS.DynamoDB();

var insertLogin = function(email,date,callback) {
	
	var params = {
			TableName:"Logins",
		    Item:{
		    	email : { S:email},
		        timestamp: {N:String(date.getTime())}
		    }
		};
	
	dynamodb.putItem(params,callback);
}

var countLogins = function(email,callback) {

	var docClient = new AWS.DynamoDB.DocumentClient();

	var params = {
	    TableName:"Logins",
	    KeyConditionExpression:"#email = :emailValue",
	    ExpressionAttributeNames: {
	    	"#email":"email"
	    },
	    ExpressionAttributeValues: {
	    	":emailValue":email
	    },
	    Select:'COUNT'
	};
	
	docClient.query(params,callback);
}

var fetchLoginsDesc = function(email,callback) {

	var docClient = new AWS.DynamoDB.DocumentClient();

	var params = {
	    TableName:"Logins",
	    KeyConditionExpression:"#email = :emailValue",
	    ExpressionAttributeNames: {
	    	"#email":"email"
	    },
	    ExpressionAttributeValues: {
	    	":emailValue":email
	    },
	    ScanIndexForward: false
	};
	
	docClient.query(params,callback);
}


var queryLogins = function(email,from,to,callback) {

	var docClient = new AWS.DynamoDB.DocumentClient();
	
	var params = {
	    TableName:"Logins",
	    KeyConditionExpression:"#email = :emailValue and #timestamp BETWEEN :from AND :to",
	    ExpressionAttributeNames: {
	    	"#email":"email",
	    	"#timestamp":"timestamp"
	    },
	    ExpressionAttributeValues: {
	    	":emailValue":email,
	    	":from": from.getTime(),
	    	":to":to.getTime()
	    }			
	};
	
	var items = []
	
	var queryExecute = function(callback) {
	
		docClient.query(params,function(err,result) {

			if(err) {
				callback(err);
			} else {
			
				items = items.concat(result.Items);
			
				if(result.LastEvaluatedKey) {

					params.ExclusiveStartKey = result.LastEvaluatedKey;
					queryExecute(callback);				
				} else {
					callback(err,items);
				}	
			}
		});
	}
	
	queryExecute(callback);
};

var scanLogins = function(date,callback) {

	var docClient = new AWS.DynamoDB.DocumentClient();

	var params = {
		TableName:"Logins",
		ProjectionExpression: "email",
	    FilterExpression: "#timestamp < :from",
	    ExpressionAttributeNames: {
	        "#timestamp": "timestamp",
	    },
	    ExpressionAttributeValues: {
	         ":from": date.getTime()
	    }
	};

	var items = []
	
	var scanExecute = function(callback) {
	
		docClient.scan(params,function(err,result) {

			if(err) {
				callback(err);
			} else {
				
				items = items.concat(result.Items);

				if(result.LastEvaluatedKey) {

					params.ExclusiveStartKey = result.LastEvaluatedKey;
					scanExecute(callback);				
				} else {
					callback(err,items);
				}	
			}
		});
	}
	
	scanExecute(callback);
};


module.exports = {
	queryLogins:queryLogins,
	insertLogin:insertLogin,
	countLogins:countLogins,
	fetchLoginsDesc: fetchLoginsDesc,
	scanLogins: scanLogins
}