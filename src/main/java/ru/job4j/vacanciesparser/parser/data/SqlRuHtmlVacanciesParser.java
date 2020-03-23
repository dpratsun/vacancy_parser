package ru.job4j.vacanciesparser.parser.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import ru.job4j.vacanciesparser.entity.Vacancy;
import ru.job4j.vacanciesparser.parser.date.DateParser;
import ru.job4j.vacanciesparser.predicate.DatePredicate;
import ru.job4j.vacanciesparser.predicate.VacancyPredicate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class SqlRuHtmlVacanciesParser implements VacanciesParser {
    private static final Logger LOG = LogManager.getLogger(SqlRuHtmlVacanciesParser.class);

    private final static int LINK_TD_INDEX = 1;
    private final static String TR_SELECTOR = "tr:has(td.postslisttopic:not(:contains(Важно)))";

    private final DateParser dateParser;
    private final VacancyPredicate vacancyPredicate;
    private final DatePredicate datePredicate;

    public SqlRuHtmlVacanciesParser(DateParser dateParser, VacancyPredicate vacancyPredicate, DatePredicate datePredicate) {
        this.dateParser = dateParser;
        this.vacancyPredicate = vacancyPredicate;
        this.datePredicate = datePredicate;
    }

    @Override
    public Set<Vacancy> parse(String data) {
        Set<Vacancy> vacancies = new HashSet<>();
        var document = Jsoup.parse(data);
        var trEls = document.select(TR_SELECTOR);
        for (var trEl: trEls) {
            var tdEls = trEl.getElementsByTag("td");
            var dateEl = tdEls.last();
            var date = dateParser.parse(dateEl.text());
            if (date == null) {
                continue;
            }
            if (!datePredicate.test(date)) {
                LOG.info("Stop parsing vacancies because vacancy date is after acceptable date.");
                break;
            }
            var titleEl = tdEls.get(LINK_TD_INDEX);
            var aEl = titleEl.selectFirst("a");
            if (vacancyPredicate.test(aEl.text())) {
                vacancies.add(new Vacancy(0, aEl.text(), "", aEl.attr("href")));
            }
        }
        return vacancies;
    }
}
