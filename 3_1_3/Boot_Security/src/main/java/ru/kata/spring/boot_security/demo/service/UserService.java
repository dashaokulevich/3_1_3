package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.entites.User;

import java.util.List;


public interface UserService {

    public void save(User user);

    public List<User> listAll();

    public User getById(Long id);

    public void deleteById(Long id);

    public void update(Long id, User user);

    public User getUserByUsername(String username);
}
