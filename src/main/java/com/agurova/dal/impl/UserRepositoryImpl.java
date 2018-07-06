package com.agurova.dal.impl;

import com.agurova.dal.UserRepository;
import com.agurova.hibernate.HibernateUtil;
import com.agurova.models.Image;
import com.agurova.models.User;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    public void addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.flush();
        session.getTransaction().commit();
    }

    public User findByID(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User resultUser = (User) session.load(User.class, id);
        Hibernate.initialize(resultUser.getFavoriteImagesList());
        return resultUser;
    }

    public void deleteUser(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
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
