package com.gkatzioura.bootdatabaseinitialization.controller;

import com.gkatzioura.bootdatabaseinitialization.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by gkatzioura on 29/4/2016.
 */
@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/names")
    public List<String> userNames() {

        return userRepository.getNames();
    }

}
