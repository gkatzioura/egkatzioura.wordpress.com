package com.example.gkatzioura.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.sql.DataSource;

public class GetConnectionHandler implements InvocationHandler {

    private final DataSource dataSource;

    public GetConnectionHandler(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("getConnection")) {
            System.out.println("Called before actual method");

        }

        return method.invoke(dataSource, args);
    }

    public static DataSource proxy(DataSource dataSource) {
        return (DataSource) Proxy.newProxyInstance(DataSource.class.getClassLoader(), new Class[]{DataSource.class}, new GetConnectionHandler(dataSource));
    }

}
