import java.util.Date;
import java.text.SimpleDateFormat;
public class NormalTicket extends Ticket {
    private int normalprice;
    private boolean hasDiscount;
    public NormalTicket() {

    }
    public NormalTicket(String name, Date date, int seat, int normalprice, boolean hasDiscount) {
        super(name, date, seat);
        this.normalprice = normalprice;
        this.hasDiscount = hasDiscount;
    }
    public int getNormalprice() {
        return normalprice;
    }
    public void setNormalprice(int normalprice) {
        this.normalprice = normalprice;
    }
    public boolean hasDiscount() {
        return hasDiscount;
    }
    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }
    @Override
    public String toString() {
        return "NormalTicket{" +
                super.toString() +
                ", normalprice=" + normalprice +
                ", hasDiscount=" + hasDiscount +
                '}';
    }
}
