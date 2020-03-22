package ru.job4j.vacanciesparser.repository;

import ru.job4j.vacanciesparser.entity.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class ListVacancyRepository implements VacancyRepository {
    private List<Vacancy> vacancies = new ArrayList<>();

    @Override
    public void store(List<Vacancy> vacancies) {
        this.vacancies.addAll(vacancies);
    }

    @Override
    public List<Vacancy> findAll() {
        return this.vacancies;
    }
}
