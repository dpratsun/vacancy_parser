package ru.job4j.vacanciesparser.parser.site;

import ru.job4j.vacanciesparser.entity.Vacancy;

import java.util.Set;

public interface SiteParser {
    Set<Vacancy> parse();
}
