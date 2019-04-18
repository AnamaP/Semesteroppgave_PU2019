package Exceptions;

public class InvalidEmailFormatException extends Exception {
    public InvalidEmailFormatException(String msg) {
        super(msg);
    }
}
