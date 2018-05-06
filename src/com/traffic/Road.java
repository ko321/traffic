package com.traffic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 车开到路上的代码
 *
 * @author Administrator
 * @create 2018/5/6
 * @since 1.0.0
 */
public class Road {
    private List<String> vechicles = new ArrayList<String >();
    private String name = null;
    public Road(String name){
        this.name = name;

        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new Runnable(){                    //随机生成车辆
            public void run(){
                for(int i = 0; i<1000; i++){
                    try {
                        Thread.sleep((new Random().nextInt(10)+1) * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    vechicles.add(Road.this.name + "_" + i );
                }
            }
        });

        ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);//定时器
        timer.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        if(vechicles.size()>0){
                            boolean lighted = Lamp.valueOf(Road.this.name).isLighted();
                            if(lighted){
                                System.out.println(vechicles.remove(0) + "is traversing!");
                            }
                        }
                    }
                },
                1,
                1,
                TimeUnit.SECONDS
        );
    }

}