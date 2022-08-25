package learn.solar.ui;

public class InvalidMenuChoiceException extends Exception{

    public InvalidMenuChoiceException(String message){
        super(message);
    }

    public InvalidMenuChoiceException(String message, Throwable innerException){
        super( message, innerException);
    }
}
