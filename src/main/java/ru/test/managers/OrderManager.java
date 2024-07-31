package ru.test.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.test.entities.Order;
import ru.test.hibernate.HibernateUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class OrderManager {
    private SessionFactory sessionFactory;

    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Order> getAllOrders() {
        try (Session session = sessionFactory.openSession()){
            Query<Order> query = session.createQuery("from Order", Order.class);
            return query.list();
        } catch (Exception e) {
            System.out.println("Get all orders failed");
            return null;
        }
    }

    public boolean addOrder(Order order) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Add order failed");
            return false;
        }
    }

    public boolean deleteOrderByAddress(String address) {
        Order order;
        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Order where address = :address", Order.class);
            query.setParameter("address", address);
            order = (Order) query.getSingleResult();
            Transaction transaction = session.beginTransaction();
            session.remove(order);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Delete order failed");
            return false;
        }
    }

    public boolean updateOrderByAddress(String address) {
        Order order;
        try (Session session = sessionFactory.openSession();
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            Query query = session.createQuery("from Order where address = :address", Order.class);
            query.setParameter("address", address);
            order = (Order) query.getSingleResult();
            Transaction transaction = session.beginTransaction();
            System.out.println("Ведите новую цену: ");
            order.setTotalPrice(Integer.parseInt(reader.readLine()));
            session.merge(order);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Update order failed");
            return false;
        }
    }

    public Order getOrderByAddress(String address) {
        Order order;
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from Order where address = :address", Order.class);
            query.setParameter("address", address);
            return order = (Order) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Get order failed");
            return null;
        }
    }
}
