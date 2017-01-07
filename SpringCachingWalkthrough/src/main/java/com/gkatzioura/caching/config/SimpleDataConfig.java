package com.gkatzioura.caching.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkatzioura.caching.model.UserPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by gkatzioura on 1/5/17.
 */
@Configuration
@Profile("simple-cache")
public class SimpleDataConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("classpath:/users.json")
    private Resource usersJsonResource;

    @Bean
    public List<UserPayload> payloadUsers() throws IOException {

        try(InputStream inputStream = usersJsonResource.getInputStream()) {

            UserPayload[] payloadUsers = objectMapper.readValue(inputStream,UserPayload[].class);
            return Collections.unmodifiableList(Arrays.asList(payloadUsers));
        }
    }
}
