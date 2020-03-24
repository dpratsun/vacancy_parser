package ru.job4j.vacanciesparser.database.connection;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionRollback {
    private final Connection connection;

    public ConnectionRollback(Connection connection) {
        this.connection = connection;
    }

    /**
     * Create connection with autocommit=false mode and rollback call, when conneciton is closed.
     *
     * @return Connection object.
     * @throws SQLException possible exception.
     */
    public Connection getConnection() throws SQLException {
        connection.setAutoCommit(false);
        return (Connection) Proxy.newProxyInstance(
                ConnectionRollback.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    Object rsl = null;
                    if ("close".equals(method.getName())) {
                        connection.rollback();
                        connection.close();
                    } else {
                        rsl = method.invoke(connection, args);
                    }
                    return rsl;
                }
        );
    }
}
