package com.lottery.core;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
public class LuckyNumberGenerator {

    private final int sizeOfNumberSeries;

    private final int low;

    private final int high;

    public LuckyNumberGenerator(int sizeOfNumberSeries, int low, int high, boolean distinct) {
        this.sizeOfNumberSeries = sizeOfNumberSeries;
        this.low = low;
        this.high = high;
    }

    public int[] generate()    {
        int[] luckyNos = new int[sizeOfNumberSeries];
        Random random = new Random();
        for(int i = 0; i < sizeOfNumberSeries; i++) {
            int temp = 0;
            int search = 0;
            while(search >= 0) {
                temp = random.nextInt(high - low) + low +1;
                Arrays.sort(luckyNos);
                search = Arrays.binarySearch(luckyNos, temp);
            }
            luckyNos[sizeOfNumberSeries - i -1] = temp;
        }
        return luckyNos;
    }
}
