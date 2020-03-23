package ru.job4j.vacanciesparser.repository;

import java.sql.Connection;
import java.util.Date;

public class SqlParseDateRepository implements ParseDateRepository {
    private final static String INSERT_SQL = "INSERT INTO `parse_date` (`date_time`) VALUES(CURRENT_TIMESTAMP);";
    private final static String SELECT_MAX_SQL = "SELECT MAX(`date_time`) FROM `parse_date`;";

    private Connection connection;

    public SqlParseDateRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add() {
        try (var statement = connection.prepareStatement(INSERT_SQL)) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Date getLast() {
        Date date = null;
        try (var statement = connection.prepareStatement(SELECT_MAX_SQL)) {
            var result = statement.executeQuery();
            if (result.next()) {
                date = new Date(result.getTimestamp(1).getTime());
            } else {
                date = getDefaultDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
