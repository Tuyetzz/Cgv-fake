public class Ticket {
    private int code, price;
    private String seatType;
    Ticket() {

    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
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
