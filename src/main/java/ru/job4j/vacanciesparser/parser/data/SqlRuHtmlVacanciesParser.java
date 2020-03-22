package ru.job4j.vacanciesparser.parser.data;

import org.jsoup.Jsoup;
import ru.job4j.vacanciesparser.entity.Vacancy;
import ru.job4j.vacanciesparser.parser.date.DateParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class SqlRuHtmlVacanciesParser implements VacanciesParser {
    private final static int LINK_TD_INDEX = 1;
    private final static String TR_SELECTOR = "tr:has(td.postslisttopic:not(:contains(Важно)))";

    private final DateParser dateParser;
    private final Predicate<String> vacancyPredicate;
    private final Predicate<Date> datePredicate;

    public SqlRuHtmlVacanciesParser(DateParser dateParser, Predicate<String> vacancyPredicate, Predicate<Date> datePredicate) {
        this.dateParser = dateParser;
        this.vacancyPredicate = vacancyPredicate;
        this.datePredicate = datePredicate;
    }

    @Override
    public List<Vacancy> parse(String data) {
        List<Vacancy> vacancies = new ArrayList<>();
        var document = Jsoup.parse(data);
        var trElements = document.select(TR_SELECTOR);

        for (var trElement: trElements) {
            var tdElements = trElement.getElementsByTag("td");
            var element = tdElements.last();
            if (!datePredicate.test(dateParser.parse(element.text()))) {
                break;
            }
            element = tdElements.get(LINK_TD_INDEX);
            element = element.selectFirst("a");
            if (vacancyPredicate.test(element.text())) {
                vacancies.add(new Vacancy(0, element.text(), "", element.attr("href")));
            }
        }

        return vacancies;
    }

    private boolean checkDate(String date) {
        return false;
    }
}
