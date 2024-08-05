import ru.test.entities.*;
import ru.test.managers.CourierManager;
import ru.test.managers.DishManager;
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
                System.out.println(
                        "Выберите таблицу:\n" +
                        "1) - Курьеры \n" +
                        "2) - Люди \n" +
                        "3) - Блюда \n" +
                        "4) - Заказы \n" +
                        "0) - Закрыть программу"
                );
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
                                continue;
                            }
                            if (actionInCourier.equals("1")) {
                                courierManager.updateCourierByPhoneNumber(courier.getPhoneNumber());
                                continue;
                            }
                            break;
                        }
                    }
                }
                if (action.equals("2")) {
                    while (true) {
                        PersonManager personManager = new PersonManager();
                        personManager.init();
                        Person person;
                        List<Person> persons = personManager.getAllPersons();
                        int personNumber = 0;
                        for (int i = 1; i <= persons.size(); i++) {
                            System.out.println(i + ") - Никнэйм: " + persons.get(i - 1).getTelegramNickname());
                        }
                        System.out.println("0) - Выход");
                        System.out.println("Веберите пользователя: ");
                        personNumber = Integer.parseInt(reader.readLine());
                        if (personNumber == 0) {
                            break;
                        } else if (personNumber > persons.size()) {
                            System.out.println("Такого пользователя не существует. Введите снова:");
                            continue;
                        } else {
                            person = persons.get(personNumber - 1);
                            System.out.println("Имя: " + person.getTelegramNickname());
                            System.out.println("Выберите действие: 1) - Изменить 2) - Удалить 0) - Назад");
                            String actionInPerson = reader.readLine();
                            if (actionInPerson.equals("0")) {
                                continue;
                            }
                            if (!actionInPerson.matches("[1,2,0]")) {
                                System.out.println("Вы ввели неверное число. Попробуйте снова");
                                continue;
                            }
                            if (actionInPerson.equals("2")) {
                                personManager.deletePersonByNickname(person.getTelegramNickname());
                                continue;
                            }
                            if (actionInPerson.equals("1")) {
                                personManager.updatePersonByNickname(person.getTelegramNickname());
                                continue;
                            }
                        }
                        break;
                    }
                }
                if (action.equals("3")) {
                    while (true) {
                        DishManager dishManager = new DishManager();
                        dishManager.init();
                        Dish dish;
                        List<Dish> dishes = dishManager.getAllDishes();
                        int dishNumber;
                        for (int i = 1; i <= dishes.size(); i++) {
                            System.out.println(i + ") - Название: " + dishes.get(i - 1).getName() + ". Цена: " + dishes.get(i - 1).getPrice() + ". Вес: " + dishes.get(i - 1).getWeight() + ". Категория: " + dishes.get(i - 1).getCategory() + ". Состав: " + dishes.get(i - 1).getCompound() + ". Описание: " + dishes.get(i - 1).getDescription());
                        }
                        System.out.println("0) - Выход");
                        System.out.println("Выберите блюдо: ");
                        dishNumber = Integer.parseInt(reader.readLine());
                        if (dishNumber == 0) {
                            break;
                        } else if (dishNumber > dishes.size()) {
                            System.out.println("Такого блюда не существует. Введите снова: ");
                            continue;
                        } else {
                            dish = dishes.get(dishNumber - 1);
                            System.out.println("Название: " + dish.getName() + ". Цена: " + dish.getPrice() + ". Вес:" + dish.getCategory() + ". Категория: " + dish.getCategory() + ". Состав:" + dish.getCompound() + ". Описание: " + dish.getDescription());
                            System.out.println("Выберите действие: 1) - Изменить 2) - Удалить 0) - Назад");
                            String actionIdDish = reader.readLine();
                            if (actionIdDish.equals("0")) {
                                continue;
                            }
                            if (!actionIdDish.matches("[1,2,0]")) {
                                System.out.println("Вы ввели неверное число. Попробуйте снова");
                                continue;
                            }
                            if (actionIdDish.equals("2")) {
                                dishManager.deleteDishByName(dish.getName());
                                continue;
                            }
                            if (actionIdDish.equals("1")) {
                                dishManager.updateDishByName(dish.getName());
                                continue;
                            }
                        }
                        break;
                    }
                }
                if (action.equals("4")) {
                    while (true) {
                        OrderManager orderManager = new OrderManager();
                        orderManager.init();
                        System.out.println("Выберите действие:\n1) Сделать заказ\n2) Отменить заказ\n3) Посмотреть заказ\n0) Вернуться назад");
                        String acrionInOrder = reader.readLine();
                        if (acrionInOrder.equals("1")) {
                            DishManager dishManager = new DishManager();
                            dishManager.init();
                            List<Dish> dishes = dishManager.getAllDishes();
                            List<Dish> orderDishes = new ArrayList<>();
                            System.out.println("Выберите блюда: ");
                            System.out.println("0) - Закончить");
                            for (int i = 1; i <= dishes.size(); i++) {
                                System.out.println(i + ") Название блюда: " + dishes.get(i - 1).getName() + ". Цена блюда: " + dishes.get(i - 1).getPrice());
                            }
                            while (true) {
                                String number = reader.readLine();
                                int dishNumber = Integer.parseInt(number);
                                if (dishNumber > dishes.size() || number.equals("")) {
                                    System.out.println("Такого блюда нет. Попробуйте снова");
                                    continue;
                                } else if (dishNumber == 0) {
                                    break;
                                } else {
                                    orderDishes.add(dishes.get(dishNumber - 1));
                                    System.out.println("Вы выбрали " + dishes.get(dishNumber - 1).getName());
                                    continue;
                                }
                            }
                            System.out.println("Вы выбрали: ");
                            int totalPrice = 0;
                            for (Dish d : orderDishes) {
                                System.out.println(d.getName() + " " + d.getPrice());
                                totalPrice += d.getPrice();
                            }
                            System.out.println("Общая сумма: " + totalPrice);
                            System.out.println("Введите адрес: ");
                            String address = reader.readLine();
                            System.out.println("Форма оплаты:\n1)Карта\n2)Наличные\n3)Перевод");
                            Form form = null;
                            String paymentForm = reader.readLine();
                            switch (paymentForm) {
                                case "1":
                                    form = Form.CART;
                                    break;
                                case "2":
                                    form = Form.CASH;
                                    break;
                                case "3":
                                    form = Form.TRANSFER;
                                    break;
                            }
                            Status status = Status.INPROCESS;
                            System.out.println("Кто делает заказ? Введите имя: ");
                            String name = reader.readLine();
                            Person person = new Person(name);
                            PersonManager personManager = new PersonManager();
                            personManager.init();
                            personManager.addPerson(person);
                            Order order = new Order(status, orderDishes, false, form, address, person);
                            orderManager.addOrder(order);
                        }
                        if (acrionInOrder.equals("2")) {
                            System.out.println("Введте адрес заказа: ");
                            orderManager.deleteOrderByAddress(reader.readLine());
                        }
                        if (acrionInOrder.equals("3")) {
                            System.out.println("Введите адрес заказа: ");
                            Order order = orderManager.getOrderByAddress(reader.readLine());
                            System.out.println("Стоимость: " + order.getTotalPrice() + " Время заказа: " + order.getTime() + " Форма оплаты: " + order.getPaymentForm() + " Статус: " + order.getStatus());
                        }
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

