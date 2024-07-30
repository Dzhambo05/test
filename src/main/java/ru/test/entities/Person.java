package ru.test.entities;

import jakarta.persistence.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.UUID;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "telegram_nickname", unique = true)
    private String telegramNickname;

    public Person() {
    }

    public Person(String telegramNickname) {
        this.telegramNickname = telegramNickname;
    }

    public UUID getId() {
        return id;
    }

    public String getTelegramNickname() {
        return telegramNickname;
    }
    public void setTelegramNickname(String telegramNickname) {
        this.telegramNickname = telegramNickname;
    }

    /*static String path = Test.url + Test.dbName;

    public static void addPerson() {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                Statement statement = conn.createStatement();
                System.out.println("Введите ник: ");
                String nickName = reader.readLine();

                String query = "INSERT INTO person(telegram_nickname) VALUES(?)";

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, nickName);
                ps.execute();
            }
        } catch (Exception e) {
            System.out.println("Add person failed");
        }
    }

    public static void updatePerson(String name) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                Statement statement = conn.createStatement();
                System.out.println("Введите новое имя: ");
                String newName = reader.readLine();

                String query = "UPDATE person SET telegram_nickname = (?) WHERE telegram_nickname = (?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, newName);
                ps.setString(2, name);
                ps.execute();
            }
        } catch (Exception e) {
            System.out.println("Update person failed");
        }
    }
    public static void deletePerson(String name) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization)) {
                Statement statement = conn.createStatement();
                String query = "DELETE FROM person WHERE telegram_nickname = (?)";

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.execute();
            }
        } catch (Exception e) {
            System.out.println("Delete person failed");
        }
    }
    public static void showPerson(String telegramNickname) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization)) {
                PreparedStatement ps = conn.prepareStatement("SELECT * from person where telegram_nickname = (?)");
                ps.setString(1, telegramNickname);
                ps.execute();
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
                }
            }
        } catch (Exception e) {
            System.out.println("Show person failed");
        }
    }*/
}
