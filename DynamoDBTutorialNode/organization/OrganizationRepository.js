/**
 * http://usejsdoc.org/
 */
var AWS = require("aws-sdk");

AWS.config.update({
	  region: "us-west-2",
	  endpoint: "http://localhost:8000"
});

var dynamodb = new AWS.DynamoDB();

var getMultipleInformation = function(email,name,callback) {
	
	var params = {
			"RequestItems" : {
			    "Users": {
			      "Keys" : [
			        {"email" : { "S" : email }}
			      ]
			    },
			    "Supervisors": {
				   "Keys" : [
					{"name" : { "S" : name }}
				  ]
			    }
			  }
			};
	
	dynamodb.batchGetItem(params,callback);
};

module.exports = {
	getMultipleInformation:getMultipleInformation
}