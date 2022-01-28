
/**
 * This class contains the score board to keep track of the progress
 * of 2 players. This class also has the following features:
 * 1. Set the scoreboard with relevant player names and scores
 * 2. Prints the scoreboard to the console
 * 3. Reset the scoreboard
 *
 * @author (Vivek Hegde)
 */
public class ScoreBoard
{
    // instance variables
    public Player p1;
    public Player p2;

    /**
     * ScoreBoard()
     * -----------
     * DESCRIPTION: This method is a no-arg constructor.
     * PARAMS: NONE
     * PRE-CONDITION: Players should be declared.
     * POST-CONDITION: A score board is created with default names and '0' scores.
     */
    public ScoreBoard()
    {
        this.p1 = new Player("Player 1",0);
        this.p2 = new Player("Player 2",0);
    }

    /**
     * ScoreBoard()
     * -----------
     * DESCRIPTION: This method is constructor.
     * PARAMS: (player1 name, player1 score, player2 name, player2 score)
     * PRE-CONDITION: Players should be declared.
     * POST-CONDITION: A score board is created with names and scores provided in the argument.
     */
    public ScoreBoard(String p1Name, int p1Score, String p2Name, int p2Score)
    {
        this.p1 = new Player(p1Name,p1Score);
        this.p2 = new Player(p2Name,p2Score);
    }
    
   /**
     * reset()
     * -------
     * DESCRIPTION: This method resets the scores to 0 - 0
     * PARAMS: NONE
     * PRE-CONDITION: The helper method "update()" should exist.
     * POST-CONDITION: The score is reset to 0 - 0
     */
    public void reset(){
        this.p1.update(0);
        this.p2.update(0);
    }

    /**
     * printScoreBoard()
     * -----------------
     * DESCRIPTION: This method prints the score board
     * PARAMS: NONE
     * PRE-CONDITION: A score board must exist
     * POST-CONDITION: The score board is displayed on the console
     */
    public void printScoreBoard(){
        System.out.println("**********SCORE BOARD**********\n");
        
        int v1 = 30 - p1.getName().length();
        System.out.printf(p1.getName()+"%"+v1+"s",p1.getScore());
        
        int v2 = 30 - p2.getName().length();
        System.out.printf("\n"+p2.getName()+"%"+v2+"s",p2.getScore());
    }

    public static void main(String[] args){
        ScoreBoard test = new ScoreBoard ("Human",0,"Robot",0);
        System.out.println("\n");
        test.printScoreBoard();
        test.p1.update(5);
        System.out.println("\n");
        test.printScoreBoard();
    }
}
