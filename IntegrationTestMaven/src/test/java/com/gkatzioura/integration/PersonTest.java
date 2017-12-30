package com.gkatzioura.integration;

import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

    @Test
    public void testHello() {

        Person person = new Person();

        String hello = person.hello();
        Assert.assertEquals(hello,"Hello");
    }

}
