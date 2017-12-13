import java.util.ArrayList;
/**
 * Write a description of class Inventory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Inventory
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> items;
    /**
     * Constructor for objects of class Inventory
     */
    public Inventory()
    {
        items = new ArrayList<>();
    }

    /**
     * Displays the items located in the room.
     */
    public void printItems()
    {
        if(!(items.isEmpty())){
            items.forEach(items -> System.out.print(items.getName() + ","));
            System.out.println();
        }
    }

    /**
     * @return weight the total weight of all items beign carried.
     */
    public int getWeight(){
        return items.stream()
        .map(items -> items.getWeight())
        .reduce(0,(total,weight) -> total + weight );
    }  

    public ArrayList<Item> getItems()
    {
        return items;
    }
}
