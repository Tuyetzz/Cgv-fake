import java.util.Date;

public class Bill {
    private int cusCode;
    private Date buy;
    private int nTicket;
    private String seatType;
    private int price;

    public int getcusCode() {
        return cusCode;
    }

    public void setcusCode(int cusCode) {
        this.cusCode = cusCode;
    }

    public Date getBuy() {
        return buy;
    }

    public void setBuy(Date buy) {
        this.buy = buy;
    }

    public int getnTicket() {
        return nTicket;
    }

    public void setnTicket(int nTicket) {
        this.nTicket = nTicket;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
