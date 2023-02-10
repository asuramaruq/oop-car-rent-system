import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "55810579";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Car_Rental";
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String login = null;
        String password = null;
        Client client = new Client();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            System.out.println("Welcome to the ROD");
            System.out.println("1.Sign in\n2.Sign up");
            int res = scan.nextInt();
            if (res == 2) {
                Registration();
                //System.out.println("спс за регис" + client.getName()+" " + client.getSurname());
            } else if (res == 1) {
                System.out.print("login:");
                login = scan.next();
                System.out.print("password:");
                password = scan.next();
                client = Sign_in(login, password);
            }
            System.out.println("приветствие" + " " + client.getName() + " " + client.getSurname());
            while (true) {
                System.out.println("выбор опц");
                System.out.println(" 1. аренда авто \n 2.все машины \n 3.доступные машины\n 4.профиль\n 5.выход");
                int command = scan.nextInt();
                if (command == 1) {
                    Car car = new Car();
                    System.out.println("слова при аренде");
                    System.out.println("вот доступные машины для аренды ВЫБИРАЙ");
                    //showAllAvailableCars();
                    showAllCars();
                    int ID = scan.nextInt();
                    car = rentCar(ID);
                    System.out.println(" время аренды? -15.02.23-");
                    String time = scan.next();
                    System.out.println(client.getName() + " аренда " + car.getBrand() + " " + car.getModel() + " до " + time);
                    // функция с арендой машины
                } else if (command == 2) {
                    showAllCars();
                } else if (command == 3) {
                    String status = "available";
                    showAllAvailableCars();
                } else if (command == 4) {
                    checkProfile(password);
                } else if (command == 5) {
                    System.exit(0);
                }
            }


        } catch (Exception e) {
            System.out.println("Exception occurred!");
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Exception occurred!");
            }
        }
    }

    public static void Registration() {
        String SQL_INSERT = "insert into clients (name, surname, login, password , telephone) values (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
            String name, surname, login, password, telephone;
            System.out.println("First name: ");
            name = scan.nextLine();
            preparedStatement.setString(1, name);
            System.out.println("Second name: ");
            surname = scan.nextLine();
            preparedStatement.setString(2, surname);
            System.out.println("Login: ");
            login = scan.nextLine();
            preparedStatement.setString(3, login);
            System.out.println("Password: ");
            password = scan.nextLine();
            preparedStatement.setString(4, password);
            System.out.println("Telephone: ");
            telephone = scan.nextLine();
            preparedStatement.setString(5, telephone);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Client Sign_in(String login, String password) {
        Client client = new Client();
        String DB = "select * from clients where login = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DB)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                while (!resultSet.getString("password").equals(password) || !resultSet.getString("login").equals(login)) {
                    System.out.println("try again");
                    password = scan.next();
                }
                client = new Client(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("telephone"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return client;
    }

    public static void showAllCars() {
        String AllCars = "select * from cars";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(AllCars)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getInt("year") + " " + resultSet.getString("brand") + " "
                        + resultSet.getString("model") + " " + resultSet.getString("type") + " " + resultSet.getString("color") + " " + resultSet.getString("status"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void showAllAvailableCars() {

        String availableCars = "SELECT * FROM cars WHERE status = 'available'";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(availableCars);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getInt("year") + " " + resultSet.getString("brand") + " "
                        + resultSet.getString("model") + " " + resultSet.getString("type") + " " + resultSet.getString("color") + " " + resultSet.getString("status"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Car rentCar(int id) {
        Car car = new Car();
        String Cars = "select * from cars where id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(Cars)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                car = new Car(resultSet.getInt("id"), resultSet.getInt("year"), resultSet.getString("brand"), resultSet.getString("model"),
                        resultSet.getString("type"), resultSet.getString("color"), resultSet.getString("status"));
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }

        return car;
    }

    public static void checkProfile(String password) {
        String users = "select * from clients where password = ? ";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(users)) {
            preparedStatement.setString(1, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("User: " + resultSet.getString("name") + " " + resultSet.getString("surname") + "\nLogin: " + resultSet.getString("login") + "\nPassword: "
                        + resultSet.getString("password") + "\nTelephone: " + resultSet.getString("telephone"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}