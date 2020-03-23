package ru.job4j.vacanciesparser.repository;

import ru.job4j.vacanciesparser.entity.Vacancy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class SqlVacancyRepository implements VacancyRepository {
    private final static String INSERT_SQL = "INSERT INTO `vacancy` (`name`, `text`, `url`) VALUES(?, ?, ?);";

    private Connection connection;

    public SqlVacancyRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void store(Set<Vacancy> vacancies) {
        setAutoCommit(false);
        try (var statement = connection.prepareStatement(INSERT_SQL)) {
            for (var vacancy: vacancies) {
                statement.setString(1, vacancy.getName());
                statement.setString(2, vacancy.getText());
                statement.setString(3, vacancy.getUrl());
                statement.addBatch();
            }
            statement.executeBatch();
            commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setAutoCommit(true);
    }

    @Override
    public List<Vacancy> findAll() {
        return null;
    }

    private void setAutoCommit(boolean value) {
        try {
            connection.setAutoCommit(value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void commit() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
