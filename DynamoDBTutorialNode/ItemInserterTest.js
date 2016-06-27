/**
 * 
 */

var ItemInserter = require("./ItemInserter");

exports.testInsertUsers = function(test) {
	
	ItemInserter.insertUsers(function(err,result) {
		if(err) {
			console.log("Problem inserting to table");
			test.done(err);
		} else {
			console.log("Inserted successfully");
			test.done();
		}
	});
};

exports.testInsetBatchLogins = function(test) {
	
	ItemInserter.insetBatchLogins(function(err,result) {
		if(err) {
			console.log("Problem inserting to table");
			test.done(err);
		} else {
			console.log("Inserted successfully");
			test.done();
		}
	});
};

exports.testInsertSupervisor = function(test) {
	
	ItemInserter.insertSupervisor(function(err,result) {
		if(err) {
			console.log("Problem inserting to table");
			test.done(err);
		} else {
			console.log("Inserted successfully");
			test.done();
		}
	});
};
