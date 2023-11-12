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
import java.util.ArrayList;

public class MovieTicketBookingApp {

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

        ArrayList<Movie> movies = new ArrayList<>();
        String csvFilePath = "C:\\Users\\Admin\\Desktop\\Code\\Java\\CGV\\Movies.csv";

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

                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


//        // Tạo 8 ô hình chữ nhật chứa thông tin về các phim
//        MovieInfoPanel movie1 = new MovieInfoPanel("Conan: Tàu ngầm sắt màu đen", "1 giờ 30 phút", "10:00 AM", "img/conan.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie2 = new MovieInfoPanel("Batman The Dark Knight", "2 giờ", "1:30 PM", "img/batman.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie3 = new MovieInfoPanel("Phong ấn quỷ dữ", "2 giờ 15 phút", "4:00 PM", "img/phongan.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie4 = new MovieInfoPanel("Cù Lao xác sống", "1 giờ 45 phút", "7:00 PM", "img/culao.jpg", targetWidth, targetHeight);
//
//        MovieInfoPanel movie5 = new MovieInfoPanel("Thành phố ngủ gật", "2 giờ 30 phút", "9:00 AM", "img/ngu.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie6 = new MovieInfoPanel("Bước chân thép", "1 giờ 55 phút", "1:30 PM", "img/buocchan.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie7 = new MovieInfoPanel("Cú máy ăn tiền", "2 giờ 20 phút", "4:00 PM", "img/tien.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie8 = new MovieInfoPanel("Thanh gươm trừ tà", "2 giờ 10 phút", "7:30 PM", "img/thanhguom.jpg", targetWidth, targetHeight);
//
//        MovieInfoPanel movie9 = new MovieInfoPanel("Conan: Tàu ngầm sắt màu đen", "1 giờ 30 phút", "10:00 AM", "img/conan.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie10 = new MovieInfoPanel("Batman The Dark Knight", "2 giờ", "1:30 PM", "img/batman.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie11 = new MovieInfoPanel("Phong ấn quỷ dữ", "2 giờ 15 phút", "4:00 PM", "img/phongan.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie12 = new MovieInfoPanel("Cù Lao xác sống", "1 giờ 45 phút", "7:00 PM", "img/culao.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie13 = new MovieInfoPanel("Conan: Tàu ngầm sắt màu đen", "1 giờ 30 phút", "10:00 AM", "img/conan.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie14 = new MovieInfoPanel("Batman The Dark Knight", "2 giờ", "1:30 PM", "img/batman.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie15 = new MovieInfoPanel("Phong ấn quỷ dữ", "2 giờ 15 phút", "4:00 PM", "img/phongan.jpg", targetWidth, targetHeight);
//        MovieInfoPanel movie16 = new MovieInfoPanel("Cù Lao xác sống", "1 giờ 45 phút", "7:00 PM", "img/culao.jpg", targetWidth, targetHeight);
//        // Thêm 4 ô phim ban đầu vào màn hình bên trái
//        moviePanelLeft.add(movie1);
//        moviePanelLeft.add(movie2);
//        moviePanelLeft.add(movie3);
//        moviePanelLeft.add(movie4);
//        moviePanelLeft.add(movie5);
//        moviePanelLeft.add(movie6);
//        moviePanelLeft.add(movie7);
//        moviePanelLeft.add(movie8);
//
//        // Thêm 4 ô phim mới vào màn hình bên phải
//        moviePanelRight.add(movie9);
//        moviePanelRight.add(movie10);
//        moviePanelRight.add(movie11);
//        moviePanelRight.add(movie12);
//        moviePanelRight.add(movie13);
//        moviePanelRight.add(movie14);
//        moviePanelRight.add(movie15);
//        moviePanelRight.add(movie16);

//        for (int i = 0; i < Math.min(movies.size(), 8); i++) {
//            Movie movie = movies.get(i);
//            MovieInfoPanel moviePanel = new MovieInfoPanel(movie.getTitle(), movie.getDuration(), "10:00 AM", movie.getImagePath(), targetWidth, targetHeight);
//            moviePanelLeft.add(moviePanel);
//        }
//
//        // Tạo 8 ô phim mới vào màn hình bên phải
//        for (int i = 8; i < Math.min(movies.size(), 16); i++) {
//            Movie movie = movies.get(i);
//            MovieInfoPanel moviePanel = new MovieInfoPanel(movie.getTitle(), movie.getDuration(), "10:00 AM", movie.getImagePath(), targetWidth, targetHeight);
//            moviePanelRight.add(moviePanel);
//        }
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            MovieInfoPanel movieInfoPanel = new MovieInfoPanel(
                    movie.getTitle(),
                    Integer.toString(movie.getDuration()),
                    "10:00 AM",
                    movie.getImagePath(),
                    targetWidth,
                    targetHeight
            );

            if (i < 4) {
                // Add the first 8 movies to the left panel
                moviePanelLeft.add(movieInfoPanel);
            }
            else {
                // Add the rest to the right panel
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
        public MovieInfoPanel(String title, String duration, String showtime, String imagePath, int targetWidth, int targetHeight) {
            setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            // Đọc hình ảnh và điều chỉnh kích thước
            BufferedImage image = readAndResizeImage(imagePath, targetWidth, targetHeight);

            // Tạo JLabel để hiển thị hình ảnh
            ImageIcon imageIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon);

            Movie movie = new Movie();

            // Tạo JLabel để hiển thị thông tin về phim
            JLabel movieInfoLabel = new JLabel("<html><b>" + title + "</b><br>Thời lượng: " + duration + "<br>Thời gian chiếu: " + showtime + "</html>");
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
