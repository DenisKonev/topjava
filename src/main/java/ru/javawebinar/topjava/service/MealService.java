package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private MealRepository repository;

    private static final Logger log = LoggerFactory.getLogger(MealService.class);

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public void delete(int id, int userId) {
        if (userId == get(id).getUserId()) {
            checkNotFoundWithId(repository.delete(id), id);
        } else {
            log.error("Unauthorized attempt to delete meal with id={} detected", id);
            throw new RuntimeException("Not authorized to delete");
        }
    }

    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Collection<MealTo> getAll(int userId) {
        return MealsUtil.getTos(sortByDateTime(repository.getAll().stream()
                .filter(meal -> meal.getUserId() == userId)
                .collect(Collectors.toList())), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Collection<MealTo> getAllFilteredByDateTime(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getFilteredTos(sortByDateTime(repository.getAll()), MealsUtil.DEFAULT_CALORIES_PER_DAY, startDate, startTime, endDate, endTime);
    }

    private Collection<Meal> sortByDateTime(Collection<Meal> all) {
        return all.stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    public void update(Meal meal, Integer id, int userId) {
        if (userId == get(id).getUserId()) {
            checkNotFoundWithId(repository.save(meal), id);
        } else {
            log.error("Unauthorized attempt to update meal with id={} detected", id);
            throw new RuntimeException("Not authorized to update");
        }
    }

    public void save(Meal meal) {
        repository.save(meal);
    }
}