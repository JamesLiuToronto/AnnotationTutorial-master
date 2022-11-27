package com.peak.annotationtutorial.lambda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author James Liu
 * @date 11/27/2022 -- 3:29 PM
 */
public class StreamExample {
    public static void main(String[] args) {
        rangeExample();
        convert();
        mapExample();

    }

    private static void rangeExample(){
        Stream<String> stringStream = Stream.of("James", "John", "Patrick", "Sheryl") ;
        stringStream.forEach(System.out::println);

        IntStream intStream1 = IntStream.range(2, 8).skip(2) ;
        IntStream intStream2 = IntStream.rangeClosed(2, 8) ;
        intStream1.forEach(System.out::println);
        intStream2.forEach(System.out::println);
    }

    private static void convert(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);

        list.stream().filter(p-> p> 20)
                .sorted((p1, p2) -> p2 - p1)
        .forEach(System.out::println);

        Set<Integer> set = new HashSet<Integer>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(30);
        set.add(40);
        set.add(50);
        set.add(60);

        IntStream intStream = set.stream()
                .filter(p-> p> 20)
                .mapToInt(Integer::intValue);

        OptionalInt oint= intStream.max();
        System.out.println("Max=" + oint.getAsInt());
        intStream = set.stream()
                .filter(p-> p> 20)
                .mapToInt(Integer::intValue);
        System.out.println("Min=" + intStream.min().getAsInt());

        intStream = set.stream()
                .filter(p-> p> 20)
                .mapToInt(Integer::intValue);
        System.out.println("Average=" + intStream.average().getAsDouble());

        intStream = set.stream()
                .filter(p-> p> 20)
                .mapToInt(Integer::intValue);
        System.out.println("Sum=" + intStream.sum());

    }

    public static void mapExample(){
        List<String> dateList = new ArrayList<>();
        dateList.add("2022-02-08");
        dateList.add("2022-03-08");
        dateList.add("2022-04-08");
        dateList.add("2022-05-08");
        dateList.add("2022-06-08");

        dateList.stream().map(StreamExample::getDateFromString)
                .filter(p->isWeekend(p))
                .forEach(System.out::println);

    }

    private static Date getDateFromString(String input){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            return format.parse(input) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null ;
    }

    private static boolean isWeekend(Date date){
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ;
    }



}

