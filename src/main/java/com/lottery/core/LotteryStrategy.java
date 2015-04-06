package com.lottery.core;


import java.time.LocalDate;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
public interface LotteryStrategy {

    int calculateWinnings(int[] winningSeries, int[] registeredNumbers, LocalDate drawDate);

}
