use springsecurity
db.users.insert({"name":"John","surname":"doe","email":"john@doe.com","password":"cleartextpass","authorities":["user","admin"]})
db.users.insert({"name":"John","surname":"doe","email":"john2@doe.com","password":"$2a$12$qB.L7buUPi2RJHZ9fYceQ.XdyEFxjAmiekH9AEkJvh1gLFPGEf9mW","authorities":["user","admin"]})
