package com.lottery.registration;

import com.lottery.core.Lottery;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
public class LotteryRegistration {

    private final LocalDate finalLotteryDate;

    private final Lottery lottery;

    private final int[] randomSeries;

    public LotteryRegistration(LocalDate finalLotteryDate, Lottery lottery, int[] randomSeries) {
        this.finalLotteryDate = finalLotteryDate;
        this.lottery = lottery;
        this.randomSeries = randomSeries;
        lottery.observe(this);
    }

    public LocalDate getFinalLotteryDate() {
        return finalLotteryDate;
    }

    public int[] getRandomSeries() {
        return randomSeries.clone();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LotteryRegistration{");
        sb.append("finalLotteryDate=").append(finalLotteryDate);
        sb.append(", lottery=").append(lottery);
        sb.append(", randomSeries=").append(Arrays.toString(randomSeries));
        sb.append('}');

        return sb.toString();
    }


    public void notification(LocalDate drawDate, int[] clone, int sum) {
        System.out.println(String.format("%s ; %s ; %d ; ticket: %s", drawDate, Arrays.toString(clone), sum, Arrays.toString(randomSeries)));
    }
}
