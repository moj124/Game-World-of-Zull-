import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * This class is used to implement all the additional commands
 * that extends the usage of the game. This would include 
 * the setup, advanced commands and interacting with the Person, 
 * Item and Room classes.
 * 
 *
 * @author Michael Jones
 * @version 2017.11.30
 */
public class GameConfigure
{
    private int moves;                                  // the number of moves made in the game.                             
    private Stack<String> trackItems;                   // tracks the items added to the game.
    private Stack<Room> visits;                         // tracks the rooms visited by the player.
    private ArrayList<Person> characters;               // used to store the characters in the game.
    private ArrayList<Item> items;                      // used to store the items in the game.
    private ArrayList<Room> rooms;                      // used to store the rooms in the game.
    private Item winItem;                               // stores the winning item in the game.
    private Random random;                              // used for random functionality.
    private Room currentRoom;                           // stores the current room the player is in.
    private static final String PLAYER = "Mario";       // constant string value for the player's name.
    private static final String STARTROOM = "Outside";  // constant start room in the game.
    private static final int MOVELIMIT = 35;            // move limit, before losing.

    /**
     * Constructor for objects of class GameConfigure
     */
    public GameConfigure()
    {
        trackItems = new Stack<String>();
        random = new Random();
        visits = new Stack<Room>();
        items = new ArrayList<Item>(); 
        rooms = new ArrayList<Room>();  
        characters = new ArrayList<Person>();

        // Creates all the items, people, rooms and the player.
        resetGame();
    }

    /**
     * Create all the characters in the game, including the player.
     */
    private void createCharacters()
    {
        Person player = new Person(PLAYER,getRoom(STARTROOM));
        characters.add(player);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, garden, kitchen, frontyard, garage, livingroom,
        upperhallway, downhallway, bedroom1, bedroom2, toilet,teleporter;

        // create the rooms
        outside = new Room("outside the house","Outside");
        garden = new Room("in the Garden", "Garden");
        kitchen = new Room("in the Kitchen","Kitchen");
        frontyard = new Room("in the Frontyard of the house", "Frontyard");
        garage = new Room("in the Garage", "Garage");
        livingroom = new Room("in the Living room", "Living Room");
        upperhallway = new Room("in the Upstairs Hallway","Upstairs Hallway");
        downhallway = new Room("in the Downstairs Hallway", "Downstairs Hallway");
        bedroom1 = new Room("in one of the Bedrooms", "Bedroom");
        bedroom2 = new Room("in the other Bedroom", "Bedroom");
        toilet = new Room("in the Toilet upstairs","Toilet");
        teleporter = new Room("in the Warp Pipe", "Warp Pipe");

        // initialise room exits
        outside.setExit("north", garden);
        outside.setExit("east", frontyard);

        garden.setExit("south", outside);
        garden.setExit("east", kitchen);

        kitchen.setExit("west", garden);
        kitchen.setExit("north", livingroom);
        kitchen.setExit("south", downhallway);

        frontyard.setExit("west", outside);
        frontyard.setExit("north", downhallway);
        frontyard.setExit("east", garage);

        garage.setExit("west", frontyard);
        garage.setExit("north", downhallway);

        livingroom.setExit("west", kitchen);

        downhallway.setExit("north",kitchen);
        downhallway.setExit("west",frontyard);
        downhallway.setExit("south",garage);
        downhallway.setExit("east",upperhallway);

        upperhallway.setExit("north", bedroom2);
        upperhallway.setExit("east", bedroom1);
        upperhallway.setExit("south", toilet);
        upperhallway.setExit("west", downhallway);

        toilet.setExit("north", upperhallway);

        bedroom1.setExit("west",upperhallway);

        bedroom2.setExit("south", upperhallway);

        rooms.add(outside);
        rooms.add(garden);
        rooms.add(kitchen);
        rooms.add(frontyard);
        rooms.add(garage);
        rooms.add(livingroom);
        rooms.add(upperhallway);
        rooms.add(downhallway);
        rooms.add(bedroom1);
        rooms.add(bedroom2);
        rooms.add(toilet);
    }

