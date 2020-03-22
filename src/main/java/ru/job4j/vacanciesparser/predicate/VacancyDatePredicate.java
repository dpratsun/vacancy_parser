package ru.job4j.vacanciesparser.predicate;

import java.util.Date;
import java.util.function.Predicate;

public class VacancyDatePredicate implements Predicate<Date> {
    private final Date lastParsedDate;

    public VacancyDatePredicate(Date lastParsedDate) {
        this.lastParsedDate = lastParsedDate;
    }

    @Override
    public boolean test(Date date) {
        return date.after(lastParsedDate);
    }
}
