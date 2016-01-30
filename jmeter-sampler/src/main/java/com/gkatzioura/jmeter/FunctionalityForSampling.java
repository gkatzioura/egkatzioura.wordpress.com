package com.gkatzioura.jmeter;

/**
 * Created by gkatzioura on 30/1/2016.
 */
public class FunctionalityForSampling {

    public String testFunction(String arguement1,String arguement2) throws Exception {

        if (arguement1.equals(arguement2)) {
            throw new Exception();
        }

        else return arguement1+arguement2;
    }

}
