package ru.job4j.vacanciesparser.dataprovider;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileDataProviderTest {
    @Test
    public void whenDataProviderShouldReturnFileContent() {
        var expected = "<html><body><div><span>text</span></div></body></html>";
        var result = new FileDataProvider().get("testdata.html");
        assertEquals(result, expected);
    }
}
