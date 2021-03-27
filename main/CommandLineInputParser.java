package main;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

public class CommandLineInputParser implements InputParser {
    private final String[] args;
    private final Set<String> supportedTimeZones;
    private final int inputArgumentCount = 3;

    public CommandLineInputParser(String[] args, Set<String> supportedTimeZones) throws InvalidInputException {
        if (!isNumberOfArgumentsValid(args)){
            throw new InvalidInputException("Wrong number of input arguments.", new InvalidNumberOfArguments(inputArgumentCount + " arguments expected."));
        }
        this.supportedTimeZones = supportedTimeZones;
        this.args = args;
    }

    @Override
    public LocalTime parseTime() throws InvalidInputException {
        LocalTime time;
        try{
            time = LocalTime.parse(args[0], DateTimeFormatter.ISO_LOCAL_TIME);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid time format.", e);
        }
        return time;
    }

    @Override
    public String parseOriginalTimeZone() throws InvalidInputException {
        if (!isTimeZoneValid(args[1]))
            throw new InvalidInputException("Invalid time zone.", new NoSuchTimezoneException("No time zone " + args[1] + "."));
        return args[1];
    }

    @Override
    public String parseTargetTimeZone() throws  InvalidInputException{
        if (!isTimeZoneValid(args[2])){
            throw new InvalidInputException("Invalid time zone.", new NoSuchTimezoneException("No time zone " + args[2]));
        }
        return args[2];
    }

    private boolean isTimeZoneValid(String timeZone) {
        return supportedTimeZones.contains(timeZone);
    }

    private boolean isNumberOfArgumentsValid(String[] args) {
        return args.length == inputArgumentCount;
    }
}


