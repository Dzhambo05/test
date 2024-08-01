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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Выберите таблицу:\n1)Курьеры\n2)Заказы\n3)Покупатели\n4)Блюда");
                String action1 = reader.readLine();

                if (!action1.matches("[1-4]")) {
                    if (action1.equals("close")) {
                        break;
                    }
                    System.out.println("Введено не правильно число. Попробуйте снова");
                    continue;
                } else if (action1.equals("1")) {
                    CourierManager courierManager = new CourierManager();
                    courierManager.init();
                    System.out.println("Выберите дейтсвие:\n1)Добавить\n2)Удалить\n3)Редактировать\n4)Показать");
                    String courierAction = reader.readLine();
                    switch (courierAction) {
                        case "1":
                            System.out.println("Введите имя курьера:");
                            String name = reader.readLine();
                            System.out.println("Введите номер телефона:");
                            String phoneNumber = reader.readLine();
                            Courier courier = new Courier(name, phoneNumber);
                            courierManager.addCourier(courier);
                            break;
                        case "2":
                            System.out.println("Введите номер телефона:");
                            courierManager.deleteCourierByPhoneNumber(reader.readLine());
                            break;
                        case "3":
                            System.out.println("Введите номер телефона: ");
                            String phoneNumberUpdate = reader.readLine();
                            System.out.println("Введите новое имя: ");
                            String newName = reader.readLine();
                            courierManager.updateCourierByPhoneNumber(phoneNumberUpdate, newName);
                            break;
                        case "4":
                            System.out.println("Введите номер телефона:");
                            Courier courier1 = courierManager.getCourierByPhoneNumber(reader.readLine());
                            System.out.println("Имя: + " + courier1.getName() + "Телефон: " + courier1.getPhoneNumber());
                            break;
                    }
                } else if (action1.equals("2")) {
                    OrderManager orderManager = new OrderManager();
                    orderManager.init();
                    System.out.println("Выберите дейтсвие:\n1)Добавить\n2)Удалить\n3)Редактировать\n4)Показать");
                    String orderAction = reader.readLine();
                    switch (orderAction) {
                        case "1":
                            Status status;
                            System.out.println("Выебрите статус заказа:\n1)В процессе\n2)Готово\n3)отправлено\n4)Доставлено");
                            String s = reader.readLine();
                            if (s.equals("1")) {
                                status = Status.INPROCESS;
                            } else if(s.equals("2")) {
                                status = Status.READY;
                            } else if(s.equals("3")) {
                                status = Status.SENT;
                            } else {
                                status = Status.DELIVERED;
                            }
                            List<Dish> dishes = new ArrayList<>();
                            System.out.println("Введите названия блюд или слово \"stop\"");
                            String nameOfDish;
                            while (!(nameOfDish = reader.readLine()).equals("stop")) {
                                dishes.add(new Dish(nameOfDish));
                            }
                            boolean isPaid = false;
                            System.out.println("Введите 1 если заказ оплачен: ");
                            if (reader.readLine().equals("1")) {
                                isPaid = true;
                            }
                            Form form;
                            System.out.println("Выберите форму оплаты:1)Карта2)Наличные3)Перевод");
                            String f = reader.readLine();
                            if (f.equals("1")) {
                                form = Form.CART;
                            } else if (f.equals("2")) {
                                form = Form.CASH;
                            } else {
                                form = Form.TRANSFER;
                            }
                            System.out.println("Введите адрес доставки: ");
                            String address = reader.readLine();
                            System.out.println("Внесите имя человека, сделавшего заказ: ");
                            PersonManager personManager = new PersonManager();
                            personManager.init();
                            String nameOfPerson = reader.readLine();
                            personManager.addPerson(new Person(nameOfPerson));
                            Person person = personManager.getPersonByNickname(nameOfPerson);
                            System.out.println("Введите цену: ");
                            int price = Integer.parseInt(reader.readLine());
                            Order order = new Order(status, dishes, isPaid, form, address, person, price);
                            orderManager.addOrder(order);
                            break;
                        case "2":
                            System.out.println("Введите адрес заказа: ");
                            orderManager.deleteOrderByAddress(reader.readLine());
                            break;
                        case "3":
                            System.out.println("Введите адрес заказа: ");
                            String ad = reader.readLine();
                            System.out.println("Введите новою цену: ");
                            orderManager.updateOrderByAddress(ad, Integer.parseInt(reader.readLine()));
                            break;
                        case "4":
                            System.out.println("Введите адрес заказа: ");
                            Order order1 = orderManager.getOrderByAddress(reader.readLine());
                            System.out.println(order1.getAddress() + " " + order1.getAddress());
                            break;
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

