package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

@Component
public class SecurityUtil {
    private static int id;
    private static UserService userService;

    public SecurityUtil(UserService userService) {
        this.userService = userService;
    }

    public static int authUserId() {
        return id;
    }

    public static User getUserByEmail(String email) {
        User user = userService.getByLogin(email);
        id = user.getId();
        return user;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}