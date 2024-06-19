package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private DateTimeUtil() {
    }

    public static boolean isLocalTimeWithinRange(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return !lt.isBefore(startTime) && lt.isBefore(endTime);
    }

    public static boolean isDateTimeWithinRange(LocalDateTime ldt, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return !ldt.isBefore(LocalDateTime.of(startDate, startTime)) && ldt.isBefore(LocalDateTime.of(endDate, endTime));
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}