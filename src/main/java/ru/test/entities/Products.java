package ru.test.entities;

import jakarta.persistence.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "description")
    private String description;
    @Column(name = "compound")
    private String compound;
    @Column(name = "weight")
    private float weight;
    @Column(name = "category")
    private String category;
    /*static String path = Test.url + Test.dbName;
    public static void addProduct() {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                Statement statement = conn.createStatement();
                System.out.println("Внесите название товаара: ");
                String name = reader.readLine();
                System.out.println("Внесите цену товара: ");
                int price = Integer.parseInt(reader.readLine());
                System.out.println("Внесите описание товара: ");
                String description = reader.readLine();
                System.out.println("Внесите состав товара: ");
                String compound = reader.readLine();
                System.out.println("Внесите вес товара: ");
                float weight = Float.parseFloat(reader.readLine());
                System.out.println("Внесите категорию товара: ");
                String category = reader.readLine();

                String query = "INSERT INTO products(name, price, description, compound, weight, category) VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.setInt(2, price);
                ps.setString(3, description);
                ps.setString(4, compound);
                ps.setFloat(5, weight);
                ps.setString(6, category);
                ps.execute();
            }
        } catch (Exception e) {
            System.out.println("Add order failed");
        }
    }
    public static void deleteProduct(String name) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization)){
                Statement statement = conn.createStatement();

                String query = "DELETE FROM products WHERE name = (?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.execute();
            }
        } catch (Exception e) {
            System.out.println("Delete order failed");
        }
    }
    public static void updateProduct(String name) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                Statement statement = conn.createStatement();
                
                System.out.println("Введите цену товара: ");
                int price = Integer.parseInt(reader.readLine());
                System.out.println("Введите описание товара: ");
                String description = reader.readLine();
                System.out.println("Введите состав товара: ");
                String compound = reader.readLine();
                System.out.println("Введите вес товара: ");
                float weight = Float.parseFloat(reader.readLine());
                System.out.println("Введите категорию товара: ");
                String category = reader.readLine();


                String query = "UPDATE products SET price = (?), description = (?), compound = (?), weight = (?), category = (?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, price);
                ps.setString(2, description);
                ps.setString(3, compound);
                ps.setFloat(4, weight);
                ps.setString(5, category);
                ps.execute();
            }
        } catch (Exception e) {
            System.out.println("Update courier failed");
        }
    }
    public static void showProduct(String name) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization)) {
                PreparedStatement ps = conn.prepareStatement("SELECT * from products where name = (?)");
                ps.setString(1, name);
                ps.execute();
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5) + " "+ resultSet.getFloat(6) + " " + resultSet.getString(7));
                }
            }
        } catch (Exception e) {
            System.out.println("Show product failed");
        }
    }*/
}
