import java.util.Scanner;
import java.util.Random;
/**
 * This class contains the game of Tic-Tac-Toe. It consists of mechanisms to keep playing the game for
 * more than one round. It also keeps a track of the scores of the two players playing the game.
 * The game can be played against either a human or a computer.
 *
 * @author (Vivek Hegde)
 */
public class TTT_Game
{
    String player1;
    String player2;
    TTT_GameBoard g= new TTT_GameBoard();
    ScoreBoard sb;
    
    /**
     * playGame()
     * -----------
     * DESCRIPTION: This method is the heart of the game. It can:
     * 1. Display the "rules" and "how to play".
     * 2. Provide a choice for the user to play against a human or a computer.
     * 3. Take user inputs for placing the symbols on the gameboard.
     * 4. Check for winners.
     * 5. Create and update the scoreboard.
     * 
     * PARAMS: NONE
     * PRE-CONDITION: The scanner class must be imported and the following methods MUST be defined:
     * 1. rules()
     * 2. isValidChoice()
     * 3. isValidLetter()
     * 4. isValidInput()
     * 5. isValidPos()
     * 6. randInt()
     * 7. checkWinner()
     * 8. getBoardPos()
     * 
     * The following classes must be defined:
     * 1. TTT_GameBoard
     * 2. ScoreBoard
     * 3. Player
     * 
     * POST-CONDITION: The user can play Tic-Tac-Toe
     */
    public void playGame(){
        System.out.println("Welcome to Tic-Tac-Toe...");
        System.out.println();
        System.out.println("This is the game board");
        g.printGameBoard();
        
        //display the rules and how to play the game
        rules();

        Scanner sc=new Scanner(System.in);

        //ask the user to choose to play against either a human or a computer
        System.out.println("Select your opponent:");
        System.out.println("1: Computer");
        System.out.println("2: Human");
        System.out.println("(Enter the number)");
        System.out.println();

        int choice = sc.nextInt();

        while(!isValidChoice(choice)){
            System.out.println("Enter a valid choice:");
            choice=sc.nextInt();
        }

        switch(choice){
            case 1 : this.player2 ="Computer";
            break;

            case 2 : this.player2 ="Human";
            break;

            default : System.out.println("Unexpected Error");
            break;
        }

        System.out.println("You are playing against a "+ player2);

        //ask for user's name for scoreboard
        System.out.println("Player 1, please enter your name:");
        this.player1 = sc.next();

        if(!player2.equals("Computer")){
            System.out.println("Player 2, please enter your name:");
            this.player2 = sc.next();
            while(player2.equals(player1)){
                System.out.println("Sorry, name already taken :( Please enter a different name:");
                this.player2 = sc.next();
            }
        }

        //create a scoreboard with player names and 0-0 scores
        sb = new ScoreBoard(player1,0,player2,0);

        boolean PlayAgain = true;
        String firstPlayer = player1;
        String secondPlayer = player2;

        //while loop to keep playing the game for as many times as the user wants
        while(PlayAgain){  
            String winner = " ";
            boolean isWinnerDeclared=false;

            while(!isWinnerDeclared){
                //define how the board is updated according to the players 
                if(firstPlayer.equals("Computer")){
                    int compPos;
                    do{
                        compPos=randInt(1,9);
                    }
                    while(!isValidPos(compPos)); //checks if a piece already exists

                    //updates the board
                    g.setNewBoard(compPos,firstPlayer,secondPlayer);
                    System.out.println();
                    g.printGameBoard();
                }
                else{
                    System.out.println("\n"+ firstPlayer +", enter your position");                

                    int playerPos=sc.nextInt();
                    //if the user does not input a valid number on the board
                    while(!isValidInput(playerPos) || !isValidPos(playerPos)){
                        System.out.println("Enter a valid input:");
                        playerPos=sc.nextInt();
                    }

                    //updates the board
                    g.setNewBoard(playerPos,firstPlayer,secondPlayer);
                    System.out.println();
                    g.printGameBoard();
                }
                
                System.out.println();
                
                // At this point a game might be over because of a draw match or the first player winning
                // so check for the condition and repeat the process done for player 1
                if(!g.checkDraw() && !checkWinner(firstPlayer,firstPlayer,secondPlayer)){
                    if(secondPlayer.equals("Computer")){
                        int compPos;
                        do{
                            compPos=randInt(1,9);
                        }
                        while(!isValidPos(compPos)); //checks if a piece already exists

                        //updates the board
                        g.setNewBoard(compPos,secondPlayer,secondPlayer);
                        System.out.println();
                        g.printGameBoard();
                    }
                    else{
                        System.out.println(secondPlayer+" , enter your position");
                        int player2Pos = sc.nextInt();
                        while(!isValidInput(player2Pos) || !isValidPos(player2Pos)){
                            System.out.println("Enter a valid position:");
                            player2Pos=sc.nextInt();
                        }

                        //updates the board
                        g.setNewBoard(player2Pos,secondPlayer,secondPlayer);
                        System.out.println();
                        g.printGameBoard();
                    }
                }
                System.out.println();

                //One round has been completed

                //Check for winners or draws

                if(checkWinner(firstPlayer,firstPlayer,secondPlayer)){
                    if(firstPlayer.equals("Computer"))System.out.println("Congratulations... You Win");
                    else System.out.println("Congratulations! "+firstPlayer+" wins this game");
                    winner = firstPlayer;
                    isWinnerDeclared=true;
                }
                else if(checkWinner(secondPlayer,firstPlayer,secondPlayer)){
                    if(secondPlayer.equals("Computer"))System.out.println("Better luck next time ;)");
                    else System.out.println("Congratulations! "+secondPlayer+" wins this game");
                    winner = secondPlayer;
                    isWinnerDeclared=true;
                }

                else {
                    if(g.checkDraw()){
                        System.out.println("*** Draw Match ***");
                        isWinnerDeclared=true;
                    }
                }
            }

            //Update the scoreboard according to the result of the game
            if(winner.equals(player1)){
                sb.p1.updateBy(1);
            }      
            else if(winner.equals(player2)){
                sb.p2.updateBy(1);
            } 
            else {
                sb.p1.updateBy(0);
                sb.p2.updateBy(0);
            }

            System.out.println("\n");
            sb.printScoreBoard(); //display the scoreboard at the end of a game
            System.out.println("\n\nDo you want to play again? (y/n)"); //ask the user if they want to play again
            char yon = sc.next().charAt(0);        

            while(!isValidLetter(yon)){
                System.out.println("Please enter a valid letter:");
                yon = sc.next().charAt(0);
            }

            if (yon == 'n'){
                PlayAgain = false;
                System.out.println("\n                                      ********* THE END *********                      ");
            }
            else {
                g.reset();
                String temp = secondPlayer;
                secondPlayer = firstPlayer;
                firstPlayer = temp;
            }
        }
    }

