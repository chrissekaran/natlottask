package com.lottery.core;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
public class LotteryException extends Exception {

    public LotteryException() {

    }

    public LotteryException(String message) {
        super(message);
    }

    public LotteryException(String message, Throwable cause) {
        super(message, cause);
    }
}
