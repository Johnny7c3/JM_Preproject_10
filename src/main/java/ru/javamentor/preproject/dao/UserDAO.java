package ru.javamentor.preproject.dao;

import ru.javamentor.preproject.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(User user);
    User getUserById(int id);
    void updateUser(User user);
    boolean ExistUser(User user);
    User getUserByName(String name);
}
