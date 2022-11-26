package com.peak.annotationtutorial.lambda;

import java.util.function.Function;

/**
 * @author James Liu
 * @date 11/24/2022 -- 11:04 PM
 */
public class FunctionChainExample {
    public static void main(String[] args) {
        Function<String, Integer> numberOfString = s -> s.split(" ").length;

        Function<Integer, String> numberToString = input -> {
            switch (input){
                case 0:
                    return "Zero";
                case 1:
                    return "One";
                case 2:
                    return "Two" ;
                case 3:
                    return "Three" ;
                default:
                    return "too big" ;
            }

        };

        String result1 = numberOfString.andThen(numberToString).apply("this is example") ;
        System.out.println(result1);
        Integer result2 = numberOfString.compose(numberToString).apply(3) ;
        System.out.println(result2);

    }
}
