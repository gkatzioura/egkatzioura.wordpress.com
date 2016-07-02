/**
 * http://usejsdoc.org/
 */

var LoginRepository = require('../../login/LoginRepository'),
	TableCreator = require('../../TableCreator'),
	assert = require('assert');

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
	
})

