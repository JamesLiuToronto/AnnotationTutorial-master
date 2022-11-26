package com.peak.annotationtutorial.lambda;

import java.util.function.BiConsumer;

/**
 * @author James Liu
 * @date 11/23/2022 -- 6:42 PM
 */
public class ExceptionHandler {
    public static void main(String[] args) {
        int[] numbers = {1,2,3,4} ;
        int key = 0 ;
        process(numbers, key, (v, k) -> System.out.println(v+k)) ;
        System.out.println("------------------");
        process(numbers, key, (v, k) -> System.out.println(v*k)) ;
        System.out.println("------------------");
        process(numbers, key, wrapper((v, k) -> System.out.println(v/k)));
    }

    private static void process(int[] numbers, int key, BiConsumer<Integer, Integer> consumer) {

        for (Integer i: numbers) {
            consumer.accept(i, key);
        }
    }

    private static BiConsumer<Integer, Integer> wrapper(BiConsumer<Integer, Integer> consumer){
        return (k,v) -> {
            try {
                consumer.accept(k,v);
            } catch (Exception e){
                System.out.println("Exception aaa" + e.getMessage());
            }

        };
    }


}
