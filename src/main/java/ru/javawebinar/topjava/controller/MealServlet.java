package ru.javawebinar.topjava.controller;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final String INSERT_OR_EDIT = "/addOrUpdateMeal.jsp";
    private static final String LIST_MEAL = "/listMeals.jsp";
    private static final String MEALS = "meals";
    private static final String MEALS_ID = "mealId";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "listMeals";
        }

        if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter(MEALS_ID));
            log.debug("request to delete meal with ID {}", mealId);
            MealDao.deleteMeal(mealId);
            forward = LIST_MEAL;
            request.setAttribute(MEALS, MealsUtil.filteredByStreams(MealDao.getAllMeals(), null, null, MealsUtil.CALORIES_PER_DAY));
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter(MEALS_ID));
            log.debug("request to get meal with ID {}", mealId);
            Meal meal = MealDao.getMealById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeals")) {
            forward = LIST_MEAL;
            log.debug("request to list all meals");
            request.setAttribute(MEALS, MealsUtil.filteredByStreams(MealDao.getAllMeals(), null, null, MealsUtil.CALORIES_PER_DAY));
        } else {
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateTimeStr = request.getParameter("dateTime");
        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            log.error("Invalid dateTime format: {}", dateTimeStr, e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid dateTime format");
            return;
        }

        Meal meal = new Meal(0, dateTime,
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        String mealId = request.getParameter(MEALS_ID);
        if (mealId == null || mealId.isEmpty()) {
            log.debug("request to add {}", meal);
            MealDao.addMeal(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            log.debug("request to update {}", meal);
            MealDao.updateMeal(meal);
        }
        request.setAttribute(MEALS, MealsUtil.filteredByStreams(MealDao.getAllMeals(), null, null, MealsUtil.CALORIES_PER_DAY));
        request.getRequestDispatcher(LIST_MEAL).forward(request, response);
    }
}