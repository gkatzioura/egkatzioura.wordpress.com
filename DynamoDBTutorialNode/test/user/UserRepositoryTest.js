/**
 * http://usejsdoc.org/
 */

var UserRepository = require('../../user/UserRepository'),
	TableCreator = require('../../TableCreator'),
	assert = require('assert');

describe('User Repository Test',function(){
    
	before(function(done) {
		TableCreator.deleteUsers(function(err,result) {
			
			if(err) {
				test.done(err);
				return;
			}
			
			
			TableCreator.createUsers(function(err,result) {
				
				if(err) {
					test.done(err);
					return;
				}
				
				UserRepository.insertUser("john@doe.com", "John Doe", new Date(), function(err,result) {
					
					if(err) {
						done(err);
					} else {
						done();
					}
				});

			});			
		});
	});
	
	it('Query user',function(done) {
		UserRepository.getUser("john@doe.com",function(err,result) {
			if(err) {
				done(err);
			} else {
				console.log(result);
				done();
			}
		});
	});
	
})

