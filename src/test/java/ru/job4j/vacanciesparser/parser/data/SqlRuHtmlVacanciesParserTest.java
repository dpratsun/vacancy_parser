package ru.job4j.vacanciesparser.parser.data;

import org.junit.Test;
import ru.job4j.vacanciesparser.dataprovider.DataProvider;
import ru.job4j.vacanciesparser.dataprovider.FileDataProvider;
import ru.job4j.vacanciesparser.entity.Vacancy;
import ru.job4j.vacanciesparser.parser.date.SqlRuDateParser;
import ru.job4j.vacanciesparser.predicate.JavaVacancyPredicate;
import ru.job4j.vacanciesparser.predicate.VacancyDatePredicate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SqlRuHtmlVacanciesParserTest {
    private final VacanciesParser parser = new SqlRuHtmlVacanciesParser(
            new SqlRuDateParser(),
            new JavaVacancyPredicate(),
            new VacancyDatePredicate(new SimpleDateFormat("dd-mm-Y H:mm").parse("08-03-2020 00:00"))
    );
    private final DataProvider provider = new FileDataProvider();

    public SqlRuHtmlVacanciesParserTest() throws ParseException {
    }

    @Test
    public void whenNoVacanciesThanParseShouldReturnEmptyList() {
        var result = parser.parse(provider.get("testdata.html"));
        assertTrue(result.isEmpty());
    }

    @Test
    public void whenPageHaveVacanciesThanParserShouldReturnListOfAllVacancies() {
        var result = parser.parse(provider.get("sqlru.html"));
        List<Vacancy> expected = List.of(
                new Vacancy(0, "Lead Android developer (Java), Moscow, to 250000", "", "https://www.sql.ru/forum/1323427/vedushhiy-razrabotchik-android-java-moskva-do-250k"),
                new Vacancy(0, "JAVA # Moscow", "", "https://www.sql.ru/forum/1321527/java-moskva"),
                new Vacancy(0, "java developer needed (middle/middle+) projects IoT MTC", "", "https://www.sql.ru/forum/1322779/nuzhen-java-razrabotchik-middle-middle-proekty-iot-mts")
        );
        assertThat(result, is(expected));
    }
}
