package com.lottery.registration;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
public class RegistrationException extends RuntimeException {

    public RegistrationException()  {

    }

    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String message, Throwable throwable)   {
        super(message, throwable);
    }

}
