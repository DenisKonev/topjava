package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        if (isDuplicateEmail(user)) {
            throw new RuntimeException("User with this email already exists");
        } else {
            return repository.save(user);
        }
    }

    private boolean isDuplicateEmail(User newUser) {
        for (User user : repository.getAll()) {
            if (newUser.getEmail().equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return sortedByEmail(repository.getAll());
    }

    private List<User> sortedByEmail(List<User> users) {
        ((List<User>) new ArrayList<>(users)).sort(Comparator.comparing(User::getEmail));
        return new ArrayList<>(users);
    }

    public void update(User user) {
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}