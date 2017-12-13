
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises the game by creating
 *  the GameConfigure object: It deals with basic commands,
 *  such as 'go' and 'quit'; while it retrieves input via the Parser and sets the story.
 * 
 * @author  Michael Jones
 * @version 2017.11.30
 */

public class Game 
{
    private Parser parser;              // the parser used for recognising the input.
    private GameConfigure gameSettings; // the settings of the game and implementation of other commands. 
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        gameSettings = new GameConfigure();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over or player has won.
        boolean hasWon = false;
        boolean finished = false;
        while (! finished && !hasWon) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            hasWon = gameSettings.checkWin(); //returns true if game is in winning situation.
        }
        System.out.println("Thank you for playing.  Good bye.");
        System.out.println();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome!");
        System.out.println("The objective of this game is to locate Princess Peach's favourite toy for Rover, the dog. \nBring it back to Princess Peach.");
        System.out.println("Princess Peach: "+"I'm counting on you " + gameSettings.getPLAYERName() + "! don't take too long, Rover seems restless.");
        System.out.println("The note says, the vital clue to find his favorite toy is:");
        System.out.println("'"+ gameSettings.getWinItem().getDescription() + "'.");
        System.out.println();
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(gameSettings.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        String commandSecond = command.getSecondWord();

        switch(commandWord){
                case "help":
                    printHelp();
                break;
                case "go":
                    goRoom(command);
                    gameSettings.getVisits().push(gameSettings.getCurrentRoom());
                    gameSettings.incrementMoves();
                break;
                case "back":
                    gameSettings.setCurrentRoom(gameSettings.back());
                    gameSettings.incrementMoves();
                break;
            case "interact":
            gameSettings.interact(command.getObjectName());
            gameSettings.incrementMoves();
            break;
            case "inventory":
            gameSettings.carry(gameSettings.getItem(command.getObjectName()));
            gameSettings.printStatus();
            gameSettings.incrementMoves();
            break;
            case "drop":
            gameSettings.dropItem(gameSettings.getItem(command.getObjectName()));
            gameSettings.printStatus();
            gameSettings.incrementMoves();
            break;
            case "quit":
            wantToQuit = quit(command);
            break;
        }

        // else command not recognised.
        return wantToQuit;
    }

    // implementations of basic user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are confused. You don't know what to do. You wander");
        System.out.println("inside the house.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room and show status of game, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(command.getOtherWords().isEmpty()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getOtherWords().get(0);

        // Try to leave current room.
        //Room nextRoom = gameSettings.getCurrentRoom().getExit(direction);

        if (gameSettings.getCurrentRoom().getExit(direction) == null) {
            System.out.println("There is no door!");
        }
        else {
            // track room visits of the user.
            gameSettings.getVisits().push(gameSettings.getCurrentRoom());
            gameSettings.setCurrentRoom(gameSettings.getCurrentRoom().getExit(direction));
            gameSettings.setUserLocation(gameSettings.getCurrentRoom());

            System.out.println();
            gameSettings.printStatus();
        }
    }

    /**     
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

