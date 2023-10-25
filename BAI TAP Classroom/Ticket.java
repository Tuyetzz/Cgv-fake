public class Ticket {
    private int code;
    private String seatType, price;
    Ticket() {

    }
    Ticket(String seatType , String price) {
        this.code++;
        this.seatType = seatType;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeatType() {
        return seatType;
    }
    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}
