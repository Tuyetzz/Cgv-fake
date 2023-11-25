
import java.util.List;
import java.util.Scanner;



public class Main {
    static Scanner sc = new Scanner(System.in);
    static Item inputItem(){
        System.out.print("Enter type: ");
        String type = sc.nextLine();
        if(type.equalsIgnoreCase("vase")){
            Vase v = new Vase();
            v.input();
            return v;
        }else if(type.equalsIgnoreCase("statue")){
            Statue s = new Statue();
            s.input();
            return s;
        }else if(type.equalsIgnoreCase("painting")){
            Painting p = new Painting();
            p.input();
            return p;
        }
        return null;
    }
    public static void main(String[] args) {
        ItemList il = new ItemList();
        while(true){
            System.out.println("\n========================================");
            System.out.println("ITEM LIST");
            System.out.println("1. Adding an item");
            System.out.println("2. Displaying all items");
            System.out.println("3. Finding an item by name");
            System.out.println("4. Finding an item index");
            System.out.println("5. Updating an item by index");
            System.out.println("6. Removing an item by index");
            System.out.println("7. Displaying all items by type");
            System.out.println("8. Sorting items");
            System.out.println("0. Quit");
            System.out.println("=========================================\n");
            System.out.print("Choose (0-8): ");
            int choice = Integer.parseInt(sc.nextLine());
            Item i;
            String creator;
            int index;
            switch (choice) {
                case 1:
                    i = inputItem();
                    il.addItem(i);
                    break;
                case 2:
                    System.out.println("Display all: ");
                    il.displayAll();
                    break;
                case 3:
                    System.out.print("Enter the creator: ");
                    creator = sc.nextLine().trim();
                    for(Item it : il.findItem(creator)){
                        System.out.println(it);
                    }
                    break;
                case 4:
                    System.out.print("Enter the creator: ");
                    creator = sc.nextLine();
                    for(int x : il.findItemIndex(creator)){
                        System.out.println(x);
                    }
                    break;
                case 5:
                    System.out.print("Enter index: ");
                    index = Integer.parseInt(sc.nextLine());
                    i = inputItem();
                    il.updateItem(index, i);
                    break;
                case 6:
                    System.out.print("Enter index: ");
                    index = Integer.parseInt(sc.nextLine());
                    il.removeItem(index);
                    break;
                case 7:
                    System.out.print("Enter the type: ");
                    String type = sc.nextLine();
                    il.displayItemByType(type);
                    break;
                case 8:
                    il.sortItem();
                    System.out.println("The sorted list:");
                    il.displayAll();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Only chose from 0 to 8");
                    break;
            }
        }
    }
}
