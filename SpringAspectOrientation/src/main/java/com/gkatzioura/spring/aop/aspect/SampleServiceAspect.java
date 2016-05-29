package com.gkatzioura.spring.aop.aspect;

import com.gkatzioura.spring.aop.model.Sample;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by gkatzioura on 5/28/16.
 */
@Aspect
@Component
public class SampleServiceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleServiceAspect.class);

    @Before("execution(* com.gkatzioura.spring.aop.service.SampleService.createSample (java.lang.String)) && args(sampleName)")
    public void beforeSampleCreation(String sampleName) {

        LOGGER.info("A request was issued for a sample name: "+sampleName);
    }

    @Around("execution(* com.gkatzioura.spring.aop.service.SampleService.createSample (java.lang.String)) && args(sampleName)")
    public Object aroundSampleCreation(ProceedingJoinPoint proceedingJoinPoint,String sampleName) throws Throwable {

        LOGGER.info("A request was issued for a sample name: "+sampleName);

        sampleName = sampleName+"!";

        Sample sample = (Sample) proceedingJoinPoint.proceed(new Object[] {sampleName});
        sample.setName(sample.getName().toUpperCase());

        return sample;
    }

}

