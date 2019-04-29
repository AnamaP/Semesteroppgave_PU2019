package exceptions;

public class InvalidEmailFormatException extends Exception {
    public InvalidEmailFormatException(String msg) {
        super(msg);
    }
}
