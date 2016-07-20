/**
 * http://usejsdoc.org/
 */

var OrganizationRepository = require('../../organization/OrganizationRepository'),
	SupervisorRepository = require('../../supervisor/SupervisorRepository'),
	UserRepository = require('../../user/UserRepository'),
	TableCreator = require('../../TableCreator'),
	assert = require('assert'),
	async = require('async');

describe('Organization Repository Test',function(){
    
	before(function(done) {
		
		async.waterfall([
				 function(callback) {
					 TableCreator.deleteSupervisors(function(err,result) {callback();});
				 },
				 function(callback) {
					 TableCreator.deleteUsers(function(err,result) {callback();});
				 },
				 function(callback) {
					 TableCreator.createSupervisors(function(err,result) {callback();});
				 },
				 function(callback) {
					 TableCreator.createUsers(function(err,result) {callback();});
				 },				 
				 function(callback) {
					 SupervisorRepository.insertSupervisor("John Doe","Sun","Athens",function(err,result) {callback();});
		         },
		         function(callback) {
		        	 UserRepository.insertUser("john@doe.com", "John Doe", new Date(), function(err,result) {callback();});
		         }
		         ],
		    function(err,result) {
				done(err,result);
			});
	});
	
	it('Batch get supervisor iformation',function(done) {
		OrganizationRepository.getMultipleInformation("john@doe.com","John Doe",function(err,result) {
			if(err) {
				done(err);
			} else {
				console.log(result);
				done();
			}
		});
	});
	
})
