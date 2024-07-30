package ru.test.managers;

import org.hibernate.Transaction;
import ru.test.entities.Person;
import ru.test.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class PersonManager {

    private SessionFactory sessionFactory;

    public void init() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Person> getAllPersons() {
        try (Session session = sessionFactory.openSession()){
            Query<Person> query = session.createQuery("from Person", Person.class);
            return query.list();
        }
    }

    public boolean addPerson(Person person) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(person);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Add person failed");
            return false;
        }
    }
    public boolean deletePersonByNickname(String nickname) {
        Person person;
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from Person p where telegramNickname = :telegram_nickname");
            query.setParameter("telegram_nickname", nickname);
            person = (Person) query.getSingleResult();
            Transaction transaction = session.beginTransaction();
            session.remove(person);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Delete person failed");
            return false;
        }
    }

    public void updatePerson(Person person) {
        try (Session session = sessionFactory.openSession()){
            person = (Person) session.find(Person.class, person.getId());
        }
    }

    public Person getPersonByNickname(String nickname) {
        Person person;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Person p where telegramNickname = :telegram_nickname");
            query.setParameter("telegram_nickname", nickname);
            return person = (Person) query.getSingleResult();
        }
    }
}
