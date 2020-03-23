package ru.job4j.vacanciesparser.predicate;

import java.util.Date;

public class VacancyDatePredicate implements DatePredicate {
    private final Date lastParsedDate;

    public VacancyDatePredicate(Date lastParsedDate) {
        this.lastParsedDate = lastParsedDate;
    }

    @Override
    public boolean test(Date date) {
        return date.after(lastParsedDate);
    }
}
