/**
 * 
 */

var AWS = require("aws-sdk");

AWS.config.update({
	  region: "us-west-2",
	  endpoint: "http://localhost:8000"
});

var createUsers = function(callback) {

	var dynamodb = new AWS.DynamoDB();

	var params = {
	    TableName : "Users",
	    KeySchema: [       
	        { AttributeName: "email", KeyType: "HASH"}
	    ],
	    AttributeDefinitions: [       
	        { AttributeName: "email", AttributeType: "S" }
	    ],
	    ProvisionedThroughput: {       
	        ReadCapacityUnits: 5, 
	        WriteCapacityUnits: 5
		   }
		};

	dynamodb.createTable(params, callback);	
};

var createLogins = function(callback) {

	var dynamodb = new AWS.DynamoDB();

	var params = {
	    TableName : "Logins",
	    KeySchema: [       
	        { AttributeName: "email", KeyType: "HASH"},
	        { AttributeName: "timestamp", KeyType: "RANGE"}
		],
	    AttributeDefinitions: [       
	        { AttributeName: "email", AttributeType: "S" },
	        { AttributeName: "timestamp", AttributeType: "N" }
	    ],
	    ProvisionedThroughput: {       
	        ReadCapacityUnits: 5, 
	        WriteCapacityUnits: 5
		   }
		};

	dynamodb.createTable(params, callback);	
};

var createLogins = function(callback) {

	var dynamodb = new AWS.DynamoDB();

	var params = {
	    TableName : "Logins",
	    KeySchema: [       
	        { AttributeName: "email", KeyType: "HASH"},
	        { AttributeName: "timestamp", KeyType: "RANGE"}
		],
	    AttributeDefinitions: [       
	        { AttributeName: "email", AttributeType: "S" },
	        { AttributeName: "timestamp", AttributeType: "N" }
	    ],
	    ProvisionedThroughput: {       
	        ReadCapacityUnits: 5, 
	        WriteCapacityUnits: 5
		   }
		};

	dynamodb.createTable(params, callback);	
};

var createSupervisors = function(callback) {

	var dynamodb = new AWS.DynamoDB();

	var params = {
	    TableName : "Supervisors",
	    KeySchema: [       
	        { AttributeName: "name", KeyType: "HASH"}
		],
	    AttributeDefinitions: [       
	        { AttributeName: "name", AttributeType: "S" },
	        { AttributeName: "company", AttributeType: "S" },
	        { AttributeName: "factory", AttributeType: "S" }    
	    ],
	    ProvisionedThroughput: {       
	        ReadCapacityUnits: 5, 
	        WriteCapacityUnits: 5
		   },
		GlobalSecondaryIndexes: [{
				IndexName: "FactoryIndex",
				KeySchema: [
				    {
				    	AttributeName: "company",
				    	KeyType: "HASH"
				    },
					{
						AttributeName: "factory",
						KeyType: "RANGE"
					}
				],
				Projection: {
					ProjectionType: "ALL"
				},
				ProvisionedThroughput: {
					ReadCapacityUnits: 1,
					WriteCapacityUnits: 1
				}
		    }]
	};

	dynamodb.createTable(params, callback);	
};


var createCompanies = function(callback) {

	var dynamodb = new AWS.DynamoDB();

	var params = {
	    TableName : "Companies",
	    KeySchema: [       
	        { AttributeName: "name", KeyType: "HASH"},
	        { AttributeName: "subsidiary", KeyType: "RANGE"}
		],
	    AttributeDefinitions: [       
	        { AttributeName: "name", AttributeType: "S" },
	        { AttributeName: "subsidiary", AttributeType: "S" },
	        { AttributeName: "ceo", AttributeType: "S" }    
	    ],
	    ProvisionedThroughput: {       
	        ReadCapacityUnits: 5, 
	        WriteCapacityUnits: 5
		   },
		LocalSecondaryIndexes: [{
				IndexName: "CeoIndex",
				KeySchema: [
				    {
				    	AttributeName: "name",
				    	KeyType: "HASH"
				    },
					{
						AttributeName: "ceo",
						KeyType: "RANGE"
					}
				],
				Projection: {
					ProjectionType: "ALL"
				}
		    }]
	};

	dynamodb.createTable(params, callback);	
};


module.exports = {
	createUsers: createUsers,
	createLogins: createLogins,
	createSupervisors: createSupervisors,
	createCompanies: createCompanies
}
