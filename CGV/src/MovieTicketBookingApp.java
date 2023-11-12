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


public class MovieTicketBookingApp {
    //list
    private static ArrayList<Movie> movies = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Tạo cửa sổ chính
        JFrame frame = new JFrame("CGV Cinema");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800); // Kích thước lớn hơn để chứa 8 ô phim
        frame.setLocationRelativeTo(null);

        // Tạo JPanel để chứa các phim với GridLayout
        JPanel moviePanelLeft = new JPanel(new GridLayout(0, 1)); // 1 cột, số hàng tùy ý
        JPanel moviePanelRight = new JPanel(new GridLayout(0, 1)); // 1 cột, số hàng tùy ý
        int targetWidth = 200; // Kích thước mục tiêu (độ rộng)
        int targetHeight = 250; // Kích thước mục tiêu (độ cao)
        JPanel moviePanel = new JPanel(new GridLayout(0, 2));
        JScrollPane scrollPane = new JScrollPane(moviePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        String csvFilePath = "C:\\Users\\Admin\\Desktop\\Code\\Java\\CGV\\Movies.csv";

        int cnt=1;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String title = parts[0].trim();
                    int duration = Integer.parseInt(parts[1].trim());
                    String imagePath = parts[2].trim();

                    Movie movie = new Movie();
                    movie.setTitle(title);
                    movie.setDuration(duration);
                    movie.setImagePath(imagePath);
                    movie.setIndex(cnt++);

                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            MovieInfoPanel movieInfoPanel = new MovieInfoPanel(
                    movie,
                    "10:00 AM",
                    movie.getImagePath(),
                    targetWidth,
                    targetHeight
            );

            if (i <= movies.size()/2-1) {
                moviePanelLeft.add(movieInfoPanel);
            }
            else {
                moviePanelRight.add(movieInfoPanel);
            }
        }

        // Tạo JSplitPane để chia màn hình thành hai bên
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, moviePanelLeft, moviePanelRight);
        splitPane.setDividerLocation(700); // Điều chỉnh vị trí chia màn hình
        splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Thêm JSplitPane vào cửa sổ chính
        frame.add(splitPane);

        // Hiển thị cửa sổ
        frame.setVisible(true);
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
}
