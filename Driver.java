import java.util.Scanner;
/**
 * This is a driver class for playing the following three games
 * 1. Guess the Number
 * 2. Rock, Paper, Scissors
 * 3. Tic-Tac-Toe
 *
 * @author (Vivek Hegde)
 */
public class Driver
{
    public static void main(String args[]){
        TTT_Game t = new TTT_Game();
        RPS_Game r = new RPS_Game();
        GuessTheNumber g = new GuessTheNumber();

        Scanner key = new Scanner(System.in);
        boolean playAgain = true;
        int choice;
        while(playAgain){
            System.out.println("********************************************************************************************");
            System.out.println("********                                MAIN MENU                                ***********");
            System.out.println("********************************************************************************************");
            System.out.println("\nChoose a game you want to play:");
            System.out.println("1. Guess the Number");
            System.out.println("2. Rock, Paper, Scissors");
            System.out.println("3. Tic-Tac-Toe");
            System.out.println("4. EXIT");

            choice = key.nextInt();
            while(!isValidInput(choice)){
                System.out.println("Please enter a valid input");
                choice = key.nextInt();
            }

            System.out.println("\n");
            switch (choice){
                case 1: g.playGame();
                System.out.println("\nReturning to MAIN MENU....");
                break;

                case 2: r.playGame();
                System.out.println("\nReturning to MAIN MENU....");
                break;

                case 3: t.playGame();
                System.out.println("\nReturning to MAIN MENU....");
                break;
                
                case 4: playAgain = false;
                System.out.println("THANK YOU FOR PLAYING :)");
                break;

                default: System.out.println("ERROR");
                break;
            }
        }
    }

    public static boolean isValidInput(int n){
        boolean value = false;
        for( int i=1; i<5;i++){
            if(i == n) value = true;
        }
        return value;
    }
}
