package ru.javamentor.preproject.service;

import ru.javamentor.preproject.dao.UserDAO;
import ru.javamentor.preproject.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserSeviceImpl implements UserService {
    private UserDAO userDAO;

    public UserSeviceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean addUser(User user) {
        if (userDAO.ExistUser(user)) {
            userDAO.addUser(user);
            return true;
        }
        return false;
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }
}
