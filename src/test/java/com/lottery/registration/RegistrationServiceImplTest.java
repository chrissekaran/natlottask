package com.lottery.registration;

import com.lottery.core.monday.MondayLottery;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
public class RegistrationServiceImplTest {

    private RegistrationService registrationService;

    @Test(expected = RegistrationException.class)
    public void registerPlayerForDateFailsWhenWithinSixMonths() throws RegistrationException    {

        String formatedDate = "03/06/2014";

        registrationService = new RegistrationServiceImpl(MondayLottery.getInstance());

        try {
            registrationService.registerPlayerForDate(formatedDate);
            //Next line shouldn't be called
            assertTrue(false);
        } catch (RegistrationException e) {
            throw e;
        }
    }

    @Test
    public void registerPlayerForMondayLottery()    {

        String formattedDate = "03/08/2017";
        registrationService = new RegistrationServiceImpl(MondayLottery.getInstance());
        LotteryRegistration lotteryRegistration = null;
        try {
            lotteryRegistration = registrationService.registerPlayerForDate(formattedDate);
        } catch (RegistrationException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testRegistrationCreation() {
        LocalDate localDate = LocalDate.now();
        String formattedDate = "03/08/2017";
        registrationService = new RegistrationServiceImpl(MondayLottery.getInstance());
        LotteryRegistration lotteryRegistration = null;
        try {
            lotteryRegistration = registrationService.registerPlayerForDate(formattedDate);

        } catch (RegistrationException e) {
            fail("Don't Expect a Registration Exception here");
        }

        assertNotNull(lotteryRegistration);
    }

    @Test
    public void testRegistrationAndLottery()    {

        String formattedDate = "03/08/2035";
        registrationService = new RegistrationServiceImpl(MondayLottery.getInstance());
        LotteryRegistration lotteryRegistration = null;
        try {
            lotteryRegistration = registrationService.registerPlayerForDate(formattedDate);
            System.out.println("Registered Series: "+Arrays.toString(lotteryRegistration.getRandomSeries()));
            MondayLottery.getInstance().play();
        } catch (RegistrationException e) {
            fail("Don't Expect a Registration Exception here");
        }
    }

}
