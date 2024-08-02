package ru.test.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "time")
    private Timestamp time;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @OneToMany
    //@Column(name = "dishes")
    private List<Dish> dishes;

    @Column(name = "total_price")
    private int totalPrice;
    @Column(name = "is_paid")
    private boolean isPaid;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_form")
    private Form paymentForm;
    @Column(name = "address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = true)
    private Person person;
    @ManyToOne
    @JoinColumn(name = "courier_id", nullable = true)
    private Courier courier;



    public Order() {
    }

    public Order(Status status, List<Dish> dishes, boolean isPaid, Form paymentForm, String address, Person person) {
        this.time = Timestamp.valueOf(LocalDateTime.now());
        this.status = status;
        this.dishes = dishes;
        this.isPaid = isPaid;
        this.paymentForm = paymentForm;
        this.address = address;
        this.person = person;
        for (Dish d : dishes) {
            totalPrice += d.getPrice();
        }
    }

    public UUID getId() {
        return id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Form getPaymentForm() {
        return paymentForm;
    }

    public void setPaymentForm(Form paymentForm) {
        this.paymentForm = paymentForm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    /*static String path = Test.url + Test.dbName;

    public static void addOrder () {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                Statement statement = conn.createStatement();

                System.out.println("Выберите статус заказа: ");
                System.out.println("1) В процессе \n 2) Готово \n 3) Отправлено \n 4) Доставлено");
                String status;
                String value = reader.readLine();
                if (value.equals("1")) {
                    status = String.valueOf(Status.INPROCESS);
                } else if (value.equals("2")) {
                    status = String.valueOf(Status.READY);
                } else if (value.equals("3")) {
                    status = String.valueOf(Status.SENT);
                } else {
                    status = String.valueOf(Status.DELIVERED);
                }
                System.out.println("Внесите блюда: ");
                String dishes = reader.readLine();
                System.out.println("Внесите полную стоимость: ");
                int totalPrice = Integer.valueOf(reader.readLine());
                System.out.println("Заказ оплачен?: ");
                boolean isPaid = false;
                if (reader.readLine().equals(("Да"))) {
                    isPaid = true;
                }
                System.out.println("Выберите форму оплаты: ");
                System.out.println("1) Перевод \n 2) Наличные \n 3) Карта");
                String form;
                String value1 = reader.readLine();
                if (value1.equals("1")) {
                    form = String.valueOf(Form.TRANSFER);
                } else if (value1.equals("2")) {
                    form = String.valueOf(Form.CASH);
                } else {
                    form = String.valueOf(Form.CART);
                }
                System.out.println("Впишите адрес доставки: ");
                String address = reader.readLine();

                String query = "INSERT INTO orders(time, status, dishes, total_price, is_paid, payment_form, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(2, status);
                ps.setString(3, dishes);
                ps.setInt(4, totalPrice);
                ps.setBoolean(5, isPaid);
                ps.setString(6, form);
                ps.setString(7, address);
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Add order failed");
        }
    }

    public static void deleteOrder(String address) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization)) {
                String query = "DELETE FROM orders WHERE address  = (?)";

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, address);
                ps.execute();
            }
        } catch (Exception e) {
            System.out.println("Delete order failed");
        }
    }

    public static void updateOrder(String address) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                Statement statement = conn.createStatement();

                System.out.println("Выберите статус заказа: ");
                System.out.println("1) В процессе \n 2) Готово \n 3) Отправлено \n 4) Доставлено");
                String status;
                String value = reader.readLine();
                if (value.equals("1")) {
                    status = String.valueOf(Status.INPROCESS);
                } else if (value.equals("2")) {
                    status = String.valueOf(Status.READY);
                } else if (value.equals("3")) {
                    status = String.valueOf(Status.SENT);
                } else {
                    status = String.valueOf(Status.DELIVERED);
                }
                System.out.println("Внесите блюда: ");
                String dishes = reader.readLine();
                System.out.println("Внесите полную стоимость: ");
                int totalPrice = Integer.valueOf(reader.readLine());
                System.out.println("Заказ оплачен?: ");
                boolean isPaid = false;
                if (reader.readLine().equals(("Да"))) {
                    isPaid = true;
                }
                System.out.println("Выберите форму оплаты: ");
                System.out.println("1) Перевод \n 2) Наличные \n 3) Карта");
                String form;
                String value1 = reader.readLine();
                if (value1.equals("1")) {
                    form = String.valueOf(Form.TRANSFER);
                } else if (value1.equals("2")) {
                    form = String.valueOf(Form.CASH);
                } else {
                    form = String.valueOf(Form.CART);
                }

                String query = "UPDATE orders set time = (?), status = (?), dishes = (?), total_price = (?), is_paid = (?), payment_form = (?) WHERE address = (?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(2, status);
                ps.setString(3, dishes);
                ps.setInt(4, totalPrice);
                ps.setBoolean(5, isPaid);
                ps.setString(6, form);
                ps.setString(7, address);
                ps.execute();
            }
        } catch (Exception e) {
            System.out.println("Update order failed");
        }
    }

    public static void showOrder(String address) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(path, Test.authorization)) {
                Statement statement = conn.createStatement();
                PreparedStatement ps = conn.prepareStatement("SELECT * from orders where address = (?)");
                ps.setString(1, address);
                ps.execute();
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " " + resultSet.getTimestamp(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getInt(5) + " " + resultSet.getBoolean(6) + " " + resultSet.getString(7) + " " + resultSet.getString(8));
                }
            }
        } catch (Exception e) {
            System.out.println("Show order failed");
        }
    }*/
}
