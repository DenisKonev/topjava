package ru.javawebinar.topjava.repository;

import org.springframework.data.jpa.repository.Query;
import ru.javawebinar.topjava.model.User;

import java.util.List;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.meals WHERE u.id = :id")
    User getWithMeals(int id);
}