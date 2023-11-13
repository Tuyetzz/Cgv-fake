import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.awt.*;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.lang.ClassNotFoundException;

import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

import javax.imageio.ImageIO;

//import java.math.BigInteger;
//import java.util.Stack;

/*public class Login1 extends JFrame {


    private Connection connection;

    public Login1() {
        showLoginWindow();
    }

    private void initializeMainApp() {
        setLayout(new GridLayout(6, 5, 20, 20));
        System.out.println("Main application initialized!");
    }

    private void showLoginWindow() {
        connectToDatabase();

        JFrame loginFrame = new JFrame("Login");
        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();


        String[] roles = {"Manager", "Employee"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);

        JButton loginButton = new JButton("Login");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel("Role:"));
        loginPanel.add(roleComboBox);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                String selectedRole = (String) roleComboBox.getSelectedItem();

                if (authenticate(username, password, selectedRole)) {
                    loginFrame.dispose();

                    if ("Manager".equals(selectedRole)) {
                        openMovieManagementSystemGUI();
                    } else if ("Employee".equals(selectedRole)) {
                        openMovieTicketBookingApp();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginFrame.add(loginPanel);
        loginFrame.setSize(300, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }
    private void connectToDatabase() {
        try {

            String url = "jdbc:mysql://localhost:3306/employee";
            String user = "root";
            String password = "Hung123456@";

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean authenticate(String username, char[] password, String role) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, new String(password));
            statement.setString(3, role);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void openMovieManagementSystemGUI() {
        SwingUtilities.invokeLater(() -> {
            MovieManagementSystemGUI movieManagementSystemGUI = new MovieManagementSystemGUI();
            movieManagementSystemGUI.runCode();
        });
    }

    private void openMovieTicketBookingApp() {
        SwingUtilities.invokeLater(() -> {
            MovieTicketBookingApp movieTicketBookingApp = new MovieTicketBookingApp();
            movieTicketBookingApp.runCode();
        });
    }

}*/

public class Login1 extends JFrame {

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set up the connection properties
            Properties connectionProps = new Properties();
            connectionProps.put("user", "root");
            connectionProps.put("password", "Hung123456@");

            // Establish the connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", connectionProps);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Unable to establish a connection to the database.", e);
        }
    }


    public boolean executeUpdate(Connection conn, String command) throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(command); // This will throw a SQLException if it fails
            return true;
        } finally {

            // This will run whether we throw an exception or not
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public ResultSet executeQuery(Connection conn, String command) throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(command); // This will throw a SQLException if it fails
            return rs;
        } catch (SQLException e) {
            System.out.println("ERROR: Could not query the database");
            e.printStackTrace();
            return null;
        }
    }
    private Connection connection;

    public Login1() {
        showLoginWindow();
    }

    private void initializeMainApp() {
        setLayout(new GridLayout(6, 5, 20, 20));
        System.out.println("Main application initialized!");
    }

    private void showLoginWindow() {
        // Modify this line to get the connection and assign it to the 'connection' field
        try {
            connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        JFrame loginFrame = new JFrame("Login");
        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        String[] roles = {"Manager", "Employee"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);

        JButton loginButton = new JButton("Login");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel("Role:"));
        loginPanel.add(roleComboBox);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                String selectedRole = (String) roleComboBox.getSelectedItem();

                if (authenticate(username, password, selectedRole)) {
                    loginFrame.dispose();

                    if ("Manager".equals(selectedRole)) {
                        openMovieManagementSystemGUI();
                    } else if ("Employee".equals(selectedRole)) {
                        openMovieTicketBookingApp();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginFrame.add(loginPanel);
        loginFrame.setSize(300, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    private boolean authenticate(String username, char[] password, String role) {
        String query = "SELECT * FROM employee WHERE username = ? AND password = ? AND role = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, new String(password));
            statement.setString(3, role);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void openMovieManagementSystemGUI() {
        SwingUtilities.invokeLater(() -> {
            MovieManagementSystemGUI movieManagementSystemGUI = new MovieManagementSystemGUI();
            movieManagementSystemGUI.runCode();
        });
    }

    private void openMovieTicketBookingApp() {
        SwingUtilities.invokeLater(() -> {
            MovieTicketBookingApp movieTicketBookingApp = new MovieTicketBookingApp();
            movieTicketBookingApp.runCode();
        });
    }



    /*public void run() {
        // Connect DB
        Connection conn = null;
        try {
            conn = this.getConnection();
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not connect to the database");
            e.printStackTrace();
            return;
        }

        // Create a table
        /*try {
            String dropString = "DROP TABLE IF EXISTS employee";
            this.executeUpdate(conn, dropString);
            String createString
                    = "CREATE TABLE employee( "
                    + "ID INTEGER NOT NULL, "
                    + "NAME varchar(40) NOT NULL, "
                    + "PRIMARY KEY (ID))";
            this.executeUpdate(conn, createString);
            System.out.println("Created a table");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not create the table");
            e.printStackTrace();
            return;
        }*/

        // Insert data
        // Query data
        /*while (true) {
            System.out.println("1. Insert to table");
            System.out.println("2  Delete element");
            System.out.println("3. View all");
            System.out.println("0. Exit");
            System.out.println("Please choose from 0 - 3");
            int chon;
            Scanner sc = new Scanner(System.in);
            chon = sc.nextInt();
            switch (chon) {
                case 1:


                    try {
                        conn = getConnection();

                        System.out.print("Enter employee ID: ");
                        int id = sc.nextInt();

                        System.out.print("Enter employee name: ");
                        String name = sc.next();

                        String insertString = "INSERT INTO employee VALUES (" + id + ", '" + name + "')";
                        executeUpdate(conn, insertString);

                        System.out.println("Employee added successfully.");
                    } catch (SQLException e) {
                        System.out.println("ERROR: Could not add employee to the table");
                        e.printStackTrace();

                    }
                    break;

                case 2:
                    try {
                        int tmp;
                        tmp = sc.nextInt();
                        String queryString = "Delete from employee where ID = " + tmp + ";";
                        this.executeUpdate(conn, queryString);
                        System.out.println("Employee deleted");
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                case 3:
                    try {
                        String queryString = "SELECT * FROM employee";
                        ResultSet rs = this.executeQuery(conn, queryString);
                        System.out.println("Data in the table:");
                        while (rs.next()) {
                            System.out.println("ID: " + rs.getInt("ID"));
                            System.out.println("Name: " + rs.getString("Name"));
                            System.out.println("---------------");
                        }
                    } catch (SQLException e) {
                        System.out.println("ERROR: Could not query data from the table");
                        e.printStackTrace();
                        return;
                    }
                    break;
                case 0:
                    System.out.println("Goodbye");
                    System.exit(0);
                default : System.out.println("Invalid choice");
            }
        }
    }*/

}
