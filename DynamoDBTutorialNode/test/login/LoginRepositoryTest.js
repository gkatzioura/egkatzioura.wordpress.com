/**
 * http://usejsdoc.org/
 */

var LoginRepository = require('../../login/LoginRepository'),
	TableCreator = require('../../TableCreator'),
	assert = require('assert'),
	async = require('async');

describe('LoginRepository Repository Test',function(){
    
	before(function(done) {
		TableCreator.deleteLogins(function(err,result) {
			
			if(err) {
				test.done(err);
				return;
			}
			
			TableCreator.createLogins(function(err,result) {
				
				if(err) {
					test.done(err);
					return;
				}
				
				LoginRepository.insertLogin("john@doe.com",new Date(), function(err,result) {
					
					if(err) {
						done(err);
					} else {
						done();
					}
				});

			});			
		});
	});
	
	it('Query logins',function(done) {
	
		var from = new Date();
		from.setDate(from.getDate()-1);
		var to = new Date(); 	
		to.setDate(from.getDate()+1);
		
		LoginRepository.queryLogins("john@doe.com",from ,to,function(err,result) {
			if(err) {
				done(err);
			} else {
				console.log(result);
				done();
			}
		});
	});

	it('Count logins',function(done) {
	
		LoginRepository.countLogins("john@doe.com",function(err,result) {
			if(err) {
				done(err);
			} else {
				assert.equal(result.Count, 1);
				done();
			}
		});
	});
	
	it('Fetch Logins Descending',function(done) {

		var dateOne = new Date();
		dateOne.setDate(dateOne.getDate()-1);
		var dateTwo = new Date(); 	
		dateTwo.setDate(dateTwo.getDate()+1);
		
		async.map([dateOne,dateTwo],
				function(date,callback) {
					LoginRepository.insertLogin("john@doe.com", date, callback);
				},
				function(err,result){
					LoginRepository.fetchLoginsDesc("john@doe.com", function(err,result) {
						assert.equal(result.Items[2].timestamp,dateOne.getTime());
						assert.equal(result.Items[0].timestamp,dateTwo.getTime());
						done(err,result);
					});
				});
	});
	
	it('Scan Logins',function(done) {

		var dateOne = new Date();
		dateOne.setDate(dateOne.getDate()-1);
		var dateTwo = new Date(); 	
		dateTwo.setDate(dateTwo.getDate()+1);
		
		async.map([dateOne,dateTwo],
				function(date,callback) {
					LoginRepository.insertLogin("john@doe.com", date, callback);
				},
				function(err,result){
					LoginRepository.scanLogins(dateOne, function(err,result) {
						assert.equal(result.length,1);
						done(err,result);
					});
				});
	});
})

