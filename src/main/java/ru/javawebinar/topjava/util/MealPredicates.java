package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalTime;
import java.util.function.Predicate;

public class MealPredicates {

    private MealPredicates() {
    }

    public static Predicate<Meal> byBothTimes(LocalTime startTime, LocalTime endTime) {
        return meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime);
    }

    public static Predicate<Meal> byStartTime(LocalTime startTime) {
        return meal -> meal.getTime().isAfter(startTime) || meal.getTime().equals(startTime);
    }

    public static Predicate<Meal> byEndTime(LocalTime endTime) {
        return meal -> meal.getTime().isBefore(endTime) || meal.getTime().equals(endTime);
    }

    public static Predicate<Meal> allMeals() {
        return meal -> true;
    }
}