package ru.test.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.test.entities.Product;
import ru.test.hibernate.HibernateUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class ProductManager {
    private SessionFactory sessionFactory;

    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()){
            Query<Product> query = session.createQuery("from Product ", Product.class);
            return query.list();
        }
    }

    public boolean addProduct(Product product) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Add product failed");
            return false;
        }
    }

    public boolean deleteProductByName(String name) {
        Product product;
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from Product where name = :name");
            query.setParameter("name", name);
            product = (Product) query.getSingleResult();
            Transaction transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Delete product failed");
            return false;
        }
    }

    public boolean updateProductByName(String name) {
        Product product;
        try (Session session = sessionFactory.openSession();
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            Query query = session.createQuery("from Product where name = :name");
            query.setParameter("name", name);
            product = (Product) query.getSingleResult();
            Transaction transaction = session.beginTransaction();
            System.out.println("Введите новую цену: ");
            product.setPrice(Integer.parseInt(reader.readLine()));
            session.update(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Update product failed");
            return false;
        }
    }

    public Product getProductByName(String name) {
        Product product;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Product  where name = :name");
            query.setParameter("name", name);
            return product = (Product) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Get product failed");
            return null;
        }
    }




}
