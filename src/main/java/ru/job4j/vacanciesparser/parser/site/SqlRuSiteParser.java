package ru.job4j.vacanciesparser.parser.site;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.vacanciesparser.dataprovider.DataProvider;
import ru.job4j.vacanciesparser.entity.Vacancy;
import ru.job4j.vacanciesparser.parser.data.VacanciesParser;
import ru.job4j.vacanciesparser.parser.data.VacancyParser;

import java.util.HashSet;
import java.util.Set;

public class SqlRuSiteParser implements SiteParser {
    private static final Logger LOG = LogManager.getLogger(SqlRuSiteParser.class);

    private VacanciesParser vacanciesParser;
    private VacancyParser vacancyParser;
    private DataProvider dataProvider;
    private String source;

    public SqlRuSiteParser(VacanciesParser vacanciesParser, VacancyParser vacancyParser, DataProvider dataProvider, String source) {
        this.vacanciesParser = vacanciesParser;
        this.vacancyParser = vacancyParser;
        this.dataProvider = dataProvider;
        this.source = source;
    }

    @Override
    public Set<Vacancy> parse() {
        LOG.info("Trying to parse Sql.ru for vacancies. Source to parse is " + source);

        Set<Vacancy> result = new HashSet<>();
        var html = dataProvider.get(source);
        var vacancies = vacanciesParser.parse(html);

        LOG.info(vacancies.size() + " vacancies have been found.");

        int page = 2;
        while (!vacancies.isEmpty()) {
            for (var vacancy: vacancies) {
                var vacancyPage = dataProvider.get(vacancy.getUrl());
                var vacancyPageParseResult = vacancyParser.parse(vacancyPage);
                vacancy.setText(vacancyPageParseResult.getText());
                result.add(vacancy);
            }

            var nextSource = source + page++;
            LOG.info("Trying to parse next Sql.ru source. Source is " + nextSource);

            html = dataProvider.get(nextSource);
            vacancies = vacanciesParser.parse(html);

            LOG.info(vacancies.size() + " vacancies have been found.");
        }
        LOG.info(String.format("Sql.ru parse is completed. %d vacancies have been found", result.size()));
        return result;
    }
}
