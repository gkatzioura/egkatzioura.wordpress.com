/**
 * Created by gkatzioura on 6/20/16.
 */

var send = function (text,callback) {

  sails.log.info("Should send text: "+text)
  callback();
};

module.exports =  {
  send: send
}
