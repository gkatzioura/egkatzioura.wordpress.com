package com.gkatzioura.caching.repository;

import com.gkatzioura.caching.model.UserPayload;

import java.util.List;

/**
 * Created by gkatzioura on 1/6/17.
 */
public interface UserRepository {

    List<UserPayload> fetchAllUsers();

    UserPayload firstUser();

    UserPayload userByFirstNameAndLastName(String firstName,String lastName);

}
