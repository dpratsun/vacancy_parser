package ru.job4j.vacanciesparser.dataprovider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileDataProvider implements DataProvider {
    private static final Logger LOG = LogManager.getLogger(FileDataProvider.class);

    @Override
    public String get(String source) {
        var data = "";
        var resource = FileDataProvider.class.getClassLoader().getResourceAsStream(source);
        try {
            var bf = new BufferedReader(new InputStreamReader(resource, Charset.forName("windows-1251")));
            data = bf.lines().reduce("", String::concat);
        } catch (Exception e) {
            LOG.error(String.format("Error getting data from file: %s", source), e);
        }
        return data;
    }
}
