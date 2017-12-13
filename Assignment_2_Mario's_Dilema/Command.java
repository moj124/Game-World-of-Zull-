import java.util.ArrayList;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Command
{
    private String commandWord;             // stores the first command word.
    private String secondWord;              // stores the second command word
    private ArrayList<String> otherWords;   // holds the name of the object/thing.

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognised.
     * @param secondWord The second word of the command.
     */
    public Command(String firstWord, ArrayList<String> otherWords)
    {
        commandWord = firstWord;
        this.otherWords = new ArrayList();
        
        // get each name part in the array list otherWords.
        for (String word: otherWords){
            // copies the contents of the arraylist into this class's properties.
            this.otherWords.add(word);
        }
    }
    
        /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognised.
     * @param secondWord The second word of the command.
     * @param othersWords The array list holding the name of the object/thing.
     */
    public Command(String firstWord, String secondWord, ArrayList<String> otherWords)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
        this.otherWords = new ArrayList();
        
        // get each name part in the array list otherWords.
        for (String word: otherWords){
            // copies the contents of the arraylist into this class's properties.
            this.otherWords.add(word);
        }
    }
    
        /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognised.
     * @param secondWord The second word of the command.
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;

    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return The command word.
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * @return The third word of this command. Returns null if there was no
     * third word.
     */
    public ArrayList<String> getOtherWords()
    {
        return otherWords;
    }

    /**
     * @return The name of the character/item.
     */
    public String getObjectName()
    {
        String objectName= "";
        if(otherWords!= null){
            for (int i = 0; i < otherWords.size();i++){
                if (i == otherWords.size()-1){
                    objectName += otherWords.get(i);
                }
                else {
                    objectName += otherWords.get(i) + " ";
                }
            }
        }
        else{
            objectName = secondWord;
        }
        return objectName;
    }

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

