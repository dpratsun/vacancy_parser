package ru.job4j.vacanciesparser.dataprovider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileDataProvider extends AbstractDataProvider {

    @Override
    protected String getData(String source) {
        var data = "";
        var resource = FileDataProvider.class.getClassLoader().getResourceAsStream(source);
        if (resource != null) {
            var bf = new BufferedReader(new InputStreamReader(resource, Charset.forName("windows-1251")));
            data = bf.lines().reduce("", String::concat);
        }
        return data;
    }
}
