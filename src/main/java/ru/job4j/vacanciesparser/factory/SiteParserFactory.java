package ru.job4j.vacanciesparser.factory;

import ru.job4j.vacanciesparser.parser.site.SiteParser;

import java.util.Date;

public interface SiteParserFactory {
    SiteParser build(Date lastParseDate, String source);
}
