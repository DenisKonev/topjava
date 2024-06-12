package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalTime;
import java.util.function.Predicate;

public class MealPredicates {

    private MealPredicates() {
    }

    public static Predicate<Meal> byTimes(LocalTime startTime, LocalTime endTime) {
        return meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime);
    }
}