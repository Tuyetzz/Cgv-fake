import java.util.Date;
import java.text.SimpleDateFormat;
public class VipTicket extends Ticket {
    private int vipprice;
    public VipTicket() {

    }
    public VipTicket(String name, Date date, int seat, int vipprice) {
        super(name, date, seat);
        this.vipprice = vipprice;
    }
    public int getVipPrice() {
        return vipprice;
    }
    public void setVipPrice(int vipprice) {
        this.vipprice = vipprice;
    }

    @Override
    public String toString() {
        return "VipTicket{" +
                super.toString() +
                ", vipprice=" + vipprice +
                '}';
    }
}
