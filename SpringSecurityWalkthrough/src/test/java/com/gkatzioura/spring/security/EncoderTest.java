package com.gkatzioura.spring.security;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by gkatzioura on 10/5/16.
 */
public class EncoderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncoderTest.class);

    @Test
    public void md5Encoder() {

        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String encoded = md5PasswordEncoder.encodePassword("test_pass",null);

        LOGGER.info("Md5 encoded "+encoded);
    }

    @Test
    public void bcryptEncoder() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encoded = bCryptPasswordEncoder.encode("test_pass");

        LOGGER.info("Becrypt encoded "+encoded);
    }

    @Test
    public void standardEncoder() {

        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();
        String encoded = standardPasswordEncoder.encode("test_pass");

        LOGGER.info("Standard encoded "+encoded);
    }

}
