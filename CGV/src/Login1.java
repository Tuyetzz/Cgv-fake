import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login1 extends JFrame {
    

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
  
            String url = "jdbc:mysql://localhost:3306";
            String user = "host";
            String password = "Dalinh31321";

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

}
