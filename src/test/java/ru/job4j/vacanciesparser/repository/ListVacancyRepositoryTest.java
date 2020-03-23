package ru.job4j.vacanciesparser.repository;

import org.junit.Test;
import ru.job4j.vacanciesparser.entity.Vacancy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ListVacancyRepositoryTest {

    @Test
    public void whenStoreListThanFindAllShouldReturnSameList() {
        var expected = Set.of(
                new Vacancy(1, "Java 1", "Java 1", ""),
                new Vacancy(2, "Java 2", "Java 2", ""));
        var repository = new ListVacancyRepository();
        repository.store(expected);
        var result = repository.findAll();
        assertEquals(new ArrayList<>(expected), result);
    }
}
