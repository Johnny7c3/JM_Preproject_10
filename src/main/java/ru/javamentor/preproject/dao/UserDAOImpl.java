package ru.javamentor.preproject.dao;

import ru.javamentor.preproject.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public boolean ExistUser(User user) {
        TypedQuery<User> query = entityManager
                .createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
        query.setParameter("username", user.getUsername());
        List<User> list = query.getResultList();
        return list.size() == 0;
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager
                .createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
        query.setParameter("username", name);
        return query.getSingleResult();
    }
}
