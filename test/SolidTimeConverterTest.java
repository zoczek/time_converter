package test;

import main.InvalidInputException;
import main.SolidTimeConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SolidTimeConverterTest {
    private final ByteArrayOutputStream testOutStream = new ByteArrayOutputStream();
    private final PrintStream originalOutStream = System.out;

    @BeforeEach
    public void setStream(){
        System.setOut(new PrintStream(testOutStream));
    }

    @AfterEach
    public void restoreStream(){
        System.setOut(originalOutStream);
    }

    @Test
    void mainValidInput() {
        String[] validInput = {"05:56:23", "GMT", "PDT"};
        String expectedOutput = "22:56:23\n";

        SolidTimeConverter.main(validInput);
        assertEquals(expectedOutput, testOutStream.toString());
    }

    @Test
    void mainInvalidArgumentCount(){
        String[] input = {"1", "2"};
        String expectedOutput = "Wrong number of input arguments. 3 arguments expected.\n";
        SolidTimeConverter.main(input);
        assertEquals(expectedOutput, testOutStream.toString());
    }

    @Test
    void mainInvalidTime(){
        String[] input = {"33:33:33", "GMT", "PDT"};
        String expectedOutput = "Invalid time format. Text '33:33:33' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 33\n";

        SolidTimeConverter.main(input);
        assertEquals(expectedOutput, testOutStream.toString());
    }

    @Test
    void mainInvalidTimeZone(){
        String[] input = {"15:30:00", "ABC", "PDT"};
        String expectedOutput = "Invalid time zone. No time zone ABC.\n";

        SolidTimeConverter.main(input);
        assertEquals(expectedOutput, testOutStream.toString());
    }
}