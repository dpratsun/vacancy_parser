package ru.job4j.vacanciesparser.properties;

import java.io.InputStream;
import java.util.NoSuchElementException;

public class FileProperties implements Properties {
    private java.util.Properties properties;

    public FileProperties(String filename) {
        properties = new java.util.Properties();
        try (InputStream in = FileProperties.class.getClassLoader().getResourceAsStream(filename)) {
            properties.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String getValue(String property) {
        var value = properties.getProperty(property);
        if (value == null) {
            throw new NoSuchElementException("Property " + property + " is not set in property file!");
        }
        return value;
    }
}
