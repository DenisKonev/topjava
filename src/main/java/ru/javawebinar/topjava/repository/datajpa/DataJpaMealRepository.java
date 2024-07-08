package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {
    private static final Sort SORT_DESC_DATE_TIME = Sort.by(Sort.Direction.DESC, "dateTime");

    private final CrudMealRepository crudRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        //TODO add auth for update and fix add
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        //TODO add auth for delete
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        //TODO compare to JPA realization and implement
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.getAllByUserId(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        //TODO implement
        return null;
    }
}
