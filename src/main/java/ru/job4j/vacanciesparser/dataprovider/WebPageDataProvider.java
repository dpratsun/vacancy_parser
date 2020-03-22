package ru.job4j.vacanciesparser.dataprovider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

import java.io.IOException;

public class WebPageDataProvider implements DataProvider {
    private static final Logger LOG = LogManager.getLogger(WebPageDataProvider.class);

    @Override
    public String get(String source) {
        String html = "";
        try {
            html = Jsoup.connect(source).get().html();
        } catch (IOException e) {
            LOG.error(String.format("Error during connection to url: %s", source), e);
        }
        return html;
    }
}
