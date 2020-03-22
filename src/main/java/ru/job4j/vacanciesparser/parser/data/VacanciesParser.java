package ru.job4j.vacanciesparser.parser.data;

import ru.job4j.vacanciesparser.entity.Vacancy;

import java.util.List;

public interface VacanciesParser {
    List<Vacancy> parse(String data);
}
