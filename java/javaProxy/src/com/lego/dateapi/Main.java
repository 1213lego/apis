package com.lego.dateapi;

import java.time.*;
import java.util.Date;

public class Main {
    public static void testInstant() {
        System.out.println("------------testInstant-------------");
        Instant now = Instant.now();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(now, end).getSeconds());
        System.out.println(Duration.between(now, end).getNano());
    }

    public static void localDateTest() {
        System.out.println("---------localDateTest----------------");
        LocalDate finAño = LocalDate.of(2020, 12, 31);
        LocalDate navidad = finAño.minusDays(6);
        System.out.println(navidad.getDayOfMonth());
        System.out.println(navidad.getMonth());
        Period period = Period.between(navidad, finAño);
        System.out.printf("Periodo %s y %s"
                        + " hay %d años, %d meses"
                        + " y %d dias%n", navidad, finAño,
                period.getYears(),
                period.getMonths(),
                period.getDays());
    }

    public static void localDateTimeTest() {
        System.out.println("-----------------locatDateTimeTest---------------------------");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
    }

    public static void localTimeTest() {
        System.out.println("---------------localTimeTest--------------------");
        //Current Time
        LocalTime time = LocalTime.now();
        System.out.println("Current Time=" + time);
        LocalTime timeKolkata = LocalTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current Time in IST=" + timeKolkata);
    }

    public LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public Date convertToDate(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public Date convertToDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault())
                .toInstant());
    }

    public static void main(String[] args) {
        testInstant();
        localDateTest();
        localDateTimeTest();
        localTimeTest();
    }
}
