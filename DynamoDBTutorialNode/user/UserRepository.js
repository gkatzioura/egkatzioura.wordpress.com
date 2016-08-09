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

var updateName = function(email,fullName,callback) {
	
	var docClient = new AWS.DynamoDB.DocumentClient();
	
	var params = {
			TableName:"Users",
			Key: {
				email : email
			},
			UpdateExpression: "set fullname = :fullname",
		    ExpressionAttributeValues:{
		        ":fullname":fullName
		    },
		    ReturnValues:"UPDATED_NEW"
		};
	
	docClient.update(params,callback);
}

var updateConditionally = function(email,fullName,prefix,callback) {
	
	var docClient = new AWS.DynamoDB.DocumentClient();
	
	var params = {
			TableName:"Users",
			Key: {
				email : email
			},
			UpdateExpression: "set fullname = :fullname",
			ConditionExpression: "begins_with(fullname,:prefix)",
			ExpressionAttributeValues:{
		        ":fullname":fullName,
		        ":prefix":prefix
		    },
		    ReturnValues:"UPDATED_NEW"
		};
	
	docClient.update(params,callback);
}

var addUpdateCounter = function(email,callback) {
	
	var docClient = new AWS.DynamoDB.DocumentClient();
	
	var params = {
			TableName:"Users",
			Key: {
				email : email
			},
			UpdateExpression: "set #counter = :counter",
			ExpressionAttributeNames:{
		        "#counter":"counter"
		    },
			ExpressionAttributeValues:{
		        ":counter":0
		    },
			ReturnValues:"UPDATED_NEW"
		};
	
	docClient.update(params,callback);
}

var updateAndIncreaseCounter = function(email,fullName,callback) {

	var docClient = new AWS.DynamoDB.DocumentClient();
	
	var params = {
			TableName:"Users",
			Key: {
				email : email
			},
			UpdateExpression: "set fullname = :fullname ADD #counter :incva",
			ExpressionAttributeNames:{
		        "#counter":"counter"
		    },
			ExpressionAttributeValues:{
		        ":fullname":fullName,
		        ":incva":1
		    },
		    ReturnValues:"UPDATED_NEW"
		};
	
	docClient.update(params,callback);
}


var getRegisterDate = function(email,callback) {
	
	var docClient = new AWS.DynamoDB.DocumentClient();
	
	var params = {
		    TableName: "Users",
		    KeyConditionExpression: "#email = :email",
		    ExpressionAttributeNames:{
		        "#email": "email"
		    },
		    ExpressionAttributeValues: {
		        ":email":email
		    },
		    ProjectionExpression: 'registerDate'
		};
	
	docClient.query(params,callback);
}

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
	getUser: getUser,
	getRegisterDate: getRegisterDate,
	updateName: updateName,
	updateConditionally: updateConditionally,
	addUpdateCounter: addUpdateCounter,
	updateAndIncreaseCounter: updateAndIncreaseCounter
}