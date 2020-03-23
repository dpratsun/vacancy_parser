package ru.job4j.vacanciesparser.factory;

import ru.job4j.vacanciesparser.dataprovider.WebPageDataProvider;
import ru.job4j.vacanciesparser.parser.data.SqlRuHtmlVacanciesParser;
import ru.job4j.vacanciesparser.parser.data.SqlRuHtmlVacancyParser;
import ru.job4j.vacanciesparser.parser.date.SqlRuDateParser;
import ru.job4j.vacanciesparser.parser.site.SiteParser;
import ru.job4j.vacanciesparser.parser.site.SqlRuSiteParser;
import ru.job4j.vacanciesparser.predicate.JavaVacancyPredicate;
import ru.job4j.vacanciesparser.predicate.VacancyDatePredicate;

import java.util.Date;

public class SqlRuSiteParserFactory {
    public static SiteParser build(Date lastParseDate, String url) {
        return new SqlRuSiteParser(
                new SqlRuHtmlVacanciesParser(
                        new SqlRuDateParser(),
                        new JavaVacancyPredicate(),
                        new VacancyDatePredicate(lastParseDate)
                ),
                new SqlRuHtmlVacancyParser(),
                new WebPageDataProvider(),
                url
        );
    }
}
