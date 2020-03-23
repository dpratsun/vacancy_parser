package ru.job4j.vacanciesparser.predicate;

public class JavaVacancyPredicate implements VacancyPredicate {
    @Override
    public boolean test(String s) {
        s = s.toLowerCase();
        return s.contains("java") && !(s.contains("javascript") || s.contains("java script"));
    }
}
