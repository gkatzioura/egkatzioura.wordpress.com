package com.gkatzioura.spring.aop.service;

import com.gkatzioura.spring.aop.model.Sample;
import org.springframework.stereotype.Service;

/**
 * Created by gkatzioura on 5/28/16.
 */
@Service
public class SampleService {

    public Sample createSample(String sampleName) {

        Sample sample = new Sample();
        sample.setName(sampleName);

        return sample;
    }
}
