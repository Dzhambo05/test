import ru.test.entities.*;
import ru.test.managers.CourierManager;
import ru.test.managers.OrderManager;
import ru.test.managers.PersonManager;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Test {
    static List<String> columns = new ArrayList<>();
    public static String url = "jdbc:postgresql://localhost:5432/";
    public static String dbName = "order_service";
    public static Properties authorization = new Properties();


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (true) {
                System.out.println("Выберите таблицу:\n" +
                        "1) - Курьеры \n2) - Заказы \n3) - Люди \n4) - Блюда \n0) - Закрыть программу");
                String action = reader.readLine();
                if (action.equals("0")) {
                    break;
                }
                if (!action.matches("[1-4]")) {
                    System.out.println("Вы ввели не правильное число. Попробуйте снова");
                    continue;
                }
                if (action.equals("1")) {
                    while (true) {
                        CourierManager courierManager = new CourierManager();
                        courierManager.init();
                        Courier courier;
                        List<Courier> couriers = courierManager.getAllCouriers();
                        int courierNumber = 0;
                        for (int i = 1; i <= couriers.size(); i++) {
                            System.out.println((i) + ") - Имя: " + couriers.get(i - 1).getName() + ". Телефон: " + couriers.get(i - 1).getPhoneNumber());
                        }
                        System.out.println("0) - Выход");
                        System.out.println("Выберите курьера: ");
                        courierNumber = Integer.parseInt(reader.readLine());
                        if (courierNumber == 0) {
                            break;
                        } else if (courierNumber > couriers.size()) {
                            System.out.println("Такого курьера не существует. Введите снова:");
                            continue;
                        } else {
                            courier = couriers.get(courierNumber - 1);
                            System.out.println("Имя: " + courier.getName() + ". Телефон: " + courier.getPhoneNumber());
                            System.out.println("Выберите действие: 1) - Изменить 2) - Удалить 0) - Назад");
                            String actionInCourier = reader.readLine();
                            if (actionInCourier.equals("0")) {
                                continue;
                            }
                            if (!actionInCourier.matches("[1,2,0]")) {
                                System.out.println("Вы ввели неверное число. Попробуйте снова");
                                continue;
                            }
                            if (actionInCourier.equals("2")) {
                                courierManager.deleteCourierByPhoneNumber(courier.getPhoneNumber());
                                break;
                            }
                            if (actionInCourier.equals("1")) {
                                courierManager.updateCourierByPhoneNumber(courier.getPhoneNumber());
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
            }

        }

        }

        /*try {
            System.out.println("Здравствуйте! Выберите таблицу куда хотите добавить данные: ");
            Class.forName("org.postgresql.Driver");


            authorization.put("user", "postgres");
            authorization.put("password", "18012000a");

            Connection connection = DriverManager.getConnection(url, authorization);
            Statement statement = connection.createStatement();
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gangf\\projects\\testmsqp\\src\\main\\resources\\init.sql"));
            String action;

            while (reader.ready()) {
                action = reader.readLine();
              //  System.out.println(action + "\n");
                Boolean set = statement.execute(action);
            }
            connection.close();
            statement.close();
            reader.close();

            connection = DriverManager.getConnection(url + dbName, authorization);
            statement = connection.createStatement();
            reader = new BufferedReader(new FileReader("C:\\Users\\gangf\\projects\\testmsqp\\src\\main\\resources\\tables.sql"));
            while (reader.ready()) {
                action = reader.readLine();
        //        System.out.println(action + "\n");
                Boolean set = statement.execute(action);
            }

            ResultSet set = statement.executeQuery("SELECT tablename FROM pg_tables WHERE schemaname='public';");
            ResultSetMetaData rs = set.getMetaData();
            while (set.next()) {
                columns.add(set.getString(1));
            }
            connection.close();
            statement.close();
            reader.close();

            for (int i = 1; i <= columns.size(); i++) {
                System.out.println(i + ") " + columns.get(i - 1));
            }

            reader = new BufferedReader(new InputStreamReader(System.in));
            int value;
            while (true) {
                System.out.println("Введите нужное число: ");
                String s = reader.readLine();
                if (s.matches("[1-4]")) {
                    System.out.println("Спасибо!");
                    value = Integer.parseInt(s);
                    break;
                }
                System.out.println("Вы ввели не правильное число. Попробуйте снова: ");
            }

            switch (value) {
                case (3) :
                    System.out.println("Выберите действие: ");
                    System.out.println("1) Добавить");
                    System.out.println("2) Удалить");
                    System.out.println("3) Редактирова");
                    System.out.println("4) Показать");

                    String s = reader.readLine();
                    switch (s) {
                        case ("1") :
                            ru.test.entities.Courier.addCourier();
                            break;
                        case ("2") :
                            System.out.println("Введите имя");;
                            ru.test.entities.Courier.deleteCourier(reader.readLine());
                            break;
                        case ("3") :
                            System.out.println("Введите имя");
                            String s1 = reader.readLine();
                            ru.test.entities.Courier.updateCourier(s1);
                            break;
                        case ("4") :
                            System.out.println("Кого вывести?");
                            String name = reader.readLine();
                            ru.test.entities.Courier.showCourier(name);
                            break;
                    }
                    break;
                case (2) :
                    System.out.println("Выберите действие: ");
                    System.out.println("1) Добавить");
                    System.out.println("2) Удалить");
                    System.out.println("3) Редактирова");
                    System.out.println("4) Показать");

                    String o = reader.readLine();
                    switch (o) {
                        case ("1") :
                            ru.test.entities.Products.addProduct();
                            break;
                        case ("2") :
                            System.out.println("Введтите название товара: ");
                            ru.test.entities.Products.deleteProduct(reader.readLine());
                            break;
                        case ("3") :
                            System.out.println("Введите название товара: ");
                            ru.test.entities.Products.updateProduct(reader.readLine());
                            break;
                        case ("4") :
                            System.out.println("Введите название товара: ");
                            ru.test.entities.Products.showProduct(reader.readLine());
                            break;
                    }
                    break;
                case (1) :
                    System.out.println("Выберите действие: ");
                    System.out.println("1) Добавить");
                    System.out.println("2) Удалить");
                    System.out.println("3) Редактирова");
                    System.out.println("4) Показать");

                    String q = reader.readLine();
                    switch (q) {
                        case ("1") :
                            ru.test.entities.Person.addPerson();
                            break;
                        case ("2") :
                            System.out.println("Введтите никнэйм человека: ");
                            ru.test.entities.Person.deletePerson(reader.readLine());
                            break;
                        case ("3") :
                            System.out.println("Введтите никнэйм человека: ");
                            ru.test.entities.Person.updatePerson(reader.readLine());
                            break;
                        case ("4") :
                            System.out.println("Введтите никнэйм человека: ");
                            ru.test.entities.Person.showPerson(reader.readLine());
                            break;
                    }
                    break;
                case (4) :
                    System.out.println("Выберите действие: ");
                    System.out.println("1) Добавить");
                    System.out.println("2) Удалить");
                    System.out.println("3) Редактирова");
                    System.out.println("4) Показать");

                    String w = reader.readLine();
                    switch (w) {
                        case ("1") :
                            ru.test.entities.Order.addOrder();
                            break;
                        case ("2") :
                            System.out.println("Введтите адрес заказа: ");
                            ru.test.entities.Order.deleteOrder(reader.readLine());
                            break;
                        case ("3") :
                            System.out.println("Введтите адрес заказа: ");
                            ru.test.entities.Order.updateOrder(reader.readLine());
                            break;
                        case ("4") :
                            System.out.println("Введтите адрес заказа: ");
                            ru.test.entities.Order.showOrder(reader.readLine());
                            break;
                    }
            }



        } catch (Exception ignore) {
            System.out.println("Что-то пошло не так");
        }*/

