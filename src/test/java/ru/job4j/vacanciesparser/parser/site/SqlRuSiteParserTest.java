package ru.job4j.vacanciesparser.parser.site;

import org.junit.Test;
import ru.job4j.vacanciesparser.dataprovider.FileDataProvider;
import ru.job4j.vacanciesparser.entity.Vacancy;
import ru.job4j.vacanciesparser.parser.data.SqlRuVacanciesParser;
import ru.job4j.vacanciesparser.parser.data.SqlRuVacancyParser;
import ru.job4j.vacanciesparser.parser.date.SqlRuDateParser;
import ru.job4j.vacanciesparser.predicate.JavaVacancyPredicate;
import ru.job4j.vacanciesparser.predicate.VacancyDatePredicate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SqlRuSiteParserTest {

    @Test
    public void whenParseSiteShouldReturnAllCorrectVacancies() throws ParseException {
        var parser = new SqlRuSiteParser(
                new SqlRuVacanciesParser(
                    new SqlRuDateParser(),
                    new JavaVacancyPredicate(),
                    new VacancyDatePredicate(new SimpleDateFormat("dd-mm-Y H:mm").parse("08-03-2020 00:00"))
                ),
                new SqlRuVacancyParser(),
                new FileDataProvider(),
                "sqlru.html");
        var result = parser.parse();
        var expected = Set.of(
                new Vacancy(0, "JAVA # Moscow", "", ""),
                new Vacancy(0, "Lead Android developer (Java), Moscow, to 250000", "", ""),
                new Vacancy(0, "java developer needed (middle/middle+) projects IoT MTC", "", ""),
                new Vacancy(0, "JAVA # Moscow 2", "", ""),
                new Vacancy(0, "Lead Android developer (Java), Moscow, to 250000 2", "", ""),
                new Vacancy(0, "java developer needed (middle/middle+) projects IoT MTC 2", "", "")
        );
        assertEquals(result, expected);
    }
}