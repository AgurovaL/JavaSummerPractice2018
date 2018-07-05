package com.agurova.dal.impl;

import com.agurova.dal.ImageRepository;
import com.agurova.hibernate.HibernateUtil;
import com.agurova.models.Image;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class ImageRepositoryImpl implements ImageRepository {

    public Long addImage(Image image) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Long result = (Long) session.save(image);
        session.getTransaction().commit();
        return result;
    }

    public Image findByID(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Image resultImage = (Image) session.load(Image.class, id);
        Hibernate.initialize(resultImage.getLikedUsersList());
        return resultImage;
    }

    public Image findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Image resultImage = (Image) session.load(Image.class, name);
        Hibernate.initialize(resultImage.getLikedUsersList());
        return resultImage;
    }

    public void deleteImage(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            session.beginTransaction();
            Image image = (Image) session.get(Image.class, id);
            session.delete(image);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void deleteImage(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            session.beginTransaction();
            Image image = (Image) session.get(Image.class, name);
            session.delete(image);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public List<Image> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            //!!!!!!!!!!!
            List<Image> result = session.createQuery("from Image").list();
            for (Image user : result) {
                Hibernate.initialize(user.getLikedUsersList());
            }
            return result;
        }
        catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }
}
