package ru.job4j.vacanciesparser.repository;

import ru.job4j.vacanciesparser.entity.Vacancy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListVacancyRepository implements VacancyRepository {
    private List<Vacancy> vacancies = new ArrayList<>();

    @Override
    public void store(Set<Vacancy> vacancies) {
        this.vacancies.addAll(vacancies);
    }

    @Override
    public List<Vacancy> findAll() {
        return this.vacancies;
    }
}
