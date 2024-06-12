package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbUtil;

import java.util.List;

public class MealDao {

    public static void addMeal(Meal meal) {
        List<Meal> meals = DbUtil.getMeals();
        meals.add(meal);
    }

    public static void deleteMeal(int mealId) {
        List<Meal> meals = DbUtil.getMeals();
        meals.removeIf(meal -> meal.getId() == mealId);
    }

    public static void updateMeal(Meal updatedMeal) {
        List<Meal> meals = DbUtil.getMeals();
        for (Meal meal : meals) {
            if (meal.getId() == updatedMeal.getId()) {
                meal.setDateTime(updatedMeal.getDateTime());
                meal.setDescription(updatedMeal.getDescription());
                meal.setCalories(updatedMeal.getCalories());
                break;
            }
        }
    }

    public static List<Meal> getAllMeals() {
        return DbUtil.getMeals();
    }

    public static Meal getMealById(int mealId) {
        List<Meal> meals = DbUtil.getMeals();
        for (Meal meal : meals) {
            if (meal.getId() == mealId) {
                return meal;
            }
        }
        return null;
    }
}