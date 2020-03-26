package ru.job4j.vacanciesparser.dataprovider;

import org.jsoup.Jsoup;

public class WebPageDataProvider extends AbstractDataProvider {

    @Override
    protected String getData(String source) throws Exception {
        return Jsoup.connect(source).get().html();
    }
}
