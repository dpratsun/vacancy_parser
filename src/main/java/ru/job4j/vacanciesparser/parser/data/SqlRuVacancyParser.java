package ru.job4j.vacanciesparser.parser.data;

import org.jsoup.Jsoup;
import ru.job4j.vacanciesparser.entity.Vacancy;

public class SqlRuVacancyParser implements VacancyParser {

    @Override
    public Vacancy parse(String data) {
        var document = Jsoup.parse(data);
        return new Vacancy(0,
                document.selectFirst("td.messageHeader").text(),
                document.getElementsByClass("msgBody").get(1).text(),
                "");
    }
}
