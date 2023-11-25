import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ItemList {
    private ArrayList<Item> list;
    private int numOfItem;
    private int MAX = 100;
    
    public ItemList(){
        list = new ArrayList<>();
        numOfItem = list.size();
    }
    
    public Boolean addItem(Item i){
        try{
            list.add(i);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public void displayAll(){
        for(Item x: list){
            System.out.println(x);
        }
    }
    
    public ArrayList<Item> findItem(String creator){
        ArrayList<Item> res = new ArrayList<>();
        for(Item x: list){
            if(x.getCreator().compareTo(creator)==0){
                res.add(x);
            }
        }
        return res;
    }
    
    public ArrayList<Integer> findItemIndex(String creator){
        ArrayList<Integer> res = new ArrayList<>();
        for(Item x: list){
            if(x.getCreator().compareTo(creator)==0){
                res.add(list.indexOf(x));
            }
        }
        return res;
    }
    
    public Boolean updateItem(int index, Item ie){
        try{
            list.set(index, ie);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public Boolean removeItem(int index){
        try{
            list.remove(index);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public void displayItemByType(String type){
        for(Item x: list){
            if(x.getClass().toString().contains(new StringBuffer(type))){
                System.out.println(x);
            }
        }
    }
    
    public void sortItem(){
        list.sort(new Comparator<Item>(){
            @Override
            public int compare(Item i1, Item i2){
                return i1.toString().compareTo(i2.toString());
            }
        });
    }
}
