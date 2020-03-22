package ru.job4j.vacanciesparser.dataparser;

import org.junit.Test;
import ru.job4j.vacanciesparser.dataprovider.FileDataProvider;
import ru.job4j.vacanciesparser.entity.Vacancy;

import static org.junit.Assert.*;

public class SqlRuHtmlVacancyParserTest {

    @Test
    public void whenParseCorrectPageShouldReturnParsedVacancy() {
        var parser = new SqlRuHtmlVacancyParser();
        var result = parser.parse(new FileDataProvider().get("androidjava.html"));
        var expected = new Vacancy(
                0,
                "Ведущий разработчик Android (Java), Москва, до 250К [new]",
                "Ведущий разработчик Android (Java) У моего знакомого в крупной и стабильной транспортной компании открыта вакансия для ведущего разработчика #android #java Офис: м. Комсомольская (удаленки нет) Годовые премии, ДМС и оплата проезда. Вилка 150 - 250 тыс. Возможность участвовать в проекте федерального масштаба. Задачи: - Разработка нового ПО для нужд компании; - Анализ и доработка существующего ПО системы продаж; - Разработка под Android на Java приложений (продажа/контроль билетов, контроль работы оборудования). От вас: - Успешный опыт разработки под Android (от года); - Готовность разбираться в чужом коде; - Необходимые навыки работы с: #dagger2; #sqlite; #greendao; #supportlibraries; #logback; #findbugs; #gson; #guava; #xbindings; #rxjava; #okio; #junit; #mockito; #robolectric. Резюме и рекомендации на почту: dnshigaeva@gmail.com или в телеграм: DianaShigaeva",
                "");
        assertEquals(result, expected);
    }
}