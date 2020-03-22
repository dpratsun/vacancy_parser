package ru.job4j.vacanciesparser.properties;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

public class FileProperties implements IProperties {
    private Properties properties;

    public FileProperties(String filename) {
        properties = new Properties();
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
