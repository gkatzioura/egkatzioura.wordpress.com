package com.gkatzioura.integration;

import org.junit.Assert;
import org.junit.Test;

public class PersonIT {

    @Test
    public void itHello() {

        Person person = new Person();

        String hello = person.hello();
        Assert.assertEquals(hello,"Hello");
    }

}
