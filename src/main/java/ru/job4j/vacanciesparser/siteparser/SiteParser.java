package ru.job4j.vacanciesparser.siteparser;

import ru.job4j.vacanciesparser.entity.Vacancy;

import java.util.List;

public interface SiteParser {
    List<Vacancy> parse();
}
