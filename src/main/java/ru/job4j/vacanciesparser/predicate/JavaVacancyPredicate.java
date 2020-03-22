package ru.job4j.vacanciesparser.predicate;

import java.util.function.Predicate;

public class JavaVacancyPredicate implements Predicate<String> {
    @Override
    public boolean test(String s) {
        s = s.toLowerCase();
        return s.contains("java") && !(s.contains("javascript") || s.contains("java script"));
    }
}
