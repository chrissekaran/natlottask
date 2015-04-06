package com.lottery.core;

import com.lottery.registration.LotteryRegistration;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
public interface Lottery {

    void play();

    void observe(LotteryRegistration registration);
}
