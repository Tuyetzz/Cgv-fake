import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Painting extends Item {
    private int height;
    private int width;
    private boolean isWaterColor, isFramed;

    public Painting() {
    }

    public Painting(int height, int width, boolean isWaterColor, boolean isFramed) {
        this.height = height;
        this.width = width;
        this.isWaterColor = isWaterColor;
        this.isFramed = isFramed;
    }

    public Painting(int height, int width, boolean isWaterColor, boolean isFramed, int value, String creator) {
        super(value, creator);
        this.height = height;
        this.width = width;
        this.isWaterColor = isWaterColor;
        this.isFramed = isFramed;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setIsWaterColor(boolean isWaterColor) {
        this.isWaterColor = isWaterColor;
    }
    
    public void setIsFramed(boolean isFramed) {
        this.isFramed = isFramed;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isIsWaterColor() {
        return isWaterColor;
    }
    
    public boolean isIsFramed() {
        return isFramed;
    }

    @Override
    public void input(){
        Scanner sc = new Scanner(System.in);
        super.input();
        
        System.out.print("Enter painting's height: ");
        int height = Integer.parseInt(sc.nextLine());
        setHeight(height);
        System.out.print("Enter painting's width: ");
        int width = Integer.parseInt(sc.nextLine());
        setWidth(width);
        
        System.out.print("Water color (true/false): ");
        String isWaterColor = sc.nextLine();
        setIsWaterColor(Boolean.parseBoolean(isWaterColor));
        
        System.out.print("Water color (true/false): ");
        String isFramed = sc.nextLine();
        setIsFramed(Boolean.parseBoolean(isFramed));
    }

    @Override
    public String toString() {
        
        return super.toString() + " " + height + " " + width + " " + isWaterColor + " " + isFramed;
    }
    
}
