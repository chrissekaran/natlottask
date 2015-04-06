package com.lottery.registration;


/**
 * Created by Chris Sekaran on 2/25/14.
 */
public interface RegistrationService {


    LotteryRegistration registerPlayerForDate(String formattedDate) throws RegistrationException;


}
