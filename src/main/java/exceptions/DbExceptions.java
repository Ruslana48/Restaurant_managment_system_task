package exceptions;

public class DbExceptions extends Exception{
    public DbExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public DbExceptions(String message) {
        super(message);
    }
}
