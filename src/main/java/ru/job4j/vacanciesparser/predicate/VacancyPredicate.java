package ru.job4j.vacanciesparser.predicate;

import java.util.function.Predicate;

public interface VacancyPredicate extends Predicate<String> {
    @Override
    boolean test(String s);
}
