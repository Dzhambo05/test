package ru.test.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.test.entities.Dish;
import ru.test.hibernate.HibernateUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class DishManager {
    private SessionFactory sessionFactory;

    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Dish> getAllDishes() {
        try (Session session = sessionFactory.openSession()){
            Query<Dish> query = session.createQuery("from Dish ", Dish.class);
            return query.list();
        } catch (Exception e) {
            System.out.println("Get all dishes failed");
            return null;
        }
    }

    public boolean addDish(Dish dish) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(dish);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Add dish failed");
            return false;
        }
    }

    public boolean deleteDishByName(String name) {
        Dish dish;
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from Dish where name = :name", Dish.class);
            query.setParameter("name", name);
            dish = (Dish) query.getSingleResult();
            Transaction transaction = session.beginTransaction();
            session.remove(dish);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Delete dish failed");
            return false;
        }
    }

    public boolean updateDishByName(String name) {
        Dish dish;
        try (Session session = sessionFactory.openSession();
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            Query query = session.createQuery("from Dish where name = :name", Dish.class);
            query.setParameter("name", name);
            dish = (Dish) query.getSingleResult();
            Transaction transaction = session.beginTransaction();
            System.out.println("Введите новую цену: ");
            dish.setPrice(Integer.parseInt(reader.readLine()));
            session.merge(dish);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Update dish failed");
            return false;
        }
    }

    public Dish getDishByName(String name) {
        Dish dish;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Dish  where name = :name", Dish.class);
            query.setParameter("name", name);
            return dish = (Dish) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Get dish failed");
            return null;
        }
    }




}
