package com.agurova.dal.impl;

import com.agurova.dal.ImageRepository;
import com.agurova.hibernate.HibernateUtil;
import com.agurova.models.Image;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ImageRepositoryImpl implements ImageRepository {
    public void save(Image image) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(image);
        session.flush();
        transaction.commit();
        session.close();
    }

    public Image findByID(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Image resultImage = session.get(Image.class, id);
        session.close();
        return resultImage;
    }

    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Image image = session.get(Image.class, id);
            session.delete(image);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            session.close();
        }
    }

    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Image").executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            session.close();
        }
    }

    public List<Image> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            List<Image> result = session.createQuery("from Image").list();
            return result;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
