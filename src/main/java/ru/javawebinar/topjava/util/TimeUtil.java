package ru.javawebinar.topjava.util;

import java.time.LocalTime;

public class TimeUtil {

    private TimeUtil() {
    }

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        if (startTime == null && endTime == null) {
            return true;
        } else if (startTime == null) {
            return lt.compareTo(endTime) < 0;
        } else if (endTime == null) {
            return lt.compareTo(startTime) >= 0;
        } else {
            return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
        }
    }
}