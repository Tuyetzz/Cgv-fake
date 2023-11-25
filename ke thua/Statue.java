import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Statue extends Item {
    private int weight;
    private String color;

    public Statue() {
    }

    public Statue(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Statue(int weight, String color, int value, String creator) {
        super(value, creator);
        this.weight = weight;
        this.color = color;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
    
    @Override
    public void input(){
        Scanner sc = new Scanner(System.in);
        super.input();
        
        System.out.print("Enter statue's weight: ");
        int weight = Integer.parseInt(sc.nextLine());
        setWeight(weight);
        
        System.out.print("Enter statue's color: ");
        String color = sc.nextLine();
        setColor(color);
    }

    @Override
    public String toString() {
        return super.toString() + " " + color + " " + weight;
    }
}