    /**
     * checkWinner()
     * -------------
     * DESCRIPTION: This method checks the gameboard for a winner
     * PARAMS: the player whose victory status needs to be checked, the player who 
     * played first in the game, and the second player
     * PRE-CONDITION: The gameboard must exist and the symbols on the board must be either 'X' or 'O'
     * POST-CONDITION: returns true if the player won
     */
    public boolean checkWinner(String player, String pl1, String pl2){
        char symbol='X';
        if(player.equals(pl2))symbol='O';

        if(topRow(symbol) || midRow(symbol) || botRow(symbol) || 
        leftCol(symbol) || midCol(symbol) || rightCol(symbol) ||
        diag1(symbol) || diag2(symbol)) return true;
        else return false;
    }

    /**
     * rules()
     * -------
     * DESCRIPTION: This method prints the rules and "How to Play" to the console
     * PARAMS: NONE
     * PRE-CONDITION: NONE
     * POST-CONDITION: Displays the rules and how to play the game
     */
    public void rules(){
        System.out.println("This is a 2-player game played on a 3 X 3 grid");
        System.out.println("The person who plays first is 'X' and the opponent plays 'O'");
        System.out.println("Players take turns to mark their position in empty squares using this chart:");
        g.printPositions();
        System.out.println("\nThe first player to get 3 of their marks in a sequence (horizontal,vertical or diagonal) wins");
        System.out.println("The game ends in a draw if all 9 squares are filled and no winner is declared");
        System.out.println("Good luck!\n");
    }

    /**
     * topRow(), midRow(),...., diag2()
     * --------------------------------
     * DESCRIPTION: These are a series of helper methods to checkWinner()
     * PARAMS: The symbol that needs to be located on the gameboard
     * PRE-CONDITION: The gameboard must exist
     * POST-CONDITION: returns true if three symbols of the same type align in the 
     * respective direction. For example, topRow() returns true if the top row of the gameboard
     * has 3 symbols of the same type
     */
    public boolean topRow(char symbol){
        if(g.getGameBoard()[0][0]==symbol && g.getGameBoard()[0][2]==symbol && g.getGameBoard()[0][4]==symbol) return true;
        else return false;
    }

    public boolean midRow(char symbol){
        if(g.getGameBoard()[2][0]==symbol && g.getGameBoard()[2][2]==symbol && g.getGameBoard()[2][4]==symbol) return true;
        else return false;
    }

