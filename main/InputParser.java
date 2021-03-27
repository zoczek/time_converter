package main;

import java.time.LocalTime;

public interface InputParser {
    LocalTime parseTime() throws InvalidInputException;
    String parseOriginalTimeZone() throws InvalidInputException;
    String parseTargetTimeZone() throws InvalidInputException;
}
