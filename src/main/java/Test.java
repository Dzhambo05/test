import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Test {
    static List<String> columns = new ArrayList<>();
    public static String url = "jdbc:postgresql://localhost:5432/";
    public static String dbName = "order_service";
    public static Properties authorization = new Properties();



    public static void main(String[] args) {

        try {
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
                            Courier.addCourier();
                            break;
                        case ("2") :
                            System.out.println("Введите имя");;
                            Courier.deleteCourier(reader.readLine());
                            break;
                        case ("3") :
                            System.out.println("Введите имя");
                            String s1 = reader.readLine();
                            Courier.updateCourier(s1);
                            break;
                        case ("4") :
                            System.out.println("Кого вывести?");
                            String name = reader.readLine();
                            Courier.showCourier(name);
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
                            Products.addProduct();
                            break;
                        case ("2") :
                            System.out.println("Введтите название товара: ");
                            Products.deleteProduct(reader.readLine());
                            break;
                        case ("3") :
                            System.out.println("Введите название товара: ");
                            Products.updateProduct(reader.readLine());
                            break;
                        case ("4") :
                            System.out.println("Введите название товара: ");
                            Products.showProduct(reader.readLine());
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
                            Person.addPerson();
                            break;
                        case ("2") :
                            System.out.println("Введтите никнэйм человека: ");
                            Person.deletePerson(reader.readLine());
                            break;
                        case ("3") :
                            System.out.println("Введтите никнэйм человека: ");
                            Person.updatePerson(reader.readLine());
                            break;
                        case ("4") :
                            System.out.println("Введтите никнэйм человека: ");
                            Person.showPerson(reader.readLine());
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
                            Order.addOrder();
                            break;
                        case ("2") :
                            System.out.println("Введтите адрес заказа: ");
                            Order.deleteOrder(reader.readLine());
                            break;
                        case ("3") :
                            System.out.println("Введтите адрес заказа: ");
                            Order.updateOrder(reader.readLine());
                            break;
                        case ("4") :
                            System.out.println("Введтите адрес заказа: ");
                            Order.showOrder(reader.readLine());
                            break;
                    }
            }



        } catch (Exception ignore) {
            System.out.println("Что-то пошло не так");
        }
    }
}
