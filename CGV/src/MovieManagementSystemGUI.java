import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

class Movie {
    private String title;
    private int duration;
    private String imagePath; // Đường dẫn đến hình ảnh đại diện của bộ phim

    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Movie: " + title + " (Duration: " + duration + " minutes)";
    }
}

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

        movieJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(new JLabel("Details:"));
        panel.add(detailsTextArea);
        panel.add(imageLabel);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(movieJList), BorderLayout.CENTER);

        // ActionListener cho nút "Choose Image"
        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi nút "Choose Image" được nhấn
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
                    imagePathTextField.setText(imagePath);
                    displayImage(imagePath);
                }
            }
        });

        // ActionListener cho nút "Add Movie"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi nút "Add Movie" được nhấn
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

        // ActionListener cho nút "Delete Movie"
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi nút "Delete Movie" được nhấn
                int selectedIndex = movieJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    movieList.remove(selectedIndex);
                    movieListModel.remove(selectedIndex);
                    detailsTextArea.setText("");
                    imageLabel.setIcon(null);
                }
            }
        });

        // ListSelectionListener cho JList "movieJList"
        movieJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Xử lý khi một bộ phim được chọn trong JList
                int selectedIndex = movieJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Movie selectedMovie = movieList.get(selectedIndex);
                    detailsTextArea.setText(selectedMovie.toString());
                    displayImage(selectedMovie.getImagePath());
                }
            }
        });

        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    // Phương thức hiển thị hình ảnh trên JLabel
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MovieManagementSystemGUI();
            }
        });
    }
}
