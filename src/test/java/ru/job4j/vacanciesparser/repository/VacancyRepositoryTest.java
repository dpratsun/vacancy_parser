package ru.job4j.vacanciesparser.repository;

import org.junit.Test;
import ru.job4j.vacanciesparser.database.connection.ConnectionRollback;
import ru.job4j.vacanciesparser.database.connection.SqlConnectionManager;
import ru.job4j.vacanciesparser.entity.Vacancy;
import ru.job4j.vacanciesparser.properties.FileProperties;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class VacancyRepositoryTest {

    @Test
    public void whenStoreVacanciesShouldReturnSameAmountOfVacancies() {
        try (var connectionManager = new SqlConnectionManager(new FileProperties("app.properties"))) {
            var connectionRollback = new ConnectionRollback(connectionManager.getConnection());
            var connection = connectionRollback.getConnection();
            var repository = new SqlVacancyRepository(connection);
            repository.store(Set.of(
                    new Vacancy(1, "Java", "Text", "Url"),
                    new Vacancy(2, "Java 2", "Text 2", "Url 2")
            ));
            var list = repository.findAll();
            assertEquals(2, list.size());
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
