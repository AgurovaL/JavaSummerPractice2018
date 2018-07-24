package com.agurova.dal.impl;

import com.agurova.dal.UserRepository;
import com.agurova.hibernate.HibernateUtil;
import com.agurova.models.User;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    public void save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        session.flush();
        transaction.commit();
        session.close();
    }


    public User findByID(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User resultUser = session.get(User.class, id);

        session.close();
        Hibernate.initialize(resultUser.getImages());
        return resultUser;
    }

    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            session.close();
        }
    }

    public List<User> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> result = session.createQuery("from User").list();
        for (User user : result) {
            Hibernate.initialize(user.getImages());
        }
        session.close();
        return result;
    }
}
