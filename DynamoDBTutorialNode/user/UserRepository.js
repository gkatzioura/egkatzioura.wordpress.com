/**
 * http://usejsdoc.org/
 */
var AWS = require("aws-sdk");

AWS.config.update({
	  region: "us-west-2",
	  endpoint: "http://localhost:8000"
});

var dynamodb = new AWS.DynamoDB();

var insertUser = function(email,fullName,registerDate,callback) {
	
	var params = {
			TableName:"Users",
		    Item:{
		    	email : { S:email},
		        fullname: { S:fullName},
		        registerDate: {N:String(registerDate.getTime())}
		    }
		};
	
	dynamodb.putItem(params,callback);
};

var getUser = function(email,callback) {
	
	var docClient = new AWS.DynamoDB.DocumentClient();
	
	var params = {
		    TableName: "Users",
		    KeyConditionExpression: "#email = :email",
		    ExpressionAttributeNames:{
		        "#email": "email"
		    },
		    ExpressionAttributeValues: {
		        ":email":email
		    }
		};
	
	docClient.query(params,callback);
};

module.exports = {
	insertUser:insertUser,
	getUser: getUser
}