package ru.job4j.vacanciesparser.dataparser;

import ru.job4j.vacanciesparser.entity.Vacancy;

import java.util.List;

public interface VacanciesDataParser {
    List<Vacancy> parse(String data);
}
