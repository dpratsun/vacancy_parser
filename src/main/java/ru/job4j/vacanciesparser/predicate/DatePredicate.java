package ru.job4j.vacanciesparser.predicate;

import java.util.Date;
import java.util.function.Predicate;

public interface DatePredicate extends Predicate<Date> {
    @Override
    boolean test(Date date);
}
