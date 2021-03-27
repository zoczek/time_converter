package main;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}

class NoSuchTimezoneException extends Exception {
    public NoSuchTimezoneException(String message) {
        super(message);
    }
}

class InvalidNumberOfArguments extends Exception{
    public InvalidNumberOfArguments(String message) {
        super(message);
    }
}