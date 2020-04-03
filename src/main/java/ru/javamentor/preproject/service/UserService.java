package ru.javamentor.preproject.service;

import ru.javamentor.preproject.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    boolean addUser(User user);
    void deleteUser(User user);
    User getUserById(int id);
    public void updateUser(User user);
    User getUserByName(String name);
}
