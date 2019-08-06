package com.ex.study;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LambdaTest {
    public static void main(String[] args) {
//        MyInterface consumer = (s) -> System.out.println(s);
        List<Person> people = Arrays.asList(
                new Person("san", "zhang", 25),
                new Person("si","li",28),
                new Person("wu","wang",18));
        people.stream().filter(s -> s.getLastName().startsWith("z"))
        .forEach(System.out::println);

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