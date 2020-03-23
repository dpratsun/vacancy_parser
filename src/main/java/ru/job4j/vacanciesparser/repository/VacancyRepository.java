package ru.job4j.vacanciesparser.repository;

import ru.job4j.vacanciesparser.entity.Vacancy;

import java.util.List;
import java.util.Set;

public interface VacancyRepository {
    void store(Set<Vacancy> vacancies);

    List<Vacancy> findAll();
}