    /**
     * Creates all the items and adds them to the items ArrayList.
     */
    private void createItems()
    {
        Item belt, nappies, phone, money, cigarretes, 
        jacket, cereal, shoes, keys, comics, bra, 
        bread, bowl, computer;

        belt = new Item(2,"Keeps something from falling","Belt");
        nappies = new Item(7,"Holds the unspeakable","Nappies");
        phone = new Item(4, "A electronic device that holds every answer","Iphone 10");
        money = new Item(1, "A form of currency","Money");
        cigarretes = new Item(2,"It's bad for health","Cigarretes");
        jacket = new Item(3,"Keeps you warm and cozy", "Jacket");
        cereal = new Item(3, "What you eat for breakfast","Kellog's Rice Kripsies");
        shoes = new Item(5,"Used for walking", "Sneakers");
        keys = new Item(2, "Unlock stuff", "Keys");
        comics = new Item(2, "A popular comic","Batman Chronicles");
        bra = new Item(3,"What is this thing?, Eeeewww", "Bra");
        bread = new Item(6,"Your best source of carbohydrates","Bread");
        bowl = new Item(4,"where food is placed","Plate");
        computer = new Item(10,"A computational machine","Computer");

        items.add(belt);
        items.add(nappies);
        items.add(phone);
        items.add(money);
        items.add(cigarretes);
        items.add(jacket);
        items.add(cereal);
        items.add(shoes);
        items.add(keys);
        items.add(comics);
        items.add(bra);
        items.add(bread);
        items.add(bowl);
        items.add(computer);
    } 

    /**
     * Creates all the objects in the game, inventory, location
     * and sets the winning item in the game.
     */
    private void resetGame()
    {
        createRooms();
        createItems();
        createCharacters();

        int itemsToAdd = getRandomNumber(10,items.size());
        addRoomItems(itemsToAdd);
        winItem = createWinItem();

        moves = 1;
        currentRoom = getRoom(STARTROOM);  // Player's start location.
    }

    /**
     * Displays a description of the item or conversation with a character.
     * @param name is of type 'String', retrieves the right object to interact with.
     */
    public void interact(String name)
    {

        // Gets each item from the current room item's array list.
        for (Item object : currentRoom.getInventory().getItems()){

            // check for item is found.
            if (object.getName().equals(name)){
                object.interact();
                break;
            }
        }

        // Gets each person from the character's array list.
        for (Person person : characters){

            // check for person is found.
            if (person.getName().equals(name)){
                //person.interact();
                break;
            }
        }
    }

    /**
     * Performs the command of adding an item to the player's 'bag' 
     * from the current room that contains the item.
     * @param item is of type 'Item' to add to player(Michael's) 'bag'.
     */
    public void carry(Item item)
    {

        // Check if item to be added doesn't go over carry limit.
        if (!((getPerson(PLAYER).getWeight() + item.getWeight()) > getPerson(PLAYER).getCarryLimit())){
            getPerson(PLAYER).getInventory().getItems().add(item); 

            removeRoomItem(item);
            System.out.println();
            System.out.println(item.getName() + " has been added to your bag.");
            System.out.println("You are carrying " + getPerson(PLAYER).getWeight()+ "kg.");
        }
        else {
            System.out.println("Your bag is too heavy to add more items.");
        }

    }

    /**
     * Removes an item specified by the player, from the room that the player is in.
     * @param seekItem the chosen item to be removed from the room.
     * 
     */
    public void removeRoomItem(Item seekItem )
    {
        // Gets the iterator of the currentRoom's item arraylist.
        for(Iterator<Item> it = currentRoom.getInventory().getItems().iterator();it.hasNext();){

            Item item = it.next();

            // if seekItem is found in the currentRoom's item arraylist.
            if(item.getName().equals(seekItem.getName())){

                it.remove(); // removes the item.
            }
        }
    }

    /**
     * Removes an item specified by the player, from the inventory that the player is in.
     * @param seekItem the chosen item to be removed from the inventory.
     * 
     */
    public void removePlayerItem(Item seekItem )
    {
        // Gets the iterator of the currentRoom's item arraylist.
        for(Iterator<Item> it = getPerson(PLAYER).getInventory().getItems().iterator();it.hasNext();){

            Item item = it.next();

            // if seekItem is found in the currentRoom's item arraylist.
            if(item.getName().equals(seekItem.getName())){

                it.remove(); // removes the item.
            }
        }
    }

