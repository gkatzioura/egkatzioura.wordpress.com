package com.egkatzioura;

import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.ChangeEventFormat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();

        //offset.storage.file.filename=./offset.dato

        try(final InputStream stream = Application.class.getClassLoader().getResourceAsStream("embedded_debezium.properties")) {
            properties.load(stream);
        }
        properties.put("offset.storage.file.filename",new File("offset.dat").getAbsolutePath());

        var engine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(properties)
                .notifying(new CustomChangeConsumer())
                .build();
        engine.run();

    }

}