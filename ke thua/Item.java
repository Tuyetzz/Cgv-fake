import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Item {
    protected int value;
    protected String creator;

    public Item() {
    }

    public Item(int value, String creator) {
        this.value = value;
        this.creator = creator;
    }
    
    public void input(){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter item's value: ");
        int value = Integer.parseInt(sc.nextLine());
        setValue(value);
        
        System.out.print("Enter item's creator: ");
        String creator = sc.nextLine();
        setCreator(creator);
    }

    @Override
    public String toString() {
        return value + " " + creator;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    
}