    /**
     *  Places the item to remove from player's inventory into the room.
     *  @param seekItem the item to be removed from inventory 
     */
    public void dropItem(Item seekItem)
    {
        //get each item from the items's array list.
        for (Item item : items){
            if(item.getName().equals(seekItem.getName())){
                currentRoom.getInventory().getItems().add(item);
                removePlayerItem(item);
                System.out.println("You have placed the "+ item.getName() +" in the " + currentRoom.getName() + ".");
            }
        }
    }

    /**
     * Displays the status of the game, 
     * this includes room, exits,items available and moves made.
     */
    public void printStatus(){
        System.out.println();
        System.out.println("Status: ");
        System.out.println(currentRoom.getLongDescription());
        currentRoom.printItems();
        System.out.println("You have made " + moves + " moves so far.");
    }

    /**
     * Prints out all the items that are currently in the player's inventory.
     */
    public void printInventory()
    {   
        System.out.println();

        // Checks if the player has items.
        if(!(getPerson(PLAYER).getInventory().getItems().isEmpty())){
            System.out.println("In your backpack, you have:");

            // Gets each item in the player's inventory.
            for(Item item: getPerson(PLAYER).getInventory().getItems()){
                System.out.print(item.getName() + ",");
            }
            System.out.println();
            System.out.println("You are carrying "+ getPerson(PLAYER).getWeight() + "kg.");
        }
        else{
            System.out.println("There are currently no items in your backpack.");
        }
        System.out.println();
    }

    /**
     * Goes back to the previous room visited from the stack. 
     */
    public Room back()
    {

        //Checks if the has player visited a room, excludes the starting room.
        if(!(visits.isEmpty())){

            // If the room currently in, is in the stack then remove it.
            if(currentRoom == visits.peek() && visits.size() !=1){
                visits.pop();
                currentRoom = visits.pop(); // Sets the player to move to the previous room.
            }
            else {
                currentRoom = visits.pop();
            }
            setUserLocation(currentRoom);   // Sets the location of the player.
            moves++;
            printStatus();                  // Displays the 
        }
        else {
            System.out.println();
            System.out.println("Please choose a different command.\nYou have travelled back to the start.");
        }
        System.out.println();
        return currentRoom;
    }

    /**
     * Randomly adds up to a random amount of items to a random room,
     * until a specified, random amount of items is reached. 
     */
    private void addRoomItems(int itemsToAdd)
    {

        // Adds up to 3 random items to a random room .
        if (!(itemsToAdd == 0)){

            for(int j = 0; j < itemsToAdd; j++){
                Item chooseItem = chooseValidItem(); 
                Room chooseRoom = rooms.get(random.nextInt(rooms.size()));

                chooseRoom.getInventory().getItems().add(chooseItem);
                System.out.println(chooseRoom.getName()+": "+chooseItem.getName());

            }
        }
    }

    /**
     * Checks if any person or player has reached the 
     * winning situation, in the game.
     * @return true, for the game to end and someone wins, false otherwise.
     */
    public boolean checkWin()
    {
        boolean won = false;

        // Checks for the wining condition, if true end game. Else keep running.
        if((getPerson(PLAYER).getInventory().getItems().contains(winItem) && getPerson(PLAYER).getLocation().getName().equals(STARTROOM))||(moves > MOVELIMIT)){
            won = true;

            // Checks if the player won, then print a player winning statement.
            if (moves > MOVELIMIT){
                System.out.println("Princess Peach: "+ getPerson(PLAYER).getName() + ", you have run out of time, Rover has ran away.");
            }
            else{
                System.out.println("Princess Peach:"+ getPerson(PLAYER).getName() + ", You've found the " + winItem.getName() + "!");
                printEndingStory();
            }

        } 
        return won;
    }

