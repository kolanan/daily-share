package com.chinamobile.guava;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTest {


    public static void main(String[] args) {
        LocalDate of = LocalDate.of(2012, 5, 12);
        int year = of.getYear();
        System.out.println(of);
        System.out.println(year);
        System.out.println(of.getMonth());
        System.out.println(of.getDayOfMonth());
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());

        // 秒级时间差
        System.out.println(Duration.between(LocalDateTime.now(), LocalDateTime.now()).getSeconds());

    }
}
