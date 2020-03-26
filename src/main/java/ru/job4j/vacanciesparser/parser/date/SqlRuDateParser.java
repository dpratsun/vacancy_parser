package ru.job4j.vacanciesparser.parser.date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SqlRuDateParser implements DateParser {
    private static final Logger LOG = LogManager.getLogger(SqlRuDateParser.class);

    private final static String TODAY = "сегодня";
    private final static String YESTERDAY = "вчера";
    private final static String PATTERN = "dd MMM yy',' h:mm";
    private final static String[] MONTHS = {"янв", "фев", "мар", "апр", "мая", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"};

    private SimpleDateFormat simpleDateFormat;

    public SqlRuDateParser() {
        DateFormatSymbols symbols = DateFormatSymbols.getInstance();
        symbols.setMonths(MONTHS);
        this.simpleDateFormat = new SimpleDateFormat(PATTERN, symbols);
    }

    @Override
    public Date parse(String date) {
        Date result = Calendar.getInstance().getTime();
        try {
            result = simpleDateFormat.parse(prepareDateString(date));
        } catch (ParseException e) {
            LOG.error(String.format("Error during parsing of the string: %s", date), e);
        }
        return result;
    }

    private String prepareDateString(String date) {
        if (date.contains(TODAY)) {
            date = replace(date, TODAY, Calendar.getInstance());
        } else if (date.contains(YESTERDAY)) {
            Calendar yesterday = Calendar.getInstance();
            yesterday.add(Calendar.DATE, -1);
            date = replace(date, YESTERDAY, yesterday);
        }
        return date;
    }

    private String replace(String date, String target, Calendar day) {
        var replacement = day.get(Calendar.DAY_OF_MONTH)
                + " " + MONTHS[day.get(Calendar.MONTH)]
                + " " + day.get(Calendar.YEAR);
        return date.replace(target, replacement);
    }
}
