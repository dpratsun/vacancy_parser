package ru.job4j.vacanciesparser.repository;

import ru.job4j.vacanciesparser.entity.Vacancy;

import java.util.List;

public interface VacancyRepository {
    void store(List<Vacancy> vacancies);

    List<Vacancy> findAll();
}
