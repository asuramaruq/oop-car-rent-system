package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.entities.Car;
import com.company.entities.Client;
import com.company.repositories.interfaces.IClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientRepository implements IClientRepository {
    private IDB DB;

    public ClientRepository(IDB DB) {
        this.DB = DB;
    }

    @Override
    public Client Registration(Client client) {
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);
        try {
            connection = DB.getConnection();
            String SQL_INSERT = "insert into clients (name, surname, username, password, telephone) values (?, ?, ?, ?, ?)";
            String SQL_SELECT = "select * from clients where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, client.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("client with this username already exist.\n Change your username");
                client.setUsername(scanner.nextLine());
                preparedStatement = connection.prepareStatement(SQL_SELECT);
                preparedStatement.setString(1, client.getUsername());
                resultSet = preparedStatement.executeQuery();

            }

            PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_INSERT);
            preparedStatement1.setString(1, client.getName());
            preparedStatement1.setString(2, client.getSurname());
            preparedStatement1.setString(3, client.getUsername());
            preparedStatement1.setString(4, client.getPassword());
            preparedStatement1.setString(5, client.getTelephone());
            preparedStatement1.execute();

            return new Client(client.getName(), client.getSurname(), client.getUsername(), client.getPassword(), client.getTelephone());
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

//    @Override
//    public Client findClient(String username, String password){
//        Scanner scanner = new Scanner(System.in);
//        Connection connection =null;
//        try {
//            connection = DB.getConnection();
//            String SQL = "select * from clients where username = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(!resultSet.isBeforeFirst()){
//                System.out.println("username doesn't exist :_(\nPlease try again, enter your telephone number and password\n");
//                preparedStatement.setString(1, scanner.next());
//                password = scanner.next();
//                resultSet = preparedStatement.executeQuery();
//            }
//            while (resultSet.next()) {
//                while (!resultSet.getString("password").equals(password)) {
//                    System.out.println("Wrong password! Please try again");
//                    password = scanner.next();
//                }
//
//                return new Client(resultSet.getString("name"), resultSet.getString("surname"),
//                        resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("telephone"));
//            }
//
//        }catch (Exception e) {
//            System.out.println(e + "Connection Error!");
//        }
//        return null;
//    }

    @Override
    public Client Sign_in(String username, String password) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        try {
            connection = DB.getConnection();
            String SQL = "select * from clients where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (!resultSet.isBeforeFirst()) {
                System.out.println("username doesn't exist :_(\nPlease try again, enter your telephone number and password\n");
                preparedStatement.setString(1, scanner.next());
                password = scanner.next();
                resultSet = preparedStatement.executeQuery();
            }
            while (resultSet.next()) {
                while (!resultSet.getString("password").equals(password)) {
                    System.out.println("Wrong password! Please try again");
                    password = scanner.next();
                }

                return new Client(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"),
                        resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("telephone"));
            }

        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

//    @Override
//    public Client Sign_in(Client client) {
//        String username = client.getUsername();
//        String password = client.getPassword();
//        Scanner scanner = new Scanner(System.in);
//        Connection connection = null;
//        try {
//            connection = DB.getConnection();
//            String SQL = "select * from clients where username = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (!resultSet.isBeforeFirst()) {
//                System.out.println("username doesn't exist :_(\nPlease try again, enter your telephone number and password\n");
//                preparedStatement.setString(1, scanner.next());
//                password = scanner.next();
//                resultSet = preparedStatement.executeQuery();
//            }
//            while (resultSet.next()) {
//                while (!resultSet.getString("password").equals(password)) {
//                    System.out.println("Wrong password! Please try again");
//                    password = scanner.next();
//                }
//
//                return new Client(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"),
//                        resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("telephone"));
//            }
//
//        } catch (Exception e) {
//            System.out.println(e + "Connection Error!");
//        }
//        return null;
//    }
//    @Override
//    public Client RegistrationNew(String name, String surname, String username, String password, String telephone) {
//        Connection connection = null;
//        Scanner scanner = new Scanner(System.in);
//        try {
//            connection = DB.getConnection();
//            String SQL_INSERT = "insert into clients (name, surname, username, password , telephone) values (?, ?, ?, ?, ?)";
//            String SQL_SELECT = "select * from clients where username = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.isBeforeFirst()) {
//                System.out.println("client with this username already exist.\n Change your username");
//                username = scanner.next();
//                preparedStatement = connection.prepareStatement(SQL_SELECT);
//                preparedStatement.setString(1, username);
//                resultSet = preparedStatement.executeQuery();
//
//            }
//
//            PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_INSERT);
//            preparedStatement1.setString(1, name);
//            preparedStatement1.setString(1, surname);
//            preparedStatement1.setString(1, username);
//            preparedStatement1.setString(1, password);
//            preparedStatement1.setString(1, telephone);
//            preparedStatement1.executeUpdate();
//
//            return new Client(name, surname, username, password, telephone);
//        } catch (Exception e) {
//            System.out.println(e + "Connection Error!");
//        }
//        return null;
//    }


    public List<Client> getAllClients() {
        Connection connection;
        try {
            connection = DB.getConnection();
            String AllClients = "select * from clients where username NOT like 'admin'";
            PreparedStatement preparedStatement = connection.prepareStatement(AllClients);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()){
                Client client = new Client(resultSet.getString("name"), resultSet.getString("surname"),
                        resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("telephone"));
                clients.add(client);
            }
            return clients;
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

    public Client updatePassword(Client client){
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);
        try {
            connection = DB.getConnection();
            String update = String.format("update %s set password = %s where id = %s", "clients", clientID, ID);
            PreparedStatement preparedStatement = connection.prepareStatement();
            preparedStatement.setString(1, client.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("client with this username already exist.\n Change your username");
                client.setUsername(scanner.nextLine());
                preparedStatement = connection.prepareStatement(SQL_SELECT);
                preparedStatement.setString(1, client.getUsername());
                resultSet = preparedStatement.executeQuery();

            }

            PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_INSERT);
            preparedStatement1.setString(1, client.getName());
            preparedStatement1.setString(2, client.getSurname());
            preparedStatement1.setString(3, client.getUsername());
            preparedStatement1.setString(4, client.getPassword());
            preparedStatement1.setString(5, client.getTelephone());
            preparedStatement1.execute();

            return new Client(client.getName(), client.getSurname(), client.getUsername(), client.getPassword(), client.getTelephone());
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

}
