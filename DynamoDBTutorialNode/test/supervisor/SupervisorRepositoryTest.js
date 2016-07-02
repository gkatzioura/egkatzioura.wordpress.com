/**
 * http://usejsdoc.org/
 */

var SupervisorRepository = require('../../supervisor/SupervisorRepository'),
	TableCreator = require('../../TableCreator'),
	assert = require('assert');

describe('Supervisor Repository Test',function(){
    
	before(function(done) {
		TableCreator.deleteSupervisors(function(err,result) {
			
			if(err) {
				test.done(err);
				return;
			}
			
			
			TableCreator.createSupervisors(function(err,result) {
				
				if(err) {
					test.done(err);
					return;
				}
				
				SupervisorRepository.insertSupervisor("john@doe.com", "Sun", "Athens", function(err,result) {
					
					if(err) {
						done(err);
					} else {
						done();
					}
				});

			});			
		});
	});
	
	it('Query supervisor',function(done) {
		SupervisorRepository.getSupervisor("Sun", "Athens",function(err,result) {
			if(err) {
				done(err);
			} else {
				console.log(result);
				done();
			}
		});
	});
	
})

