package ru.test.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.test.entities.Courier;
import ru.test.hibernate.HibernateUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class CourierManager {
    private SessionFactory sessionFactory;

    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Courier> getAllCouriers() {
        try (Session session = sessionFactory.openSession()){
            Query<Courier> query = session.createQuery("from Courier ", Courier.class);
            return query.list();
        } catch (Exception e) {
            System.out.println("Get all couriers failed");
            return null;
        }
    }

    public boolean addCourier(Courier courier) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(courier);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Add courier failed");
            return false;
        }
    }

    public boolean deleteCourierByPhoneNumber (String phoneNumber) {
        Courier courier;
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from  Courier  where phoneNumber = :phone_number");
            query.setParameter("phone_number", phoneNumber);
            courier = (Courier) query.getSingleResult();
            Transaction transaction = session.beginTransaction();
            session.remove(courier);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Delete courier failed");
            return false;
        }
    }

    public boolean updateCourierByPhoneNumber(String phoneNumber) {
        Courier courier;
        try (Session session = sessionFactory.openSession();
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Query query = session.createQuery("from  Courier  where phoneNumber = :phone_number");
            query.setParameter("phone_number", phoneNumber);
            courier = (Courier) query.getSingleResult();
            Transaction transaction = session.beginTransaction();
            System.out.println("Внесите новое имя: ");
            courier.setName(reader.readLine());
            session.merge(courier);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Update courier failed");
            return false;
        }
    }

    public Courier getCourierByPhoneNumber (String phoneNumber) {
        Courier courier;
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from  Courier  where phoneNumber = :phone_number", Courier.class);
            query.setParameter("phone_number", phoneNumber);
            return courier = (Courier) query.getSingleResult();
        }
    }
}
