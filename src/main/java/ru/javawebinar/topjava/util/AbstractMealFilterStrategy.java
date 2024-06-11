package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractMealFilterStrategy implements MealFilterStrategy {

    private Map<LocalDate, Integer> getCaloriesSumByDate(List<Meal> meals) {
        return meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));
    }

    @Override
    public List<MealTo> filter(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return meals.stream()
                .filter(meal -> filterCondition(meal, startTime, endTime))
                .map(meal -> MealsUtil.createTo(meal, getCaloriesSumByDate(meals).get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    protected abstract boolean filterCondition(Meal meal, LocalTime startTime, LocalTime endTime);
}