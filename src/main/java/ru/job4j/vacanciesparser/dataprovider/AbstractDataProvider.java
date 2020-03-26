package ru.job4j.vacanciesparser.dataprovider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractDataProvider implements DataProvider {
    private static final Logger LOG = LogManager.getLogger(DataProvider.class);
    private static final String ERROR_MESSAGE = "Error getting data from : %s";

    @Override
    public String get(String source) {
        var data = "";
        try {
            data = getData(source);
        } catch (Exception e) {
            LOG.error(String.format(ERROR_MESSAGE, source), e);
        }
        return data;
    }

    abstract protected String getData(String source) throws Exception;
}
