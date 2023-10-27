//package com.mycompany.netbeanstest2;
import java.util.Date;

public class Bill extends Ticket{
    private int cusCode;
    private Date buy;
    private int nTicket;


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
}
