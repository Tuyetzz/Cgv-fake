import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MovieManagementSystemGUI {
    private ArrayList<Movie> movieList = new ArrayList<>();
    private DefaultListModel<Movie> movieListModel = new DefaultListModel<>();
    private JList<Movie> movieJList = new JList<>(movieListModel);
    private JTextField titleTextField = new JTextField(20);
    private JTextField durationTextField = new JTextField(5);
    private JTextField imagePathTextField = new JTextField(20);
    private JLabel imageLabel = new JLabel();
    private JTextArea detailsTextArea = new JTextArea(5, 20);

    public MovieManagementSystemGUI() {
        JFrame frame = new JFrame("Movie Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JButton saveButton = new JButton("Save to CSV");
        panel.add(saveButton);

        movieJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(detailsTextArea);
        panel.add(imageLabel);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(movieJList), BorderLayout.CENTER);

        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi nút "Choose Image" được nhấn
                JFileChooser fileChooser = new JFileChooser("C:\\Users\\Admin\\Desktop\\Code\\Java\\CGV\\img");
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
                    imagePathTextField.setText(imagePath);
                    displayImage(imagePath);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = movieJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    movieList.remove(selectedIndex);
                    movieListModel.remove(selectedIndex);
                    detailsTextArea.setText("");
                    imageLabel.setIcon(null);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDataToCSV(System.getProperty("user.home") + "/Desktop/movies.csv");
            }
        });

        movieJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = movieJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Movie selectedMovie = movieList.get(selectedIndex);
                    detailsTextArea.setText(selectedMovie.toString());
                    displayImage(selectedMovie.getImagePath());
                }
            }
        });

        frame.setSize(1400, 800);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        // Thêm một shutdown hook để lưu dữ liệu khi ứng dụng kết thúc
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                String filePath = "C:\\Users\\Admin\\Desktop\\Code\\Java\\CGV\\Movies.csv";
                saveDataToCSV(filePath);
            }
        }));
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

    public void saveDataToCSV(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Movie movie : movieList) {
                writer.write(String.format("%s,%d,%s\n", movie.getTitle(), movie.getDuration(), movie.getImagePath()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runCode() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MovieManagementSystemGUI();
            }
        });
    }
}