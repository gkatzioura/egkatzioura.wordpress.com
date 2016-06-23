/**
 * http://usejsdoc.org/
 */

var TableCreator = require("./TableCreator");

exports.testCreateUsers = function(test) {
	
	TableCreator.createUsers(function(err,result) {
		if(err) {
			console.log("Problem creating table");
			test.done(err);
		} else {
			console.log("Created successfully");
			test.done();
		}
	});
};

exports.testCreateLogins = function(test) {
	
	TableCreator.createLogins(function(err,result) {
		if(err) {
			console.log("Problem creating table");
			test.done(err);
		} else {
			console.log("Created successfully");
			test.done();
		}
	});
};

exports.testCreateSupervisors = function(test) {
	
	TableCreator.createSupervisors(function(err,result) {
		if(err) {
			console.log("Problem creating table");
			test.done(err);
		} else {
			console.log("Created successfully");
			test.done();
		}
	});
};

exports.testCreateCompanies = function(test) {
	
	TableCreator.createCompanies(function(err,result) {
		if(err) {
			console.log("Problem creating table");
			test.done(err);
		} else {
			console.log("Created successfully");
			test.done();
		}
	});
};
