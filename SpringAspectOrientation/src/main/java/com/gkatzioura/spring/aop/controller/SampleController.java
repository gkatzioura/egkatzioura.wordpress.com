package com.gkatzioura.spring.aop.controller;

import com.gkatzioura.spring.aop.model.Sample;
import com.gkatzioura.spring.aop.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gkatzioura on 5/28/16.
 */
@RestController
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping("/sample")
    public Sample sample(String sampleName) {
        return sampleService.createSample(sampleName);
    }

}
