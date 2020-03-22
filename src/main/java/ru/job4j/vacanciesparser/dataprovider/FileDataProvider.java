package ru.job4j.vacanciesparser.dataprovider;

import ru.job4j.vacanciesparser.properties.FileProperties;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDataProvider implements DataProvider {
    @Override
    public String get(String source) {
        var data = "";
        var resource = FileDataProvider.class.getClassLoader().getResourceAsStream(source);
        try {
            var bf = new BufferedReader(new InputStreamReader(resource, Charset.forName("windows-1251")));
            data = bf.lines().reduce("", String::concat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
