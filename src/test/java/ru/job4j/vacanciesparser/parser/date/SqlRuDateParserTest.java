package ru.job4j.vacanciesparser.parser.date;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class SqlRuDateParserTest {
    private final DateParser parser = new SqlRuDateParser();

    @Test
    public void whenParseTodayShouldReturnCorrectDate() {
        String date = "сегодня, 14:32";
        Calendar today = Calendar.getInstance();
        setTime(today, 14, 32);
        assertEquals(parser.parse(date), today.getTime());
    }

    @Test
    public void whenParseYesterdayShouldReturnCorrectDate() {
        String date = "вчера, 13:41";
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        setTime(yesterday, 13, 41);
        assertEquals(parser.parse(date), yesterday.getTime());
    }

    @Test
    public void whenParseDateShouldReturnCorrectDate() {
        String date = "22 мар 20, 10:00";
        Calendar today = Calendar.getInstance();
        setTime(today, 10, 0);
        assertEquals(parser.parse(date), today.getTime());
    }

    private void setTime(Calendar day, int hours, int minutes) {
        day.set(Calendar.HOUR_OF_DAY, hours);
        day.set(Calendar.MINUTE, minutes);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
    }
}