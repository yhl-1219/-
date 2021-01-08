package com.itheima.health.utils.timer;

import java.util.Timer;
import java.util.TimerTask;

public class Demo {

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("----运行---");
            }
        },3000,1000);
    }
}
