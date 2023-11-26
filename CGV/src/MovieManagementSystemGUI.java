import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class MovieManagementSystemGUI {
    private ArrayList<Movie> movieList = new ArrayList<>();
    private DefaultListModel<Movie> movieListModel = new DefaultListModel<>();
    private JList<Movie> movieJList = new JList<>(movieListModel);
    private JTextField titleTextField = new JTextField(20);
    private JTextField durationTextField = new JTextField(5);
    private JTextField imagePathTextField = new JTextField(20);
    private JLabel imageLabel = new JLabel();
    private JTextArea detailsTextArea = new JTextArea(5, 20);

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "Hung123456@");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", connectionProps);
        return conn;
    }
    public MovieManagementSystemGUI() {

        JFrame frame = new JFrame("Movie Management System");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Title:");
        JLabel durationLabel = new JLabel("Duration (minutes):");

        JButton chooseImageButton = new JButton("Choose Image");
        panel.add(chooseImageButton);

        JButton addButton = new JButton("Add Movie");
        panel.add(titleLabel);
        panel.add(titleTextField);
        panel.add(durationLabel);
        panel.add(durationTextField);
        panel.add(new JLabel("Image Path:"));
        panel.add(imagePathTextField);
        panel.add(addButton);

        JButton deleteButton = new JButton("Delete Movie");
        panel.add(deleteButton);

        JButton saveButton = new JButton("Save to Database");
        panel.add(saveButton);

        movieJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(new JLabel("Details:"));
        panel.add(detailsTextArea);
        panel.add(imageLabel);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(movieJList), BorderLayout.CENTER);

        chooseImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser("C:\\Users\\Admin\\Desktop\\Code\\Java\\CGV\\img");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
                imagePathTextField.setText(imagePath);
                displayImage(imagePath);
            }
        });

        addButton.addActionListener(e -> {
            String title = titleTextField.getText();
            int duration = Integer.parseInt(durationTextField.getText());
            String imagePath = imagePathTextField.getText();
            Movie newMovie = new Movie(title, duration);
            newMovie.setImagePath(imagePath);
            movieList.add(newMovie);
            movieListModel.addElement(newMovie);
            titleTextField.setText("");
            durationTextField.setText("");
            imagePathTextField.setText("");
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = movieJList.getSelectedIndex();
            if (selectedIndex != -1) {
                movieList.remove(selectedIndex);
                movieListModel.remove(selectedIndex);
                detailsTextArea.setText("");
                imageLabel.setIcon(null);
            }
        });

        saveButton.addActionListener(new SaveButtonListener());

        movieJList.addListSelectionListener(e -> {
            int selectedIndex = movieJList.getSelectedIndex();
            if (selectedIndex != -1) {
                Movie selectedMovie = movieList.get(selectedIndex);
                detailsTextArea.setText(selectedMovie.toString());
                displayImage(selectedMovie.getImagePath());
            }
        });

        frame.setSize(1400, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveToDatabase();
            showSuccessMessage();
        }
    }

    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(null, "Save operation successful", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
            imageLabel.setIcon(icon);
        } else {
            imageLabel.setIcon(null);
        }
    }

    private void saveToDatabase() {
        String insertQuery = "INSERT INTO movies (title, duration, image_path) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            for (Movie movie : movieList) {
                preparedStatement.setString(1, movie.getTitle());
                preparedStatement.setInt(2, movie.getDuration());
                preparedStatement.setString(3, movie.getImagePath());
                preparedStatement.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Data saved successfully to the database!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving data to the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieManagementSystemGUI::new);

    }
}