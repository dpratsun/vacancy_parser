package ru.job4j.vacanciesparser.dataparser;

import org.jsoup.Jsoup;
import ru.job4j.vacanciesparser.entity.Vacancy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SqlRuHtmlVacanciesParser implements VacanciesParser {
    private final static int LINK_TD_INDEX = 1;
    private final static String TR_SELECTOR = "tr:has(td.postslisttopic:not(:contains(Важно)))";

    private final Predicate<String> vacancyPredicate;
    private final Predicate<LocalDateTime> datePredicate;

    public SqlRuHtmlVacanciesParser(Predicate<String> vacancyPredicate, Predicate<LocalDateTime> datePredicate) {
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
            //var tdElement = tdElements.last();
            if (!datePredicate.test(LocalDateTime.now())) {
                break;
            }
            var tdElement = tdElements.get(LINK_TD_INDEX);
            var aElement = tdElement.selectFirst("a");
            if (vacancyPredicate.test(aElement.text())) {
                vacancies.add(new Vacancy(0, aElement.text(), "", aElement.attr("href")));
            }
        }

        return vacancies;
    }

    private boolean checkDate(String date) {
        return false;
    }
}
