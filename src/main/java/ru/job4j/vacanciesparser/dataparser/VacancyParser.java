package ru.job4j.vacanciesparser.dataparser;

import ru.job4j.vacanciesparser.entity.Vacancy;

public interface VacancyParser {
    Vacancy parse(String data);
}
