package ru.job4j.vacanciesparser;

import org.jsoup.Jsoup;
import ru.job4j.vacanciesparser.dataprovider.FileDataProvider;

public class Application {
    public static void main(String[] args) {
        /*var provider = new WebPageDataProvider();
        var sqlRuJavaVacancyParser = new SqlRuHtmlVacanciesParser(new JavaVacancyPredicate());
        var repository = new SqlVacancyRepository();

        repository.store();*/
        var document = Jsoup.parse(new FileDataProvider().get("sqlru.html"));
        var elements = document.select("");
    }
}
