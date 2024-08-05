package ru.test.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.test.entities.Person;
import ru.test.hibernate.HibernateUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class PersonManager {

    private SessionFactory sessionFactory;

    public void init() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Person> getAllPersons() {
        try (Session session = sessionFactory.openSession()){
            Query<Person> query = session.createQuery("from Person", Person.class);
            return query.list();
        } catch (Exception e) {
            System.out.println("Get all persons failed");
            return null;
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
            Query query = session.createQuery("from Person p where telegramNickname = :telegram_nickname", Person.class);
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

    public boolean updatePersonByNickname(String nickname) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person;
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from Person where telegramNickname = :telegram_nickname", Person.class);
            query.setParameter("telegram_nickname", nickname);
            person = (Person) query.getSingleResult();
            Transaction transaction = session.beginTransaction();
            System.out.println("Внесите новые данные. Внимание! Если вы оставите значение пустым, оно останется прежним\n - Новое имя: ");
            String newTelegramNickname = reader.readLine();
            if (newTelegramNickname.equals("")) {
                person.setTelegramNickname(person.getTelegramNickname());
            } else {
                person.setTelegramNickname(newTelegramNickname);
            }
            System.out.println("Новoе значениe: Имя - " + person.getTelegramNickname());
            System.out.println("1) - Сохранить2) - Отменить");
            String saveOrNot = reader.readLine();
            if (saveOrNot.equals("1")) {
                session.merge(person);
                transaction.commit();
                return true;
            } else {
                System.out.println("Вы отменили действие");
                return false;
            }
        }catch (Exception e) {
            System.out.println("Update person failed");
            return false;
        }
    }

    public Person getPersonByNickname(String nickname) {
        Person person;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Person p where telegramNickname = :telegram_nickname", Person.class);
            query.setParameter("telegram_nickname", nickname);
            return person = (Person) query.getSingleResult();
        }
    }
}
