import java.util.Scanner;
import java.util.Random;
/**
 * This class contains the game "Guess the Number". It consists of mechanisms to keep playing the game for
 * more than one round. It also keeps a track of the scores of the two players playing the game.
 * The game is played against another human.
 *
 * @author (Vivek Hegde)
 */
public class GuessTheNumber
{
    // instance variables 
    private String player1;
    private String player2;
    private String difficulty;

    /**
     * GuessTheNumber()
     * DESCRIPTION: No-arg constructor.
     * PARAMS: None.
     * PRE: Instance variables should be declared.
     * POST: Initializes the instance variables.
     */
    public GuessTheNumber(){
        player1 = "Player 1";
        player2 = "Player 2";
        difficulty = "Easy";
    }

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
        System.out.println("Welcome to GUESS THE NUMBER \n");
        rules();

        Scanner sc = new Scanner (System.in);
        System.out.println("Player 1, please enter your name: ");
        this.player1 = sc.next();
        System.out.println("Player 2, please enter your name: ");
        this.player2 = sc.next();
        while(player2.equals(player1)){
            System.out.println("Sorry... Name already taken! please enter a different name:");
            player2 = sc.next();
        }

        //create a scoreboard
        ScoreBoard sb = new ScoreBoard(player1,0,player2,0);

        int choice;
        boolean playAgain = true;
        while(playAgain){
            System.out.println("\n********************************** GUESS THE NUMBER ***************************************\n");
            System.out.println("Choose difficulty (enter a number): ");
            System.out.println("   1. Easy");
            System.out.println("   2. Medium");
            System.out.println("   3. Hard");
            choice = sc.nextInt();

            //check for validity of user input
            while(!isValidChoice(choice)){
                System.out.println("Enter a valid choice:");
                choice=sc.nextInt();
            }

            switch(choice){
                case 1 : this.difficulty ="Easy";
                break;

                case 2 : this.difficulty ="Medium";
                break;

                case 3 : this.difficulty ="Hard";
                break;

                default : System.out.println("Unexpected Error");
                break;
            }

            int number;
          
            String firstPlayer = player1; //for alternating who plays first
            String secondPlayer = player2;
            int upperLimit; //for keeping track of the max value a player can input
            int guess;

            int round =1;
            System.out.println("ROUND 1");
            System.out.println("----------");
            while(sb.p1.getScore() < 50 && sb.p2.getScore() < 50){
                if(difficulty.equals("Easy")){
                    number = randInt(1,2);
                    upperLimit = 2;          
                }
                else if(difficulty.equals("Medium")){
                    number = randInt(1,3);
                    upperLimit = 3;  
                }
                else if(difficulty.equals("Hard")){
                    number = randInt(1,4);
                    upperLimit = 4;  
                }
                else {
                    number = 0;
                    upperLimit = 0;  
                }

                if(number != 0){
                    System.out.println("\nNumber was set successfully! Time to play...");
                }

                System.out.println(firstPlayer+", What is your guess?");
                guess = sc.nextInt();
                while(guess < 1 || guess > upperLimit){
                    System.out.println("Number does not exist in this mode of difficulty. Enter a valid number:");
                    guess = sc.nextInt();
                }

                if(guess == number){
                    System.out.println("CORRECT!! "+firstPlayer+" received 10 points.");
                    if(firstPlayer.equals(player1))sb.p1.updateBy(10);
                    else sb.p2.updateBy(10);
                }
                else{
                    System.out.println("OOPS!! Incorrect guess :("); //pass the chance to the next player
                    System.out.println("Opportunity passed to "+ secondPlayer+" for 5 points!\n");
                    System.out.println(secondPlayer+", What is your guess?");
                    guess = sc.nextInt();
                    while(guess < 1 || guess > upperLimit){
                        System.out.println("Number does not exist in this mode of difficulty. Enter a valid number:");
                        guess = sc.nextInt();
                    }

                    if(guess == number){
                        System.out.println("CORRECT!! "+secondPlayer+" received 5 points.");
                        if(secondPlayer.equals(player1))sb.p1.updateBy(5);
                        else sb.p2.updateBy(5);
                    }
                    else{
                        System.out.println("OOPS!! Incorrect guess :(");
                        System.out.println("Nobody received any points.");
                    }
                }

                //display scores
                if(sb.p1.getScore()>=50){
                    System.out.println();
                    sb.printScoreBoard();
                    System.out.println();
                }

                if(secondPlayer.equals(player1)){
                    System.out.println();
                    sb.printScoreBoard();
                    System.out.println();
                    round++;
                    System.out.println("\n\nROUND "+round);
                    System.out.println("----------");
                }

                //alternate the first player
                String temp = secondPlayer;
                secondPlayer = firstPlayer;
                firstPlayer = temp;
            }

            if(sb.p1.getScore()>=50){
                System.out.println("Congratulations!! "+ player1+" wins...");
            }
            else {
                System.out.println("Congratulations!! "+ player2+" wins...");
            }

            System.out.println("\n********************************* GAME OVER ******************************************\n");

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
     * rules()
     * --------
     * DESCRIPTION: This method displays the rules and "How to Play" the game.
     * PARAMS: None
     * PRE-CONDITION: None
     * POST-CONDITION: Displays the rules to the console
     */
    public void rules(){
        System.out.println("This is a 2-player game played against another human.");
        System.out.println("The game has 3 modes of difficulty");
        System.out.println("1. Easy i.e., Guess the number between 1 and 2 (both inclusive)");
        System.out.println("2. Medium i.e., Guess the number between 1 and 3 (both inclusive)");
        System.out.println("3. Hard i.e., Guess the number between 1 and 4 (both inclusive)");
        System.out.println("Each player will have one opportunity to guess the correct number according to the difficulty.");
        System.out.println("On guessing correctly, the player gets 10 points.");
        System.out.println("If the player does not guess correctly they get 0 points and the opportunity is passed to the opponent");
        System.out.println("If the opponent guesses correctly for a passed opportunity, they get 5 points.");
        System.out.println("The first player to reach atleast 50 points wins the game.");
        System.out.println("Good luck!\n");
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
     * isValidChoice()
     * ---------------
     * DESCRIPTION: This is a helper method used to validate the users choice in playGame().
     * PARAMS: an integer input.
     * PRE-CONDITION: None.
     * POST-CONDITION: Returns true if the choice is valid.
     */
    public boolean isValidChoice(int input){
        boolean value=false;

        int [] choiceArray = new int [input];

        for(int i=0; i<input; i++){
            choiceArray[i] = i+1;
        }

        for(int i=0;i<input;i++){
            if(choiceArray[i] == input){
                value = true;
                break;
            }
        }
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

    public static void main(String [] args){
        GuessTheNumber g = new GuessTheNumber();
        g.playGame();
    }
}
