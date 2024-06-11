package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalTime;

public class FilterByBothTimes extends AbstractMealFilterStrategy {
    @Override
    protected boolean filterCondition(Meal meal, LocalTime startTime, LocalTime endTime) {
        return TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime);
    }
}