package com.peak.annotationtutorial.lambda;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author James Liu
 * @date 11/22/2022 -- 7:38 PM
 */
public class LambdaOne8 {
    public static void main(String[] args) {
        LambdaInterfaceOne printMe = s  -> System.out.println(s);

        printMe.print("this is a test");
        List<Person> people = Arrays.asList(
        new Person("James", "Liu", 50),
        new Person("Cheryl", "abc", 40) ,
        new Person("aaa", "cde", 15),
        new Person("bbb", "Liu", 10)) ;

        people.sort((o1, o2) -> o1.getLastName().toLowerCase().compareTo(o2.getLastName().toLowerCase()));

        printConditionly(people, p-> true) ;

        System.out.println("last name start with L");
        printConditionly(people,  p-> p.getLastName().startsWith("L"));

        System.out.println("first name start with J");
        performConditionly(people,  p-> p.getFirstName().startsWith("J"), p-> System.out.println(p.toString()));


    }

    private static void printConditionly(List<Person> people, Predicate<Person> predicate) {
        for (Person p:people){
            if (predicate.test(p)){
                System.out.println(p.toString());
            }

        }
    }

    private static void performConditionly(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person p:people){
            if (predicate.test(p)){
                consumer.accept(p);
            }

        }
    }


}
