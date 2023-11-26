public class Movie {
    private String title;
    private int duration;
    private String imagePath; // Đường dẫn đến hình ảnh đại diện của bộ phim
    private int index;
    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
    public Movie() {

    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
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
