package dtu.whitebox;

import org.junit.jupiter.api.Test;

import dtu.superPlanner.FileEmployeeRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LoadEmployeeTest {

    @Test
    // Set A
    void testThrowExceptionInputIsNull() throws IOException { // thanks baeldung
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FileEmployeeRepository(null);
        });
        String expected = "Input file was null";
        String actual = exception.getMessage();

        assertEquals(expected, actual);

    }

    @Test
    // Set B
    void testThrowExceptionInputIsBroken() throws IOException {
        InputStream brokenInput = mock(InputStream.class);
        Exception exception = assertThrows(IOException.class, () -> {
            new FileEmployeeRepository(brokenInput);
        });

        String expected = "Failed reading input file";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    // Set C
    void testReturnsEmptyString() throws IOException {
        String emptyInput = "";
        InputStream input = new ByteArrayInputStream(emptyInput.getBytes(StandardCharsets.UTF_8));

        FileEmployeeRepository fileEmployeeRepository = new FileEmployeeRepository(input);

        int expected = 0;
        int actual = fileEmployeeRepository.getAllEmployees().size();

        assertEquals(expected, actual);
        assertFalse(fileEmployeeRepository.getSkippedLines());
    }

    @Test
    // Set D
    void testReturnsOneLine() throws IOException {
        String oneLine = "huba";
        InputStream input = new ByteArrayInputStream(oneLine.getBytes(StandardCharsets.UTF_8));
        FileEmployeeRepository fileEmployeeRepository = new FileEmployeeRepository(input);

        int expected = 1;
        int actual = fileEmployeeRepository.getAllEmployees().size();

        assertEquals(expected, actual);
        assertTrue(fileEmployeeRepository.contains("huba"));
        assertFalse(fileEmployeeRepository.getSkippedLines());

    }

    @Test
    // Set E
    void testReturnsEmptyStringAndTrue() throws IOException {
        String oneLine = "hubau";
        InputStream input = new ByteArrayInputStream(oneLine.getBytes(StandardCharsets.UTF_8));
        FileEmployeeRepository fileEmployeeRepository = new FileEmployeeRepository(input);

        int expected = 0;
        int actual = fileEmployeeRepository.getAllEmployees().size();

        assertEquals(expected, actual);
        assertTrue(fileEmployeeRepository.getSkippedLines());
    }

    @Test
    // Set F
    void testReturnsFullListAndFalse() throws IOException {
        String manyLines = "huba \n anda";
        InputStream input = new ByteArrayInputStream(manyLines.getBytes(StandardCharsets.UTF_8));
        FileEmployeeRepository fileEmployeeRepository = new FileEmployeeRepository(input);

        int expected = 2;
        int actual = fileEmployeeRepository.getAllEmployees().size();

        assertEquals(expected, actual);
        assertTrue(fileEmployeeRepository.contains("huba"));
        assertTrue(fileEmployeeRepository.contains("anda"));
        assertFalse(fileEmployeeRepository.getSkippedLines());
    }

    @Test
    // Set G
    void testReturnsFullListAndTrue() throws IOException {
        String manyLinesTrue = "hubau \n anda";
        InputStream input = new ByteArrayInputStream(manyLinesTrue.getBytes(StandardCharsets.UTF_8));
        FileEmployeeRepository fileEmployeeRepository = new FileEmployeeRepository(input);

        int expected = 1;
        int actual = fileEmployeeRepository.getAllEmployees().size();

        assertEquals(expected, actual);
        assertFalse(fileEmployeeRepository.contains("hubau"));
        assertTrue(fileEmployeeRepository.contains("anda"));
        assertTrue(fileEmployeeRepository.getSkippedLines());
    }

}
