package test;

import main.CommandLineInputParser;
import main.CommandLineOutputTimeWriter;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineOutputTimeWriterTest {
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
    void writeOutput() {
        LocalTime time = LocalTime.of(15, 30);
        String expected = time.toString() + "\n";

        CommandLineOutputTimeWriter writer = new CommandLineOutputTimeWriter();
        writer.writeOutput(time);

        assertEquals(expected, testOutStream.toString());
    }
}