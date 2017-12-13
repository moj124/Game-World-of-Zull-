
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game. 
 * 
 * A "Item" has weight and a name, they will  interact 
 * with other characters, items and the user.
 *
 * @author Michael Jones
 * @version 2017.11.21
 */
public class Item
{
    private int weight;             // stores the wieght of the item.
    private String description;     // stores the description of the item, also used for the 'clue'.
    private String name;            // stores the name of the item.

    /**
     * Constructor - Initialies the properties of each item.
     * @param heaviness sets the weight of the item onto the user, when carried.
     * @param detail adds the basic detail of the item.
     * @param givenName assign the name of the item.
     */
    public Item(int heaviness, String detail, String givenName )
    {
        this.weight = heaviness;
        this.description = detail;
        this.name = givenName;
    }

    public void interact( ){
        System.out.println(name + ": " + description +"\nweight: " + weight);
        
    }
    /**
     * @return the name of the item.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return the basic description of the item.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return the weight of the item.
     */
    public int getWeight()
    {
        return weight;
    }
}
