
/**
 * This is a simple class designed to hold the information of a player
 * such as name and score.
 *
 * @author (Vivek Hegde)
 */
public class Player
{
    // instance variables 
    private String name;
    private int score;
    
    /**
     * Player()
     * --------
     * DESCRIPTION: This method is a constructor that initializes a players name and score
     * PARAMS: The player's name and score
     * PRE-CONDITION: instance variables should exist
     * POST-CONDITION: instance variables are initialized
     */
    public Player(String playerName, int playerScore){
        this.name = playerName;
        this.score = playerScore;
    }
    
    /**
     * updateBy()
     * -----------
     * DESCRIPTION: This method updates the score of the player by adding some points
     * PARAMS: number of points to be added
     * PRE-CONDITION: Player's score must be defined
     * POST-CONDITION: Player's score is updated by some points
     */
    public void updateBy(int points){
        this.score += points;
    }
    
    /**
     * update()
     * --------
     * DESCRIPTION: This method updates the player's score
     * PARAMS: integer new score
     * PRE-CONDITION: Player's score must be defined
     * POST-CONDITION: Player's score is updated to the inputed value
     */
    public void update(int sc){
        this.score = sc;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public static void main(String[] args){
        Player test = new Player ("testPlayer", 0);
        if(test.getName().equals("testPlayer") && test.getScore() == 0) System.out.println("Player successfully created!");
        else System.out.println("Fail!");
        
        test.update(5);
        if(test.getScore() == 5)System.out.println("Player Score successfully updated!");
        else System.out.println("Fail!");
    }
}
