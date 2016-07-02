/**
 * http://usejsdoc.org/
 */
var AWS = require("aws-sdk");

AWS.config.update({
	  region: "us-west-2",
	  endpoint: "http://localhost:8000"
});

var dynamodb = new AWS.DynamoDB();

var insertSupervisor = function(name,company,factory,callback) {
	
	var params = {
			TableName:"Supervisors",
		    Item:{
		    	name : { S:name},
		        company: { S:company},
		        factory: {S:factory}
		    }
		};
	
	dynamodb.putItem(params,callback);
};

var getSupervisor = function(company,factory,callback) {

	var docClient = new AWS.DynamoDB.DocumentClient();
	
	var params = {
		    TableName: "Supervisors",
		    IndexName: "FactoryIndex",
		    KeyConditionExpression:"#company = :companyValue and #factory = :factoryValue",
		    ExpressionAttributeNames: {
		    	"#company":"company",
		    	"#factory":"factory"
		    },
		    ExpressionAttributeValues: {
		    	":companyValue": company,
		    	":factoryValue": factory
		    }
		};

	docClient.query(params,callback);
};


module.exports = {
	insertSupervisor:insertSupervisor,
	getSupervisor: getSupervisor
}