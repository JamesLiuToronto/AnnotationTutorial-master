package com.peak.annotationtutorial.lambda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author James Liu
 * @date 11/27/2022 -- 3:29 PM
 */
public class StreamExample2 {
    public static void main(String[] args) {
         //filterExample();
        collectorExample();
    }


    public static void filterExample(){

        List<Player> players = new ArrayList<Player>() ;
        players.add(new Player(1,2,3,4));
        players.add(new Player(0,10,0,20));
        players.add(new Player(3,5,6,3));
        players.add(new Player(8,0,10,4));
        players.add(new Player(1,0,1,8));

        players.stream()
                .filter(p->p.getAssist()>2)
                .filter(p->p.getMatch()>3)
                .forEach(p->System.out.println("match " + p.getMatch()));

        int totalMatches = players.stream().collect(Collectors.summingInt(p->p.getMatch()));
        System.out.println("total matches=" + totalMatches);

    }

    public static void collectorExample(){
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(5000, "Admin", "Joe"));
        employees.add(new Employee(4000, "Admin", "John"));
        employees.add(new Employee(3000, "IT", "Dave"));
        employees.add(new Employee(2000, "IT", "Steven"));

        Set<Employee> set = employees.stream().collect(Collectors.toSet());
        set.stream().forEach(System.out::println);

        employees.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

        employees.stream().map(Employee::getDepartment).collect(Collectors.toCollection(HashSet::new)).forEach(System.out::println);

        Map<String, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment)) ;

        Map<String, Double> map2 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary))) ;

        System.out.println(map2);

        Map<String, Double> map3 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary))) ;

        System.out.println(map3);


    }





}

