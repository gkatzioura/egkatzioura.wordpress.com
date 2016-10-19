package com.gkatzioura;


import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by gkatzioura on 19/10/2016.
 */
public class JythonCallerTest {

    private JythonCaller jythonCaller;

    @Before
    public void setUp() {
        jythonCaller = new JythonCaller();
    }

    @Test
    public void testInvokeScript() {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("hello_world.py");
        jythonCaller.invokeScript(inputStream);
    }

    @Test
    public void testInvokeClassCaller() {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("classcaller.py");
        jythonCaller.invokeScript(inputStream);
    }

    @Test
    public void testInvokeClass() {
        jythonCaller.invokeClass();
    }

}
