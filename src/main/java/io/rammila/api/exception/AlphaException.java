package io.rammila.api.exception;

public class AlphaException extends RuntimeException{
    private static final long serialVersionUID = 2771174581631905388L;


    public AlphaException() {
    }

    public AlphaException(String message) {
        super(message);
    }

    public AlphaException(Throwable cause) {
        super(cause);
    }

    public AlphaException(String message, Throwable cause) {
        super(message, cause);
    }


}
