package com.lottery.core.monday;

import com.lottery.core.Lottery;
import com.lottery.core.LuckyNumberGenerator;
import com.lottery.registration.LotteryRegistration;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
public final class MondayLottery implements Lottery {

    private static final List<LotteryRegistration> lotteryRegistrations = new ArrayList<LotteryRegistration>();

    private static final Lottery instance = new MondayLottery();

    //@GuardedBy("this") 
    private LocalDate drawDate;

    //@GuardedBy("this") 
    private int[] drawNumbers;

    private LuckyNumberGenerator luckyNumberGenerator;

    private volatile MondayLotteryStrategy lotteryStrategy;

    private LocalDate maxDate;

    private MondayLottery() {
        //Since MondayLottery is single and loaded statically
        drawDate = findNextDrawDate(LocalDate.now());
        luckyNumberGenerator =  new LuckyNumberGenerator(6, 1, 60, false);
        lotteryStrategy = new MondayLotteryStrategy();
    }

    private LocalDate findNextDrawDate(LocalDate now) {

        //Find the next Monday or sets today as drawDate if today is Monday
        if(now.getDayOfWeek() == DayOfWeek.MONDAY) {
            drawDate = now;
        } else  {
            drawDate = now.plusDays(8-now.getDayOfWeek().getValue());
        }
        maxDate = drawDate;
        return drawDate;
    }

    public static Lottery getInstance() {
        return instance;
    }

    @Override
    public void play() {
        while(drawDate.isBefore(maxDate))   {
            synchronized (this) {
                drawNumbers = luckyNumberGenerator.generate();
                notifyRegistrants();
                drawDate = drawDate.plusWeeks(1);
            }

        }
    }

    private void notifyRegistrants() {
        lotteryRegistrations.forEach(this::notifyRegistrant);
    }

    public void notifyRegistrant(LotteryRegistration lotteryRegistration) {
        int[] drawNumbersLocal = drawNumbers.clone();
        LocalDate drawDateLocal = drawDate;
        int sum = lotteryStrategy.calculateWinnings(drawNumbersLocal, lotteryRegistration.getRandomSeries(), drawDateLocal);
        lotteryRegistration.notification(drawDate, drawNumbers, sum);
    }

    @Override
    public void observe(LotteryRegistration registration) {
        lotteryRegistrations.add(registration);
        if(registration.getFinalLotteryDate().isAfter(maxDate)) {
            maxDate = registration.getFinalLotteryDate();
        }
    }

}