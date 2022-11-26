package com.peak.annotationtutorial.lambda;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author James Liu
 * @date 11/26/2022 -- 1:24 PM
 */
public class OptionalExample {
    public static void main(String[] args) {
        Function<String, String> secondWord = s-> s.split(" ").length > 1? s.split(" ")[1]:null;

        Function<String, Integer> countWord = String::length;
        Optional<String> myOptional = Optional.ofNullable(secondWord.apply("whatisthis"));

        if (myOptional.isPresent()){
            myOptional.ifPresent(System.out::print);
        }else {
           System.out.println(myOptional.orElse("null value")) ;
        }

        Optional.ofNullable(secondWord.apply("whatisthis")).map(countWord).ifPresent(System.out::print);
    }
}
