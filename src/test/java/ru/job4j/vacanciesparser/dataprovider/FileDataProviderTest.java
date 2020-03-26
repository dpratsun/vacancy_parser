package ru.job4j.vacanciesparser.dataprovider;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileDataProviderTest {
    @Test
    public void whenDataProviderShouldReturnFileContent() {
        var expected = "<html><body><div><span>text</span></div></body></html>";
        var result = new FileDataProvider().get("testdata.html");
        assertEquals(result, expected);
    }

    @Test
    public void whenFileNotPresentDataProviderShouldReturnEmptyString() {
        var result = new FileDataProvider().get("notExist.html");
        assertTrue(result.isEmpty());
    }
}
