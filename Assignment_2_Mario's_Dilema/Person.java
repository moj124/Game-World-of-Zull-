
/**
 * This class represents the player or character in the game.
 * It deals with each person's inventory, name and location. 
 * The maximum carry weight of each person is set in this class.
 *
 * @author Michael Jones
 * @version 2017.12.03
 */
public class Person
{ 
    private static final int CARRYLIMIT = 8;        // constant that stores the carry limit of the person.
    private String name;                            // stores the name of the person.
    private Inventory inventory;                    // holds all the items the player is 'carrying' in his bag.
    private Room location;                          // hol
    /**
     * Constructor for objects of class Person
     */
    public Person(String name,Room location)
    {
        inventory = new Inventory();
        
        this.location = location;
        this.name = name;
    }
    
    /**
     * @return weight the total weight of all items being carried.
     */
    public int getWeight(){
        return inventory.getWeight();
    }  
    
    /**
     * @param setRoom the room that the character/player needs to move to.
     */
    public  void setLocation(Room setRoom)
    {
        this.location = setRoom;
    }
    
    /**
     * @return location the position of the charcter/player.
     */
    public Room getLocation()
    {
        return location;
    }
    
    /**
     * @return the limit of carrying weight.
     */
    public int getCarryLimit()
    {
        return CARRYLIMIT;
    }
    
    /**
     * @return the name of the player/character.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return the inventory of the player.
     */
    public Inventory getInventory()
    {
        return inventory;
    }
    
    /**
     * @return the item carrying limit for each person.
     */
    public int getCARRYLIMIT()
    {
        return CARRYLIMIT;
    }
}
