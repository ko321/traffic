package com.traffic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2018/5/6
 * @since 1.0.0
 */
public class LampController {
    private Lamp currentLamp;
    public LampController(){
        currentLamp = Lamp.S2N;
        currentLamp.light();

        ScheduledExecutorService timer= Executors.newScheduledThreadPool(1);
        timer.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("来啊");
                        currentLamp = currentLamp.blackOut();
                    }
                },
                10,
                10,
                TimeUnit.SECONDS
        );
    }

}