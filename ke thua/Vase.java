import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Vase extends Item {
    private int height;
    private String material;

    public Vase() {
    }

    public Vase(int height, String material) {
        this.height = height;
        this.material = material;
    }
    
    public Vase(int height, String material, int value, String creator) {
        super(value, creator);
        this.height = height;
        this.material = material;
    }
    public void input(){
        Scanner sc = new Scanner(System.in);
        super.input();
        
        System.out.print("Enter vase's height: ");
        int height = Integer.parseInt(sc.nextLine());
        setHeight(height);
        
        System.out.print("Enter vase's material: ");
        String material = sc.nextLine();
        setMaterial(material);
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return super.toString() + " " + height + " " + material;
    }

    
}