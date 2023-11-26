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
    private Employee employee;
    private Manager manager;
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
                String password = new String(passwordField.getPassword());
                String selectedRole = (String) roleComboBox.getSelectedItem();

                if (authenticate(username, password, selectedRole)) {
                    loginFrame.dispose();

                    if ("Manager".equals(selectedRole)) {
                        manager = new Manager();
                        manager.setUsername(username);
                        manager.setPassword(password);
                        manager.setSelectedRole(selectedRole);
                        System.out.println(manager.getSelectedRole());
                        ManagerUI1(manager.getSelectedRole());
                    } else if ("Employee".equals(selectedRole)) {
                        employee = new Employee();
                        employee.setUsername(username);
                        employee.setPassword(password);
                        employee.setSelectedRole(selectedRole);
                        ManagerUI2();
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



    private boolean authenticate(String username, String password, String role) {
        String query = "SELECT * FROM employee WHERE username = ? AND password = ? AND role = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void ManagerUI1(String role) {
        SwingUtilities.invokeLater(() -> {
            ManagerUI managerUIWithRole  = new ManagerUI(role);
            managerUIWithRole.runCode();
        });
    }

    private void ManagerUI2() {
        SwingUtilities.invokeLater(() -> {
            ManagerUI managerUIWithoutRole = new ManagerUI();
            managerUIWithoutRole.runCode();
        });
    }

}
