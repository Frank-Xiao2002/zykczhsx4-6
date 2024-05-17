package dev.xxj.user.service;

import dev.xxj.user.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);

    void deleteById(int id);
}
