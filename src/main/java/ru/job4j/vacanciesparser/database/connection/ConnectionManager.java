package ru.job4j.vacanciesparser.database.connection;

import java.sql.Connection;

public interface ConnectionManager {
    Connection getConnection();
}
