use springsecurity
db.users.insert({"name":"John","surname":"doe","email":"john@doe.com","password":"cleartextpass","authorities":["user","admin"]})