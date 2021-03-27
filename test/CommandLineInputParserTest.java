package test;

import main.CommandLineInputParser;
import main.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineInputParserTest {
    Set<String> supportedTimeZones = Set.of("GMT", "PDT");

    @Test
    void CommandLineInputParserConstructor(){
        String[] validInput = {"1", "2", "3"};

        try {
            new CommandLineInputParser(validInput, supportedTimeZones);
        } catch (Exception e){
            fail();
        };

        assertThrows(InvalidInputException.class, () -> {
            String[] invalidInput = {"1", "2"};
            new CommandLineInputParser(invalidInput, supportedTimeZones);
        });
    }

    @Test
    void getTime() {
        String[] validTimeInput = {"05:34:56", "2", "3"};
        try{
            CommandLineInputParser inputParser = new CommandLineInputParser(validTimeInput, supportedTimeZones);
            assertEquals(inputParser.parseTime(), LocalTime.parse(validTimeInput[0], DateTimeFormatter.ISO_LOCAL_TIME));
        }catch (InvalidInputException e){
            fail();
        }

        String[] invalidTimeInput = {"67:00:00", "2", "3"};
        assertThrows(InvalidInputException.class, () -> {
            CommandLineInputParser inputParser = new CommandLineInputParser(invalidTimeInput, supportedTimeZones);
            inputParser.parseTime();
        });

    }

    @Test
    void getTimeZone() {
         String[] validInput = {"1", "GMT", "PDT"};

         try{
            CommandLineInputParser inputParser = new CommandLineInputParser(validInput, supportedTimeZones);
            assertEquals(validInput[1], inputParser.parseOriginalTimeZone());
            assertEquals(validInput[2], inputParser.parseTargetTimeZone());
         } catch (InvalidInputException e) {
             fail();
         }

         String[] invalidInput = {"1", "2", "3"};

         assertThrows(InvalidInputException.class, () -> {
            CommandLineInputParser inputParser = new CommandLineInputParser(invalidInput, supportedTimeZones);
            inputParser.parseOriginalTimeZone();
         });

        assertThrows(InvalidInputException.class, () -> {
            CommandLineInputParser inputParser = new CommandLineInputParser(invalidInput, supportedTimeZones);
            inputParser.parseTargetTimeZone();
        });
    }
}