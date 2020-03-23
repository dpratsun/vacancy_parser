package ru.job4j.vacanciesparser.predicate.date;

import org.junit.Test;
import ru.job4j.vacanciesparser.predicate.DatePredicate;
import ru.job4j.vacanciesparser.predicate.VacancyDatePredicate;

import java.util.Calendar;

import static org.junit.Assert.*;

public class VacancyDatePredicateTest {

    @Test
    public void whenTestDateAfterShouldReturnTrue() {
        var yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        DatePredicate predicate = new VacancyDatePredicate(yesterday.getTime());
        var today = Calendar.getInstance();
        assertTrue(predicate.test(today.getTime()));
    }

    @Test
    public void whenTestDateBeforeShouldReturnFalse() {
        var today = Calendar.getInstance();
        DatePredicate predicate = new VacancyDatePredicate(today.getTime());
        var yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        assertFalse(predicate.test(yesterday.getTime()));
    }
}