    public boolean botRow(char symbol){
        if(g.getGameBoard()[4][0]==symbol && g.getGameBoard()[4][2]==symbol && g.getGameBoard()[4][4]==symbol) return true;
        else return false;
    }

    public boolean leftCol(char symbol){
        if(g.getGameBoard()[0][0]==symbol && g.getGameBoard()[2][0]==symbol && g.getGameBoard()[4][0]==symbol) return true;
        else return false;
    }

    public boolean midCol(char symbol){
        if(g.getGameBoard()[0][2]==symbol && g.getGameBoard()[2][2]==symbol && g.getGameBoard()[4][2]==symbol) return true;
        else return false;
    }

    public boolean rightCol(char symbol){
        if(g.getGameBoard()[0][4]==symbol && g.getGameBoard()[2][4]==symbol && g.getGameBoard()[4][4]==symbol) return true;
        else return false;
    }

    public boolean diag1(char symbol){
        if(g.getGameBoard()[0][0]==symbol && g.getGameBoard()[2][2]==symbol && g.getGameBoard()[4][4]==symbol) return true;
        else return false;
    }

    public boolean diag2(char symbol){
        if(g.getGameBoard()[0][4]==symbol && g.getGameBoard()[2][2]==symbol && g.getGameBoard()[4][0]==symbol) return true;
        else return false;
    }

    /**
     * isValidChoice()
     * ---------------
     * DESCRIPTION: This method is a helper method for playGame() that helps evaluate the user's
     * choice for playing against either a human or a computer
     * PARAMS: An integer input
     * PRE-CONDITION: NONE
     * POST-CONDITION: Returns true of the input is either 1 or 2
     */
    public boolean isValidChoice(int input){
        boolean value=false;
        if(input == 1) value = true;
        else if(input == 2) value = true;

        return value;
    }

    /**
     * isValidLetter()
     * ---------------
     * DESCRIPTION: This method is a helper method for playGame()'s play again mechanism
     * PARAMS: a character input
     * PRE-CONDITION: NONE
     * POST-CONDITION: Returns true of the input is either 'y' or 'n'
     */
    public boolean isValidLetter(char input){
        boolean value=false;
        if(input == 'y') value = true;
        else if(input == 'n') value = true;

        return value;
    }

    /**
     * isValidInput()
     * --------------
     * DESCRIPTION: This method is a helper method for playGame() to evaluate the user's
     * input for their position on the gameboard
     * PARAMS: an integer input
     * PRE-CONDITION: NONE
     * POST-CONDITION: Returns true if the input is between 1 - 9 (both inclusive) 
     */
    public boolean isValidInput(int input){
        boolean value=false;
        for(int i=1;i<10;i++){
            if(input==i) value=true;
        }

        return value;
    }

    /**
     * isValidPos()
     * -----------
     * DESCRIPTION: This method is a helper method for playGame() to evaluate if the user's
     * input for a position on the gameboard is valid
     * PARAMS: an integer input for position
     * PRE-CONDITION: the gameboard must exist
     * POST-CONDITION: returns true if the position on the gameboard has an empty space
     */
    public boolean isValidPos(int pos){
        int [] boardPos=new int[2];
        boardPos=getBoardPos(pos);
        boolean value=false;
        if(g.getGameBoard() [boardPos[0]][boardPos[1]]==' ')value=true;
        return value;
    }

    /**
     * getBoardPos()
     * -------------
     * DESCRIPTION: This method is a helper method for playGame() that helps to get the true 
     * position on the gameBoard
     * PARAMS: An integer position given by the user
     * PRE-CONDITION: The gameboard must exist and the position must be valid
     * POST-CONDITION: returns the true position on the gameboard
     */
    public int[] getBoardPos(int pos){
        int[] truePos=new int[2];
        switch (pos){ //reverses player position to actual position on gameboard
            case 1:
            truePos[0]=0;
            truePos[1]=0;
            break;

            case 2: 
            truePos[0]=0;
            truePos[1]=2;
            break;

            case 3: 
            truePos[0]=0;
            truePos[1]=4;
            break;

            case 4: 
            truePos[0]=2;
            truePos[1]=0;
            break;

            case 5: 
            truePos[0]=2;
            truePos[1]=2;
            break;

            case 6: 
            truePos[0]=2;
            truePos[1]=4;
            break;

            case 7: 
            truePos[0]=4;
            truePos[1]=0;
            break;

            case 8: 
            truePos[0]=4;
            truePos[1]=2;
            break;

            case 9: 
            truePos[0]=4;
            truePos[1]=4;
            break;
        }

        return truePos;
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
        System.out.println("Testing TTT_Game:");
        TTT_Game t=new TTT_Game();
        t.playGame();
    }
}
