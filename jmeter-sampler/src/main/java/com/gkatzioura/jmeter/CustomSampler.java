package com.gkatzioura.jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by gkatzioura on 30/1/2016.
 */
public class CustomSampler extends AbstractJavaSamplerClient implements Serializable {

    private static final String METHOD_TAG = "method";
    private static final String ARG1_TAG = "arg1";
    private static final String ARG2_TAG = "arg2";

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSampler.class);

    @Override
    public Arguments getDefaultParameters() {

        Arguments defaultParameters = new Arguments();
        defaultParameters.addArgument(METHOD_TAG,"test");
        defaultParameters.addArgument(ARG1_TAG,"arg1");
        defaultParameters.addArgument(ARG2_TAG,"arg2");

        return defaultParameters;
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {

        String method = javaSamplerContext.getParameter(METHOD_TAG);
        String arg1 = javaSamplerContext.getParameter(ARG1_TAG);
        String arg2 = javaSamplerContext.getParameter(ARG2_TAG);

        FunctionalityForSampling functionalityForSampling = new FunctionalityForSampling();

        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();

        try {
            String message = functionalityForSampling.testFunction(arg1,arg2);
            sampleResult.sampleEnd();;
            sampleResult.setSuccessful(Boolean.TRUE);
            sampleResult.setResponseCodeOK();
            sampleResult.setResponseMessage(message);
        } catch (Exception e) {
            LOGGER.error("Request was not successfully processed",e);
            sampleResult.sampleEnd();
            sampleResult.setResponseMessage(e.getMessage());
            sampleResult.setSuccessful(Boolean.FALSE);

        }

        return sampleResult;
    }

}
