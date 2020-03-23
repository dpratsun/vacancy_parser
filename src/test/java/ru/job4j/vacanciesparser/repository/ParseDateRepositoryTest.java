package ru.job4j.vacanciesparser.repository;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class ParseDateRepositoryTest {

    @Test
    public void getDefaultDateShouldReturnFirsDayOfCurrentYear() throws ParseException {
        Calendar firstDay = Calendar.getInstance();
        firstDay.set(Calendar.DAY_OF_YEAR, 1);
        firstDay.set(Calendar.HOUR_OF_DAY, 0);
        firstDay.set(Calendar.MINUTE, 0);
        firstDay.set(Calendar.SECOND, 0);
        firstDay.set(Calendar.MILLISECOND, 0);
        var expected = firstDay.getTime();
        var actual = new ParseDateRepository() {
            @Override
            public void add() { }

            @Override
            public Date getLast() {
                return null;
            }
        }.getDefaultDate();
        assertEquals(expected, actual);
    }
}