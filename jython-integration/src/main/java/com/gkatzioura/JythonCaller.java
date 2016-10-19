package com.gkatzioura;

import org.python.core.PyClass;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyObjectDerived;
import org.python.util.PythonInterpreter;

import java.io.InputStream;

/**
 * Created by gkatzioura on 19/10/2016.
 */
public class JythonCaller {

    private PythonInterpreter pythonInterpreter;

    public JythonCaller() {
        pythonInterpreter = new PythonInterpreter();
    }

    public void invokeScript(InputStream inputStream) {

        pythonInterpreter.execfile(inputStream);
    }

    public void invokeClass() {

        pythonInterpreter.exec("from divider import Divider");
        PyClass dividerDef = (PyClass) pythonInterpreter.get("Divider");
        PyObject divider = dividerDef.__call__();
        PyObject pyObject = divider.invoke("divide",new PyInteger(20),new PyInteger(4));

        System.out.println(pyObject.toString());
    }

}
