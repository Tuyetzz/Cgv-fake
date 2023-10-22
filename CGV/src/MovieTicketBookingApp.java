import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MovieTicketBookingApp {

    public void createAndShowGUI() {
        // Tạo cửa sổ chính
        JFrame frame = new JFrame("CGV Cinema");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700); // Kích thước lớn hơn để chứa 8 ô phim

        // Tạo JPanel để chứa các phim với GridLayout
        JPanel moviePanelLeft = new JPanel(new GridLayout(0, 1)); // 1 cột, số hàng tùy ý
        JPanel moviePanelRight = new JPanel(new GridLayout(0, 1)); // 1 cột, số hàng tùy ý



        // Tạo 8 ô hình chữ nhật chứa thông tin về các phim
        MovieInfoPanel movie1 = new MovieInfoPanel("Phim 1", "1 giờ 30 phút", "Hành động", "10:00 AM", "img/conan.jpg", 200, 250);
        MovieInfoPanel movie2 = new MovieInfoPanel("Phim 2", "2 giờ", "Kinh dị", "1:30 PM", "img/batman.jpg", 200, 250);
        MovieInfoPanel movie3 = new MovieInfoPanel("Phim 3", "2 giờ 15 phút", "Hài hước", "4:00 PM", "img/phongan.jpg", 200, 250);
        MovieInfoPanel movie4 = new MovieInfoPanel("Phim 4", "1 giờ 45 phút", "Tình cảm", "7:00 PM", "img/trangmau.jpg", 200, 250);

        MovieInfoPanel movie5 = new MovieInfoPanel("Phim 5", "2 giờ 30 phút", "Phiêu lưu", "9:00 AM", "img/spiderman.jpg", 200, 250);
        MovieInfoPanel movie6 = new MovieInfoPanel("Phim 6", "1 giờ 55 phút", "Kịch tính", "1:30 PM", "img/buocchan.jpg", 200, 250);
        MovieInfoPanel movie7 = new MovieInfoPanel("Phim 7", "2 giờ 20 phút", "Hành động", "4:00 PM", "img/tien.jpg", 200, 250);
        MovieInfoPanel movie8 = new MovieInfoPanel("Phim 8", "2 giờ 10 phút", "Hài hước", "7:30 PM", "img/datrung.jpg", 200, 250);

        // Thêm 4 ô phim ban đầu vào màn hình bên trái
        moviePanelLeft.add(movie1);
        moviePanelLeft.add(movie2);
        moviePanelLeft.add(movie3);
        moviePanelLeft.add(movie4);

        // Thêm 4 ô phim mới vào màn hình bên phải
        moviePanelRight.add(movie5);
        moviePanelRight.add(movie6);
        moviePanelRight.add(movie7);
        moviePanelRight.add(movie8);

        // Tạo JSplitPane để chia màn hình thành hai bên
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, moviePanelLeft, moviePanelRight);
        splitPane.setDividerLocation(500); // Điều chỉnh vị trí chia màn hình
        splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Thêm JSplitPane vào cửa sổ chính
        frame.add(splitPane);

        // Hiển thị cửa sổ
        frame.setVisible(true);
        frame.setResizable(false);

    }

    // Lớp để tạo một ô hình chữ nhật chứa thông tin về phim và hình ảnh
    public class MovieInfoPanel extends JPanel {
        public MovieInfoPanel(String title, String duration, String genre, String showtime, String imagePath, int targetWidth, int targetHeight) {
            setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            // Đọc hình ảnh và điều chỉnh kích thước
            BufferedImage image = readAndResizeImage(imagePath, targetWidth, targetHeight);

            // Tạo JLabel để hiển thị hình ảnh
            ImageIcon imageIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon);

            // Tạo JLabel để hiển thị thông tin về phim
            JLabel movieInfoLabel = new JLabel("<html><b>" + title + "</b><br>Thời lượng: " + duration + "<br>Thể loại: " + genre + "<br>Thời gian chiếu: " + showtime + "</html>");
            movieInfoLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

            // Tạo nút "Đặt vé"
            JButton bookTicketButton = new JButton("Đặt vé");
            bookTicketButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Add action listener to the button
            bookTicketButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create an instance of CinemaTicketBookingApp
                    CinemaTicketBookingApp d = new CinemaTicketBookingApp();
                    // Call a method or perform any action on the CinemaTicketBookingApp instance
                    // Example: d.someMethod();
                    closeCurrentFrame();
                }
            });


            add(imageLabel);
            add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa hình ảnh và thông tin
            add(movieInfoLabel);
            add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa thông tin và nút
            add(bookTicketButton);
        }

        private BufferedImage readAndResizeImage(String imagePath, int targetWidth, int targetHeight) {
            try {
                File file = new File(imagePath);
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
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        private void closeCurrentFrame() {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window instanceof JFrame) {
                ((JFrame) window).dispose();
            }
        }
    }
}
