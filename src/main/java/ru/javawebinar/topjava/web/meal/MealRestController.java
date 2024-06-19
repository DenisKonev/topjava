package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Controller
public class MealRestController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);
    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public void save(Meal meal) {
        log.info("Save {}", meal);
        service.save(meal);
    }

    public void delete(int id) {
        log.info("Delete id={}", id);
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal get(int id) {
        log.info("Get id={}", id);
        return service.get(id);
    }

    public Collection<MealTo> getAll() {
        log.info("getAll");
        return service.getAll(SecurityUtil.authUserId());
    }

    public Collection<MealTo> getAllFilteredByDateTime(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        log.info("getAllFilteredByDateTime");
        return service.getAllFilteredByDateTime(startDate, startTime, endDate, endTime);
    }

    public void create(Meal meal) {
        log.info("Create {}", meal);
        service.save(meal);
    }

    public void update(Meal meal, Integer id) {
        log.info("Update {} with id={}", meal, id);
        service.update(meal, id, SecurityUtil.authUserId());
    }
}