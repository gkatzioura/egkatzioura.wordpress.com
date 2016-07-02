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
			
				console.log(result)
				
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

module.exports = {
	queryLogins:queryLogins,
	insertLogin:insertLogin
}