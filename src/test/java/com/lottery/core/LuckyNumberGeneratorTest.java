package com.lottery.core;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
public class LuckyNumberGeneratorTest {

    private LuckyNumberGenerator luckyNumberGenerator;

    @Test
    public void testSizeOfSeries() {
        luckyNumberGenerator = new LuckyNumberGenerator(6, 1, 60, false);
        int[] luckyNums =luckyNumberGenerator.generate();
        assertEquals(luckyNums.length, 6);
    }


    @Test
    public void testRangeOfRandomValues()   {
        luckyNumberGenerator = new LuckyNumberGenerator(6, 1, 60, false);
        for(int i = 0; i<= 2000; i++) {
            int[] luckyNums =luckyNumberGenerator.generate();
            for (int luckyNum : luckyNums) {
                assertTrue((luckyNum >= 1 && luckyNum <= 60));
            }

        }
    }





}
