package ru.job4j.vacanciesparser.repository;

import ru.job4j.vacanciesparser.entity.Vacancy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SqlVacancyRepository implements VacancyRepository {
    private final static String INSERT_SQL = "INSERT INTO `vacancy` (`name`, `text`, `url`) VALUES(?, ?, ?);";
    private final static String SELECT_SQL = "SELECT * FROM `vacancy`;";

    private final Connection connection;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vacancy> findAll() {
        List<Vacancy> result = new ArrayList<>();
        try (var statement = connection.prepareStatement(SELECT_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new Vacancy(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void setAutoCommit(boolean value) {
        try {
            connection.setAutoCommit(value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
