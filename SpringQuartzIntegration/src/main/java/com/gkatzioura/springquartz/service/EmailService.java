package com.gkatzioura.springquartz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by gkatzioura on 6/7/16.
 */
@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    public void sendSpam() {

        LOGGER.info("Should send emails");
    }

}
