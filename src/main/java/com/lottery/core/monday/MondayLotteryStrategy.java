package com.lottery.core.monday;

import com.lottery.core.LotteryStrategy;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
class MondayLotteryStrategy implements LotteryStrategy {

    public int calculateWinnings(final int[] winningSeries, final int[] registeredNumbers, LocalDate drawDate) {

        int[] commonNumbers = commonNumbers(winningSeries, registeredNumbers);
        int[] uncommonNumbers = uncommonNumbers(winningSeries, commonNumbers);
        Arrays.sort(uncommonNumbers);

        //If guessed less than 3 numbers win sum of numbers drawn
        if(commonNumbers.length < 3)    {
            int winningsAmount = sumOfArrayElements(winningSeries);

            return computeFactorForSpecialConditions(drawDate, winningsAmount);
        }

        //if guessed numbers are equal or more than 3
        if(commonNumbers.length >= 3 && commonNumbers.length != 6)   {
            int winningsAmount = 0;
            winningsAmount = commonNumbers.length * 1000;
            int missedNumbersProduct = 1;

            if(uncommonNumbers.length > 0)  {
                for (int uncommonNumber : uncommonNumbers) {
                    missedNumbersProduct *= uncommonNumber;
                }
            }
            if(uncommonNumbers.length > 0)  {
                winningsAmount += missedNumbersProduct;
            }
            return computeFactorForSpecialConditions(drawDate, winningsAmount);

        }

        //if guessed all 6 numbers then win = 10000 * each number
        if(commonNumbers.length == 6)   {
            int winningsAmount = sumOfArrayElements(winningSeries);
            winningsAmount *=10000;
            return computeFactorForSpecialConditions(drawDate, winningsAmount);
        }
        return 0;
    }

    private int sumOfArrayElements(int[] winningSeries) {
        int winningsAmount = 0;
        for (int win : winningSeries) {
            winningsAmount+=win;
        }
        return winningsAmount;
    }

    private int computeFactorForSpecialConditions(LocalDate drawDate, int winningsAmount) {

        //if month of feb then double
        if(drawDate.isLeapYear()) {
            if(drawDate.getDayOfMonth()==29)  {
                winningsAmount *=3;
            }   else    {
                winningsAmount *=2;
            }
        }
        return winningsAmount;
    }

    public final int[] uncommonNumbers(int[] winningSeries, int[] commonNumbers) {

        Arrays.sort(commonNumbers);
        int[] uncommon = new int[winningSeries.length - commonNumbers.length];
        int idx = 0;
        for (int win : winningSeries) {
            //Not very efficient
            int search = Arrays.binarySearch(commonNumbers, win);
            if(search < 0)   {
                uncommon[idx] = win;
                idx++;
            }
        }
        return uncommon;
    }

    public final int[] commonNumbers(int[] winningSeries, int[] registeredNumbers) {
        int[] temp = new int[registeredNumbers.length];
        int i = 0;
        Arrays.sort(registeredNumbers);
        for (int win : winningSeries) {
            if(Arrays.binarySearch(registeredNumbers, win) >= 0)    {
                temp[i] = win;
                i++;
            }
        }
        //Now i = length of temp
        int[] common = new int[i];
        common = Arrays.copyOf(temp, i);
        return common;
    }


}