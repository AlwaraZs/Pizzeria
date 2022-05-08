package com.company;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;


public class Pizzeria {
    private LinkedBlockingDeque<Pizza> queue = new LinkedBlockingDeque<>();
    private final long timeStartDay;
    ArrayList<Courier> couriers = new ArrayList<>();

    Pizzeria(){
        couriers.add(new Courier());
        couriers.add(new Courier());
        timeStartDay = System.currentTimeMillis();
        new WorkDay().start();
    }

    private class WorkDay extends Thread{
        @Override
        public void run(){
            for (Courier courier : couriers) {
                courier.start();
            }
            while (System.currentTimeMillis() - timeStartDay < 5000) {
                try {
                    sleep(500);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            for (Courier courier : couriers) {
                courier.interrupt();
            }
            for (Courier courier : couriers) {
                try {
                    courier.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    class Courier extends Thread{
        @Override
        public void run(){
            while (!isInterrupted()){
                if(!Pizzeria.this.queue.isEmpty()){
                    try {
                        Pizza pizza = Pizzeria.this.queue.pollFirst(500, TimeUnit.MILLISECONDS);
                        long timeStartCook = System.currentTimeMillis();
                        if (timeStartCook + 500 - pizza.timeOrder > 750){
                            System.out.println(pizza.name + " is NOT delivered");
                            continue;
                        }else {
                            sleep(500 - (System.currentTimeMillis() - timeStartCook));
                            System.out.println(pizza.name + " is delivered");
                        }
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }
    }

    class Pizza{
        String name;
        volatile long timeOrder;
        Pizza(String name, long timeOrder){
            this.name = name;
            this.timeOrder = timeOrder;
        }
    }

    void order(String pizzaName){
        if(System.currentTimeMillis() - timeStartDay < 5000) {
            try {
                queue.offerLast(new Pizza(pizzaName, System.currentTimeMillis()), 500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }// you can't order
    }
}
