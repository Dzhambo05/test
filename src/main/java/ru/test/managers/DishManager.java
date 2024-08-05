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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Dish dish;
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from Dish where name = :name", Dish.class);
            query.setParameter("name", name);
            dish = (Dish) query.getSingleResult();
            Transaction transaction = session.beginTransaction();
            System.out.println("Внесите новые данные. Внимание! Если вы оставите значение пустым, оно останется прежним\n - Новое название: ");
            String newName = reader.readLine();
            System.out.println(" - Новая цена: ");
            String newPrice = reader.readLine();
            System.out.println(" - Новый вес: ");
            String newWeight = reader.readLine();
            System.out.println(" - Новую категорию: ");
            String newCategory = reader.readLine();
            System.out.println(" - Новый состав: ");
            String newCompound = reader.readLine();
            System.out.println(" - Новое описание: ");
            String newDescription = reader.readLine();
            if (newName.equals("")) {
                dish.setName(dish.getName());
            } else {
                dish.setName(newName);
            }
            if (newPrice.equals("")) {
                dish.setPrice(dish.getPrice());
            } else {
                dish.setPrice(Integer.parseInt(newPrice));
            }
            if (newWeight.equals("")) {
                dish.setWeight(dish.getWeight());
            } else {
                dish.setWeight(Float.parseFloat(newWeight));
            }
            if (newCategory.equals("")) {
                dish.setCategory(dish.getCategory());
            } else {
                dish.setCategory(newCategory);
            }
            if (newCompound.equals("")) {
                dish.setCompound(dish.getCompound());
            } else {
                dish.setCompound(newCompound);
            }
            if (newDescription.equals("")) {
                dish.setDescription(dish.getDescription());
            } else {
                dish.setDescription(newDescription);
            }
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
