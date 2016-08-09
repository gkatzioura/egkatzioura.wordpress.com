/**
 * http://usejsdoc.org/
 */

var UserRepository = require('../../user/UserRepository'),
	TableCreator = require('../../TableCreator'),
	async = require('async'),
	assert = require('assert');

describe('User Repository Test',function(){
    
	beforeEach(function(done) {
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
	
	it('Update user',function(done) {
		
		UserRepository.updateName("john@doe.com","Different Name",function(err,result) {
			if(err) {
				done(err);
			} else {
				UserRepository.getUser("john@doe.com",function(err,result) {
					if(err) {
						done(err);
					} else {
						assert.equal(result.Items[0].fullname,"Different Name");
						done();
					}
				});
			}	
		});
	});
	
	it('Update user conditionally',function(done) {
		
		UserRepository.updateConditionally("john@doe.com","Different Name","John",function(err,result) {
			if(err) {
				done(err);
			} else {
				UserRepository.getUser("john@doe.com",function(err,result) {
					if(err) {
						done(err);
					} else {
						assert.equal(result.Items[0].fullname,"Different Name");
						done();
					}
				});
			}	
		});
	});
	
	it('Add and increse counter',function(done){
		
		async.waterfall(
				[
				 function(callback) {
					UserRepository.addUpdateCounter("john@doe.com",function(err,result){
						if(err) {
							callback(err);
						} else {
							callback();
						}
					});
				 },
				 function(callback) {
					UserRepository.updateAndIncreaseCounter("john@doe.com","New Name",function(err,result){
						if(err) {
							callback(err);
						} else {
							callback();
						}
					});
				 },
				 function(callback) {
				    UserRepository.getUser("john@doe.com",callback);
				 }
						     
					 ],
		function(err,result){
			if(err) {
				done(err);
			} else {
				assert.equal(result.Items[0].fullname,"New Name");
				assert.equal(result.Items[0].counter,"1");
				
				done();
			}
		});
	});
	
	it("Get Register Date",function(done) {
		UserRepository.getRegisterDate("john@doe.com",function(err,result) {
			if(err) {
				done(err);
			} else {
				console.log(result);
				done();
			}
		});
	});
	
})

