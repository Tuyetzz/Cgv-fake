import java.util.Date;
import java.text.SimpleDateFormat;
public class Ticket {
    private Date date;
    private String name;
    private int seat;
    public Ticket() {
        // Default constructor
    }
    public Ticket(String name, Date date, int seat) {
        this.name = name;
        this.date = date;
        this.seat = seat;
    }

    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Ticket{" + "name='" + name + '\'' + ", date=" + dateFormat.format(date) + ", seat=" + seat + '}';
    }
}