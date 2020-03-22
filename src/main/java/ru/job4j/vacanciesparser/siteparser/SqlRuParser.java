package ru.job4j.vacanciesparser.siteparser;

import ru.job4j.vacanciesparser.dataparser.VacanciesParser;
import ru.job4j.vacanciesparser.dataparser.VacancyParser;
import ru.job4j.vacanciesparser.dataprovider.DataProvider;
import ru.job4j.vacanciesparser.entity.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class SqlRuParser implements SiteParser {
    private VacanciesParser vacanciesParser;
    private VacancyParser vacancyParser;
    private DataProvider data;
    private String source;

    public SqlRuParser(VacanciesParser vacanciesParser, VacancyParser vacancyParser, DataProvider data, String source) {
        this.vacanciesParser = vacanciesParser;
        this.vacancyParser = vacancyParser;
        this.data = data;
        this.source = source;
    }

    @Override
    public List<Vacancy> parse() {
        List<Vacancy> result = new ArrayList<>();
        var html = data.get(source);
        var vacancies = vacanciesParser.parse(html);
        int page = 2;
        while (!vacancies.isEmpty()) {
            for (var vacancy: vacancies) {
                var vacancyPage = data.get(vacancy.getUrl());
                var vacancyPageParseResult = vacancyParser.parse(vacancyPage);
                vacancy.setText(vacancyPageParseResult.getText());
                result.add(vacancy);
            }
            html = data.get(source + page++);
            vacancies = vacanciesParser.parse(html);
        }
        return result;
    }
}
