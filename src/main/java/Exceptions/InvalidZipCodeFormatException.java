package Exceptions;

public class InvalidZipCodeFormatException extends Exception{
    public InvalidZipCodeFormatException(String msg) {
        super(msg);
    }
}