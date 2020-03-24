package ru.job4j.vacanciesparser.dataprovider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;

public class WebPageDataProvider implements DataProvider {
    private static final Logger LOG = LogManager.getLogger(WebPageDataProvider.class);
    private final String charset;

    public WebPageDataProvider(String charset) {
        this.charset = charset;
    }

    @Override
    public String get(String source) {
        String html = "";
        try {
            html = Jsoup.parse(new URL(source).openStream(), charset, source).html();
        } catch (IOException e) {
            LOG.error(String.format("Error during connection to url: %s", source), e);
        }
        return html;
    }
}
