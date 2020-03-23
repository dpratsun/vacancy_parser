package ru.job4j.vacanciesparser.parser.data;

import ru.job4j.vacanciesparser.entity.Vacancy;

import java.util.Set;

public interface VacanciesParser {
    Set<Vacancy> parse(String data);
}
