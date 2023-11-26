import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class MovieTicketBookingApp {
    //list
    private static ArrayList<Movie> movies = new ArrayList<>();
    public void runCode() {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CGV Cinema");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1400, 800);
        frame.setLocationRelativeTo(null);

        JPanel moviePanelLeft = new JPanel(new GridLayout(0, 1));
        JPanel moviePanelRight = new JPanel(new GridLayout(0, 1));
        int targetWidth = 200;
        int targetHeight = 250;
        JPanel moviePanel = new JPanel(new GridLayout(0, 2));
        JScrollPane scrollPane = new JScrollPane(moviePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        loadMoviesFromDatabase();

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            MovieInfoPanel movieInfoPanel = new MovieInfoPanel(
                    movie,
                    "10:00 AM",
                    movie.getImagePath(),
                    targetWidth,
                    targetHeight
            );

            if (i % 2 == 0) {
                moviePanelLeft.add(movieInfoPanel);
            } else {
                moviePanelRight.add(movieInfoPanel);
            }
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, moviePanelLeft, moviePanelRight);
        splitPane.setDividerLocation(700);
        splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(splitPane);

        frame.setVisible(true);
    }

    private static void loadMoviesFromDatabase() {
        String query = "SELECT title, duration, image_path FROM movies";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            int cnt = 1;
            while (resultSet.next()) {
                String title = resultSet.getString("title").trim();
                int duration = resultSet.getInt("duration");
                String imagePath = resultSet.getString("image_path").trim();

                Movie movie = new Movie();
                movie.setTitle(title);
                movie.setDuration(duration);
                movie.setImagePath(imagePath);
                movie.setIndex(cnt++);

                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "Hung123456@");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", connectionProps);
        return conn;
    }

    // Lớp để tạo một ô hình chữ nhật chứa thông tin về phim và hình ảnh
    static class MovieInfoPanel extends JPanel {
        public MovieInfoPanel(Movie movie, String showtime, String imagePath, int targetWidth, int targetHeight) {
            setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            // Đọc hình ảnh và điều chỉnh kích thước
            BufferedImage image = readAndResizeImage(imagePath, targetWidth, targetHeight);

            // Tạo JLabel để hiển thị hình ảnh
            ImageIcon imageIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon);


            // Tạo JLabel để hiển thị thông tin về phim
            JLabel movieInfoLabel = new JLabel("<html><b>" + movie.getTitle() + "</b><br>Thời lượng: " + movie.getDuration() + "<br>Thời gian chiếu: " + showtime + "</html>");
            movieInfoLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            Font infoFont = new Font("Arial", Font.PLAIN, 16); // Tùy chỉnh kích thước chữ ở đây
            movieInfoLabel.setFont(infoFont);
            String buttonColorHex = "#FF5733"; // Ví dụ: Mã màu HEX
            Color buttonColor = Color.decode(buttonColorHex);
            // Tạo nút "Đặt vé"
            JButton bookTicketButton = new JButton("Đặt vé");
            bookTicketButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookTicketButton.setBorderPainted(false); // Loại bỏ viền nút
            bookTicketButton.setBackground(buttonColor); // Màu nền
            bookTicketButton.setForeground(Color.WHITE); // Màu chữ

            // Tùy chỉnh font chữ (nếu cần)
            Font buttonFont = new Font("Arial", Font.BOLD, 14);
            bookTicketButton.setFont(buttonFont);

            bookTicketButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TicketUI ticketUI = new TicketUI(movie.getIndex());
                    ticketUI.runCode();
                }
            });

            add(imageLabel);
            add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa hình ảnh và thông tin
            add(movieInfoLabel);
            add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa thông tin và nút
            add(bookTicketButton);
            add(Box.createVerticalGlue());
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }

        private BufferedImage readAndResizeImage(String imagePath, int targetWidth, int targetHeight) {
            try {
                File file = new File(imagePath);
                if (!file.exists()) {
                    throw new FileNotFoundException("Image file not found: " + file.getAbsolutePath());
                }
                BufferedImage originalImage = ImageIO.read(file);

                double widthRatio = (double) targetWidth / originalImage.getWidth();
                double heightRatio = (double) targetHeight / originalImage.getHeight();
                double ratio = Math.min(widthRatio, heightRatio);

                int newWidth = (int) (originalImage.getWidth() * ratio);
                int newHeight = (int) (originalImage.getHeight() * ratio);

                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
                g.dispose();

                return resizedImage;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public static void main(String[] args) {
        MovieTicketBookingApp app = new MovieTicketBookingApp();
        app.runCode();
    }
}
