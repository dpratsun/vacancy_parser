package ru.job4j.vacanciesparser.parser.data;

import ru.job4j.vacanciesparser.entity.Vacancy;

public interface VacancyParser {
    Vacancy parse(String data);
}
