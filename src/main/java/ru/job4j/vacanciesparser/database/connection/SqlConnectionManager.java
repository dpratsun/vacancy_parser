package ru.job4j.vacanciesparser.database.connection;

import ru.job4j.vacanciesparser.properties.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnectionManager implements ConnectionManager, AutoCloseable {
    private final static String CONNECTION_URL_PROPERTY = "url";
    private final static String CONNECTION_USER_PROPERTY = "username";
    private final static String CONNECTION_PASSWORD_PROPERTY = "password";

    private Connection connection;

    public SqlConnectionManager(Properties properties) throws SQLException {
        connection = DriverManager.getConnection(
                properties.getValue(CONNECTION_URL_PROPERTY),
                properties.getValue(CONNECTION_USER_PROPERTY),
                properties.getValue(CONNECTION_PASSWORD_PROPERTY)
        );
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            if (!connection.isClosed()) {
                connection.commit();
                connection.close();
            }
        }
    }
}
