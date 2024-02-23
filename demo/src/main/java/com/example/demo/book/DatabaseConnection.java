package com.example.demo.book;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private Statement stmt;
    private Connection dbConnection;
    private static final String JDBC_URL = "jdbc:mysql://localhost/LIBRARY";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public DatabaseConnection() {
        try {
            this.dbConnection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            this.stmt = dbConnection.createStatement();
            System.out.println("Connection is not null: " + (dbConnection != null));

        } catch(SQLException ex) {
                ex.printStackTrace();
            }
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM BOOKS";
    
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int bookID = resultSet.getInt("Book_ID");
                String title = resultSet.getString("Title");
                int authorID = resultSet.getInt("Author_ID");
                int genreID = resultSet.getInt("Genre_ID");
                int yearMade = resultSet.getInt("Year_Made");
  
                Book book = new Book(bookID, title, authorID, genreID, yearMade);
    
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return books;
    }

    public boolean addUser(String username, String email, String password) {
        String query = "INSERT INTO USER_ACCOUNT (Username, User_Email, Login_Password, User_Role) VALUES (?, ?, ?, ?)";
    
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            // Set parameters for the query
            preparedStatement.setString(1, username); 
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, "user"); // Assuming default user role is "user"
    
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; 
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }       
}
