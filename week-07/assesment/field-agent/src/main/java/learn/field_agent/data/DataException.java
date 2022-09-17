package learn.field_agent.data;

public class DataException extends Exception {
    public DataException(String message){
        super(message);
    }

    public DataException( String message, Throwable innerException ){
        super( message, innerException);
    }
}
