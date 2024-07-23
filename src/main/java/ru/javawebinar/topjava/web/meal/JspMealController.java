package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
@RequestMapping("/meals")
public class JspMealController {
    private static final Logger log = LoggerFactory.getLogger(JspMealController.class);
    private static final String MEALS = "meals";

    private final MealService service;

    public JspMealController(MealService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model) {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        model.addAttribute(MEALS, MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay()));
        return MEALS;
    }

    @GetMapping("/filter")
    public String filter(HttpServletRequest request, Model model) {
        int userId = SecurityUtil.authUserId();
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);
        model.addAttribute(MEALS, MealsUtil.getFilteredTos(service.getBetweenInclusive(startDate, endDate, userId),
                SecurityUtil.authUserCaloriesPerDay(),
                startTime,
                endTime));
        return MEALS;
    }

    @PostMapping
    public String createOrUpdateGetPost(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (StringUtils.hasLength(request.getParameter("id"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            assureIdConsistent(meal, id);
            log.info("Updating meal {}", id);
            service.update(meal, SecurityUtil.authUserId());
        } else {
            log.info("Creating new meal");
            checkNew(meal);
            service.create(meal, SecurityUtil.authUserId());
        }
        return "redirect:/meals";
    }

    @GetMapping("/modify")
    public String createOrUpdateGet(HttpServletRequest request, Model model) {
        String action = request.getParameter("action");
        int userId = SecurityUtil.authUserId();
        switch (action == null ? "all" : action) {
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        service.get(getId(request), userId);
                model.addAttribute("meal", meal);
                return "mealForm";
            case "delete":
                log.info("Deleting meal {}", getId(request));
                service.delete(getId(request), userId);
                return "redirect:/meals";
            default:
                return "index";
        }
    }

    private int getId(HttpServletRequest request) {
        return Integer.parseInt(Objects.requireNonNull(request.getParameter("id")));
    }
}