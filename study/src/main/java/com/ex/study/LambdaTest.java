package com.ex.study;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class LambdaTest {
    public static void main(String[] args) {
        MyInterface consumer = (s) -> System.out.println(s);
//        List<Person> people = Arrays.asList(
//                new Person("san", "zhang", 25),
//                new Person("si","li",28),
//                new Person("wu","wang",18));
//        people.stream().filter(s -> s.getLastName().startsWith("z"))
//        .forEach(System.out::println);
    }
    void numberTest(){
        //        Short num =(short)Math.abs(Short.MIN_VALUE);
//        System.out.println(num);
//        double i = Double.NaN;
//        System.out.println(i != i);
//        double infinity = Double.POSITIVE_INFINITY;
//        System.out.println(infinity==infinity+1);
    }
}


@AllArgsConstructor
class Person{
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private int age;
}

@FunctionalInterface
interface MyInterface{
    void doSomeShit(String s);
//    void nothing(String s);
}