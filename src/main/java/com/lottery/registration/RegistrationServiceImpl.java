package com.lottery.registration;


import com.lottery.core.Lottery;
import com.lottery.core.LuckyNumberGenerator;
import com.lottery.core.monday.MondayLottery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static java.lang.String.format;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
public class RegistrationServiceImpl implements RegistrationService {

    private Lottery lottery;

    private final LuckyNumberGenerator luckyNumberGenerator;

    public RegistrationServiceImpl(Lottery lottery) {
        this.lottery = lottery;
        luckyNumberGenerator = new LuckyNumberGenerator(6, 1, 60, false);
    }

    @Override
    public LotteryRegistration registerPlayerForDate(final String formattedDate) throws RegistrationException {

        LocalDate regDate = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if(regDate.isBefore(LocalDate.now().plusMonths(6)))   {
            throw new RegistrationException(format("Date cannot be within the next six months %s", formattedDate));
        }
        LotteryRegistration lotteryRegistration = new LotteryRegistration(regDate, MondayLottery.getInstance(), luckyNumberGenerator.generate());
        System.out.println("Registered Series: "+ Arrays.toString(lotteryRegistration.getRandomSeries()));
        return lotteryRegistration;
    }




}