    /**
     * Displays the ending scene for the game.
     */
    private void printEndingStory()
    {
        System.out.println();
        System.out.println("Princess Peach: Thanks, Mario. We'll be forever friends!");
        System.out.println("Mario: Oh, no...");
        System.out.println("Mario: Princess Peach you are more than a friend to me...");
        System.out.println();
    }

    /**
     * Produces a unused valid, random index from the items array list.
     * @return a index value.
     */
    private Item chooseValidItem()
    {

        // Sets the intial random value.
        int numberRandom = random.nextInt(items.size());

        while (trackItems.contains(items.get(numberRandom).getName())){
            numberRandom = random.nextInt(items.size());
        }

        // Loops until a unused value is assigned to numberRandom,
        // within the array list size.
        
        trackItems.push(items.get(numberRandom).getName());

        return items.get(numberRandom);
    }

    /**
     * Produces the item criteria for a winning situation.
     * @return the winning item, that the player needs to find.
     */
    private Item createWinItem()
    {   
        boolean valid = false;
        int randomNumber = random.nextInt(items.size());
        
        // repeat until the winning situation is reached, and valid is true.
        while (!valid){
            // the winning situation check.
            if(!(items.get(randomNumber).getWeight() > characters.get(0).getCARRYLIMIT()) && trackItems.contains(items.get(randomNumber).getName())){
                valid = true;
                return items.get(randomNumber);
            }
            else{
                randomNumber = random.nextInt(items.size());
            }
        }
        Item winItem = items.get(randomNumber);
        return winItem;
    }

    /**
     * Sets the player's location to the specifed room.
     * @param setRoom is the room the player is to 'move to'.
     */
    public void setUserLocation(Room setRoom)
    {

        // Gets each person from the characters' array list.
        for(Person person: characters){

            // Check if found the player.
            if(person.getName().equals(PLAYER)){

                // sets the location of the player to the specifed room.
                person.setLocation(setRoom);
            }
        }
    }

    /**
     * Increments the value of the variable 'moves' by 1.
     */
    public void incrementMoves()
    {
        moves ++;
    }

    /**
     * Changes the current room the player is in.
     * @param room is the room that needs to be set.
     */
    public void setCurrentRoom(Room setRoom)
    {
        currentRoom = setRoom;
    }

    // Get methods are all below.

    /**
     * Gets a random number between a range of two limits that is specified.
     * @param lowerlimit the lower bound of the required range.
     * @param upperLimit the upper bound of the required range.
     * @return the valid random number within the specified range, excluding the upper limit.
     */
    public int getRandomNumber(int lowerLimit, int upperLimit)
    {
        int num = random.nextInt(upperLimit);
        // repeat while the number is lower than the lower limit.
        while(num < lowerLimit){
            num = random.nextInt(upperLimit);
        }
        return num;
    }

    /**
     * Gets the room object from the parameter.
     * @param roomName the name of the room object.
     */
    public Room getRoom(String roomName)
    {
        Room room = rooms.get(0);
        // get each room from the rooms's arraylist.
        for (int i=0; i<rooms.size();i++){
            if (rooms.get(i).getName().equals(roomName)){
                room= rooms.get(i);
            }
        }
        return room;
    }

    /**
     * Gets the item object from the parameter.
     * @param itemName the name of the room object to be found.
     */
    public Item getItem(String itemName)
    {
        Item currentItem = items.get(0);
        // get each item in the items's array list.
        for (Item item : items){
            if (item.getName().equals(itemName)){
                currentItem = item;
            }
        }
        return currentItem;
    }

    /**
     * Gets the person object from the parameter.
     * @param personName the name of the person object to be found.
     */
    public Person getPerson(String personName)
    {
        Person currentPerson = characters.get(0);
        // get each person object in characters's array list.
        for (Person person : characters){
            if (person.getName().equals(personName)){
                currentPerson = person;
            }
        }
        return currentPerson;
    }

    /**
     * @return the stack of visited rooms.
     */
    public Stack<Room> getVisits()
    {
        return visits;
    }

    /**
     * @return the current Room the player is in.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * @return the winning item for the game.
     */
    public Item getWinItem()
    {
        return winItem;
    }
    
    /**
     * @return the name of the player.
     */
    public String getPLAYERName()
    {
        return PLAYER;
    }
}
