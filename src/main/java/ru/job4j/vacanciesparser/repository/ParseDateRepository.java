package ru.job4j.vacanciesparser.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public interface ParseDateRepository {
    void add();

    Date getLast();

    default Date getDefaultDate() throws ParseException {
        Calendar now = Calendar.getInstance();
        return new SimpleDateFormat("dd-MM-yyyy H:mm")
                .parse("01-01-" + now.get(Calendar.YEAR) + " 00:00");
    }
}
