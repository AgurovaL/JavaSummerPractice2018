package com.agurova.dal.impl;

import com.agurova.dal.UserRepository;
import com.agurova.hibernate.HibernateUtil;
import com.agurova.models.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    public Long addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Long result = (Long) session.save(user);
        session.getTransaction().commit();
        return result;
    }

    public User findByID(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        User resultUser = (User) session.load(User.class, id);
//!!!!!!!!!!!!!!!!!get/Load
        Hibernate.initialize(resultUser.getFavoriteImagesList());
        return resultUser;
    }

    public User findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        User resultUser = (User) session.load(User.class, name);
        Hibernate.initialize(resultUser.getFavoriteImagesList());
        return resultUser;
    }

    public List<User> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> result = session.createQuery("from User").list();
        for (User user : result) {
            Hibernate.initialize(user.getFavoriteImagesList());
        }
        return result;
    }

}
