package main;

import java.time.LocalTime;

public class SolidTimeConverter {
    private static final TimeConverter converter = new TimeConverter();
    private static final OutputTimeWriter outputWriter = new CommandLineOutputTimeWriter();

    public static void main(String[] args) {
        try{
            InputParser inputParser = new CommandLineInputParser(args, converter.getSupportedTimeZones());
            LocalTime time = inputParser.parseTime();
            String timeZoneFrom = inputParser.parseOriginalTimeZone();
            String timeZoneTo = inputParser.parseTargetTimeZone();

            LocalTime convertedTime = converter.convertTime(time, timeZoneFrom, timeZoneTo);

            outputWriter.writeOutput(convertedTime);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage() + " " +  e.getCause().getMessage());
        }
    }
}

