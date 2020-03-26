package ru.job4j.vacanciesparser.factory;

import ru.job4j.vacanciesparser.dataprovider.DataProvider;
import ru.job4j.vacanciesparser.parser.data.SqlRuVacanciesParser;
import ru.job4j.vacanciesparser.parser.data.SqlRuVacancyParser;
import ru.job4j.vacanciesparser.parser.date.SqlRuDateParser;
import ru.job4j.vacanciesparser.parser.site.SiteParser;
import ru.job4j.vacanciesparser.parser.site.SqlRuSiteParser;
import ru.job4j.vacanciesparser.predicate.JavaVacancyPredicate;
import ru.job4j.vacanciesparser.predicate.VacancyDatePredicate;

import java.util.Date;

public class SqlRuSiteParserFactory implements SiteParserFactory {

    private final DataProvider dataProvider;

    public SqlRuSiteParserFactory(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public SiteParser build(Date lastParseDate, String source) {
        return new SqlRuSiteParser(
                new SqlRuVacanciesParser(
                        new SqlRuDateParser(),
                        new JavaVacancyPredicate(),
                        new VacancyDatePredicate(lastParseDate)
                ),
                new SqlRuVacancyParser(),
                dataProvider,
                source
        );
    }
}
