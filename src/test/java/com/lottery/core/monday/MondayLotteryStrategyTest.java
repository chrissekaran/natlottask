package com.lottery.core.monday;

import com.lottery.core.LuckyNumberGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Chris Sekaran on 2/25/14.
 */
public class MondayLotteryStrategyTest {

    private MondayLotteryStrategy mondayLotteryStrategy = new MondayLotteryStrategy();

    private volatile LuckyNumberGenerator luckyNumberGenerator = new LuckyNumberGenerator(10, 10, 90, false);

    @Test
    public void commonNumbers() {

        ExecutorService exec = Executors.newFixedThreadPool(30);

        Collection<Callable<Integer>> calls = new ArrayList<Callable<Integer>>();
        for(int i =1; i <= 30; i++) {
            calls.add(new Callable() {

                @Override
                public Integer call() throws Exception {
                    int[] sumA =mondayLotteryStrategy.uncommonNumbers(luckyNumberGenerator.generate(), luckyNumberGenerator.generate());
                    int sum =0;
                    for(int i : sumA)    {
                        sum+=i;
                    }
                    return sum;
                }

            });
        }

        try {
            List<Future<Integer>> callResults = exec.invokeAll(calls);
            int i =0;
            for(Callable call: calls)   {
                //callResults.get(i).get().equals(call.call());
                System.out.println(i+": "+call.call());
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        exec.shutdown();

    }


}
