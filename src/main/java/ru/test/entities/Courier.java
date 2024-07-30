package ru.test.entities;

import jakarta.persistence.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.UUID;

@Entity
@Table(name="courier")
public class Courier {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name="phone_number")
    private String phoneNumber;
    /*static String path = Test.url + Test.dbName;

    public static void addCourier() {
        try{
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
                Statement statement = conn.createStatement();
                System.out.println("Внесите имя курьера: ");
                String name = reader.readLine();
                System.out.println("Внесите номер телефона курьера: ");
                String phone = reader.readLine();

                String query = "INSERT INTO courier(name, phone_number) VALUES(?, ?)";

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, phone);
                ps.execute();
            }
        }
        catch(Exception ex){
            System.out.println("Add courier failed");
        }

    }

    public static void deleteCourier (String name) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization)) {
                Statement statement = conn.createStatement();

                String query = "DELETE FROM courier WHERE name = (?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.execute();
            }
        } catch (Exception ex) {
            System.out.println("Delete courier failed");
        }
    }
    public static void updateCourier (String name) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                Statement statement = conn.createStatement();
                String query = "UPDATE courier SET phone_number = (?) WHERE name = (?)";
                System.out.println("Введите номер: ");
                String number = reader.readLine();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, number);
                ps.setString(2, name);
                ps.execute();
            }
        } catch (Exception e) {
            System.out.println("Update courier failed");
        }
    }
    public static void showCourier (String name) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization)) {
                PreparedStatement ps = conn.prepareStatement("SELECT * from courier where name = (?)");
                ps.setString(1, name);
                ps.execute();
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                }
            }
        } catch (Exception e) {
            System.out.println("Show failed");
        }
    }*/
}
