package Exceptions;

public class InvalidAgeFormatException extends Exception{
    public InvalidAgeFormatException(String msg) {
        super(msg);
    }
}