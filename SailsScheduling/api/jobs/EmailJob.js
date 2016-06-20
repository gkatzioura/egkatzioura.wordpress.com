module.exports = function(agenda) {
  var job = {

    frequency: 'every 5 minutes',
    run: function(job, done) {
      EmailService.send("Test email",function (err,result) {

        if(err) {
          sails.log.error("Job was not executed properly");
          done(err);
        } else {
          sails.log.info("Agenda job was executed");
          done();
        }
      });
    },
  };
  return job;
}
