/**
 * 
 */
var AWS = require("aws-sdk");

AWS.config.update({
	  region: "us-west-2",
	  endpoint: "http://localhost:8000"
});

var insertUsers = function(callback) {
	
	var dynamodb = new AWS.DynamoDB();
	var params = {
			TableName:"Users",
		    Item:{
		    	email : { S:"jon@doe.com"},
		        fullname: { S:"Jon Doe"}
		    }
		};
	
	dynamodb.putItem(params,callback);
};

var insetBatchLogins = function(callback) {
	
	var dynamodb = new AWS.DynamoDB();
	var batchRequest = {
			RequestItems: {
				"Logins": [
				           {
				        	   PutRequest: { 
				        		   Item: {
				        			   "email": { S: "jon@doe.com" },
				        			   "timestamp": { N: "1467041009976" }
				        			   }
				           }},
				           {
				        	   PutRequest: { 
				        		   Item: {
				        			   "email": { S: "jon@doe.com" },
				        			   "timestamp": { N: "1467041019976" }
				        			   }
				           }}]
		    }
		};

	dynamodb.batchWriteItem(batchRequest,callback);
};

var insertSupervisor = function(callback) {
	
	var dynamodb = new AWS.DynamoDB();
	
	var params = {
			TableName:"Supervisors",
		    Item:{
		    	name: { S:"Random SuperVisor"},
		    	company: { S:"Random Company"},
		    	factory: { S:"Jon Doe"}
		    }
		};
	
	dynamodb.putItem(params,callback);
}

module.exports = {
	insertUsers: insertUsers,
	insetBatchLogins: insetBatchLogins,
	insertSupervisor: insertSupervisor
}
