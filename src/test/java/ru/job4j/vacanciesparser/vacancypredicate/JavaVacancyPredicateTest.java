package ru.job4j.vacanciesparser.vacancypredicate;

import org.junit.Test;
import ru.job4j.vacanciesparser.predicate.JavaVacancyPredicate;

import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JavaVacancyPredicateTest {

    private Predicate<String> predicate = new JavaVacancyPredicate();

    @Test
    public void whenVacancyTitleHaveNoWordJavaThanPredicateShouldReturnFalse() {
        assertFalse(predicate.test("DevOps is needed."));
    }

    @Test
    public void whenVacancyTitleHaveWordJavaThanPredicateShouldReturnTrue() {
        assertTrue(predicate.test("Java developer is needed."));
    }

    @Test
    public void whenVacancyTitleHaveWordJavaScriptThanPredicateShouldReturnFalse() {
        assertFalse(predicate.test("JavaScript developer is needed."));
    }

    @Test
    public void whenVacancyTitleHaveWordJavaSpaceScriptThanPredicateShouldReturnFalse() {
        assertFalse(predicate.test("Java Script developer is needed."));
    }
}
