package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        Pizzeria pizzeria = new Pizzeria();
//        pizzeria.order("Margarita");
//        Thread.sleep(100);
//        pizzeria.order("Pepperoni");
//        Thread.sleep(100);
//        pizzeria.order("Sicilian");
//        Thread.sleep(100);
//        pizzeria.order("Greek");
        Solution solution = new Solution();
        int [] a = {-1,0,1,2,-1,-4};
        List<List<Integer>> res = solution.threeSum(a);
        for (List<Integer> list : res){
            for(int line : list){
                System.out.print(line + " ");
            }
            System.out.println();
        }
    }
}
