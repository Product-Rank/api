package com.productrank.api.domain.entity.enums;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public enum RankingType {

    YEARLY, MONTHLY, WEEKLY, DAILY;
    public List<String> getStartAndEndDay(){
        return switch(this){
            case YEARLY -> getYears();
            case MONTHLY -> getMonths();
            case WEEKLY -> getWeeks();
            case DAILY -> getDays();
        };
    }

    private List<String> getDays() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return List.of(date.format(sdf));
    }

    private List<String> getWeeks() {
        LocalDate date = LocalDate.now();
        LocalDate firstDayOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastDayOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return List.of(firstDayOfWeek.format(sdf), lastDayOfWeek.format(sdf));
    }
    private List<String> getMonths(){
        LocalDate date = LocalDate.now();
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        // 이번 달의 마지막날 구하기
        YearMonth yearMonth = YearMonth.from(date);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return List.of(firstDayOfMonth.format(sdf), lastDayOfMonth.format(sdf));
    }
    public List<String> getYears(){
        LocalDate date = LocalDate.now();
        LocalDate firstDayOfYear = date.withDayOfYear(1);
        // 올해의 마지막날 구하기
        LocalDate lastDayOfYear = date.withDayOfYear(365);

        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return List.of(firstDayOfYear.format(sdf), lastDayOfYear.format(sdf));
    }

}