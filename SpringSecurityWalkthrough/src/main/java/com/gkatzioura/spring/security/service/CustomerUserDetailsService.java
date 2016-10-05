package com.gkatzioura.spring.security.service;

import com.gkatzioura.spring.security.model.MongoUserDetails;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gkatzioura on 9/27/16.
 */
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private MongoClient mongoClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        MongoDatabase database = mongoClient.getDatabase("springsecurity");
        MongoCollection<Document> collection = database.getCollection("users");

        Document document = collection.find(Filters.eq("email",email)).first();

        if(document!=null) {

            String name = document.getString("name");
            String surname = document.getString("surname");
            String password = document.getString("password");
            List<String> authorities = (List<String>) document.get("authorities");

            MongoUserDetails mongoUserDetails = new MongoUserDetails(email,password,authorities.toArray(new String[authorities.size()]));

            return mongoUserDetails;
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

}
