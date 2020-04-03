package ru.javamentor.preproject.service;

import ru.javamentor.preproject.dao.UserDAO;
import ru.javamentor.preproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
    private UserDAO userDAO;

    @Autowired
    public UserDetailServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String str) throws UsernameNotFoundException {
        User user = userDAO.getUserByName(str);
        if (user != null) {
            return user;
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
