import java.util.Scanner;
import java.util.Random;
/**
 * This class contains the game of Rock, Paper, Scissors. It consists of mechanisms to keep playing the game for
 * more than one round. It also keeps a track of the scores of the two players playing the game.
 * The game is played against a computer.
 *
 * @author (Vivek Hegde)
 */
public class RPS_Game
{   
    String player;
    ScoreBoard sb;    

    /**
     * playGame()
     * -----------
     * DESCRIPTION: This method is the heart of the game. It can:
     * 1. Display the "rules" and "how to play".
     * 2. Determine winners of a game.
     * 3. Create and update the scoreboard.
     * 
     * PARAMS: None
     * PRE-CONDITION: The scanner class must be imported and the following methods MUST be defined:
     * 1. rules()
     * 2. winnerBetween() 
     * 3. convertChoice()
     * 4. isValidChoice()
     * 5. isValidLetter()
     * 6. randInt()
     * 
     * The following classes must exist
     * 1. Player
     * 2. ScoreBoard
     * 
     * POST-CONDITION: The user can play Rock, Paper, Scissors.      
     */
    public void playGame(){
        // Introduce the rules and "How to Play" the game
        System.out.println("Welcome to Rock, Paper, and Scissors\n");
        rules();

        //ask the user for name to create a score board.
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter your name:");
        this.player = sc.next();

        sb = new ScoreBoard(player,0,"Computer",0);

        boolean playAgain = true;
        String playerInput;
        String computerInput;
        String winner;

        //A "while" loop to continue playing the game
        while(playAgain){
            int round = 1;
            System.out.println("\n***********************************ROCK, PAPER, SCISSORS********************************\n");

            while(sb.p1.getScore() < 5 && sb.p2.getScore() < 5){
                
                System.out.println("\nROUND "+round);
                System.out.println("---------\n");
                System.out.println("Choose your input:");
                System.out.println("1. Rock");
                System.out.println("2. Paper");
                System.out.println("3. Scissors");

                //take integer input to make the game user-friendly
                int choice = sc.nextInt();
                
                //check for validity of the user's input
                while(!isValidChoice(choice)){
                    System.out.println("Please enter a valid choice (number):");
                    choice = sc.nextInt();
                }

                //convert numerical input to its respective "action"
                playerInput = convertChoice(choice);
                computerInput = convertChoice(randInt(1,3));

                System.out.println("You chose "+ playerInput+"... The Computer chose "+ computerInput+"...\n");

                //check for winner and display the result
                winner = winnerBetween(playerInput,computerInput);

                if(winner.equals(player)){
                    System.out.println(playerInput+" beats "+computerInput+". You win this round!");
                    sb.p1.updateBy(1);
                }
                else if(winner.equals("Computer")){
                    System.out.println(computerInput+" beats "+playerInput+". Computer wins this round :(");
                    sb.p2.updateBy(1);
                }
                else {
                    System.out.println("This round is a tie!");
                }
                round++;
                System.out.println();
                sb.printScoreBoard();
                System.out.println();
            }
            
            System.out.println("\n**************************** GAME OVER ****************************\n\n");
            if(sb.p1.getScore() == 5) System.out.println("Congratulations!! YOU WIN :)");
            if(sb.p2.getScore() == 5) System.out.println("Better luck next time ;)");
            
            //ask the user if they want to play again
            System.out.println("\n\nDo you want to play again? (y/n)");
            char yon = sc.next().charAt(0);        

            while(!isValidLetter(yon)){
                System.out.println("Please enter a valid letter:");
                yon = sc.next().charAt(0);
            }

            if (yon == 'n'){
                playAgain = false;
                System.out.println("\n                                      ********* THE END *********                      ");
            }
            else {
                sb.reset();
            }
        }
    }

    /**
     * winnerBetween()
     * ----------------
     * DESCRIPTION: This method takes in the "actions" of the player and the computer, and determines a winner.
     * PARAMS: 2 string parameters for player's action and the computer's action
     * PRE-CONDITION: The parameters must be valid (Rock, Paper or Scissors)
     * POST-CONDITION: Returns the winner as a String.    
     */
    public String winnerBetween(String pInput, String cInput){
        if(pInput.equals("Rock")){
            if(cInput.equals("Rock")) return "Draw";
            if(cInput.equals("Scissors")) return player;
        }
        else if (pInput.equals("Paper")){
            if(cInput.equals("Paper")) return "Draw";
            if(cInput.equals("Rock")) return player;
        }
        else if(pInput.equals("Scissors")){
            if(cInput.equals("Scissors")) return "Draw";
            if(cInput.equals("Paper")) return player;
        }
        return "Computer";
    }

    /**
     * rules()
     * --------
     * DESCRIPTION: This method displays the rules and "How to Play" the game.
     * PARAMS: None
     * PRE-CONDITION: None
     * POST-CONDITION: Displays the rules to the console
     */
    public void rules(){
        System.out.println("There are 3 simple rules:");
        System.out.println("   1. Rock beats Scissors");
        System.out.println("   2. Scissors beat Paper");
        System.out.println("   3. Paper beats Rock\n");
        System.out.println("You play against the computer and select your input by entering a number when prompted.");
        System.out.println("The winner of the round gets 1 point. The first to get 5 points wins the game.");
        System.out.println("Good luck! :)\n");
    }

    /**
     * convertChoice()
     * ---------------
     * DESCRIPTION: This is a helper method to convert the user's input to its corresponding "action" String.
     * PARAMS: An integer input variable.
     * PRE-CONDITION: The parameter should be valid (1, 2 or 3)
     * POST-CONDITION: An "action" String is returned.
     */
    public String convertChoice(int input){
        String value = "";
        if (input == 1) value = "Rock";
        if (input == 2) value = "Paper";
        if (input == 3) value = "Scissors";
        return value;
    }

    /**
     * isValidChoice()
     * ---------------
     * DESCRIPTION: This is a helper method used to validate the users choice in playGame().
     * PARAMS: an integer input.
     * PRE-CONDITION: None.
     * POST-CONDITION: Returns true if the choice is valid.
     */
    public boolean isValidChoice(int input){
        boolean value=false;
        if(input == 1) value = true;
        else if(input == 2) value = true;
        else if(input == 3) value = true;

        return value;
    }

    /**
     * isValidLetter()
     * ---------------
     * DESCRIPTION: This is a helper method used to validate the users choice in playGame() to "play again?".
     * PARAMS: an integer input.
     * PRE-CONDITION: None.
     * POST-CONDITION: Returns true if the choice is valid.
     */
    public boolean isValidLetter(char input){
        boolean value=false;
        if(input == 'y') value = true;
        else if(input == 'n') value = true;

        return value;
    }

    /**
     * randInt()
     * ----------
     * DESCRIPTION: This method will return a random integer in the range min...max (inclusive).
     * PARAMS: NONE
     * PRECONDITION: min and max are not negative. Don't forget to do import java.util.Random.
     * POSTCONDITION: randInt(1,10) would return a random integer between 1 and 10, the lowest possible being
     * 1 and the highest possible being 10.
     */
    public static int randInt(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args){
        //System.out.println("Testing Rock, Paper, Scissors:");
        RPS_Game test = new RPS_Game();
        test.playGame();
    }
}
