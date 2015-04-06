package com.lottery;

import com.lottery.core.monday.MondayLottery;
import com.lottery.registration.LotteryRegistration;
import com.lottery.registration.RegistrationException;
import com.lottery.registration.RegistrationService;
import com.lottery.registration.RegistrationServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class App {

    private static RegistrationService registrationService;

    public static void main(String [] args) {

        Set<String> dates = new HashSet() {{
            add("04/05/2016");
            add("03/08/2020");
            add("09/07/2015");
        }};

        registrationService = new RegistrationServiceImpl(MondayLottery.getInstance());
        LotteryRegistration lotteryRegistration = null;
        try {
            dates.stream()
                    .filter(d -> LocalDate.parse(d, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            .isAfter(LocalDate.now().plusMonths(6)))
                    .forEach(registrationService::registerPlayerForDate);
            //System.out.println("Registered Series: "+ Arrays.toString(lotteryRegistration.getRandomSeries()));
            MondayLottery.getInstance().play();
        } catch (RegistrationException e) {
            e.printStackTrace();
        }
    }
}
