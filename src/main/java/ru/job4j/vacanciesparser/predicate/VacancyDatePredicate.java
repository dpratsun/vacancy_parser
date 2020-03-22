package ru.job4j.vacanciesparser.predicate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Predicate;

public class VacancyDatePredicate implements Predicate<LocalDateTime> {
    private final LocalDateTime lastParsedDate;

    public VacancyDatePredicate(LocalDateTime lastParsedDate) {
        this.lastParsedDate = lastParsedDate;
    }

    @Override
    public boolean test(LocalDateTime date) {
        return date.isAfter(lastParsedDate);
    }
}
