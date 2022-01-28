/**
 * This classs contains the gameboard as well as some useful methods to:
 * 1. print the gameboard
 * 2. update the gameboard
 * 3. print the positions on the gameboard for user reference
 * 4. reset the gameboard
 * 5. check for draw matches
 *
 * @author (Vivek Hegde)
 */
public class TTT_GameBoard
{
    private char[][] gameboard;

    /**
     * TTT_GameBoard()
     * ---------------
     * DESCRIPTION: This method is a constructor that creates a new game board for Tic-Tac-Toe.
     * PARAMS: None.
     * PRE-CONDITION: None.
     * POST-CONDITION: A new game board is created.
     */
    public TTT_GameBoard(){ //creates the board
        char[][] gameboard={{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}};

        this.gameboard=gameboard;
    }

    public char[][] getGameBoard(){ //getter method for gameboard
        return gameboard;
    }
    
    /**
     * printGameBoard()
     * ----------------
     * DESCRIPTION: This method displays the game board (with or without the player's symbols)
     * PARAMS: None
     * PRE-CONDITION: The game board MUST exist.
     * POST-CONDITION: prints the game board to the console.
     */
    public void printGameBoard(){  
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print(gameboard[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * printPositions()
     * ----------------
     * DESCRIPTION: This method prints the positions to console for the user's reference.
     * PARAMS: None.
     * PRE-CONDITION: None.
     * POST-CONDITION: Prints the positions.
     */
    public void printPositions(){  
        System.out.println();
        int count=1;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(count+" ");
                count++;
            }
            System.out.println();
        }
    }

    /**
     * reset()
     * -------
     * DESCRIPTION: This method clear all symbols from the board.
     * PARAMS: None.
     * PRE-CONDITION: None.
     * POST-CONDITION: The board is cleared.
     */
    public void reset(){
        char[][] gameboard={{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}};

        this.gameboard=gameboard;
    }
    
    /**
     * setNewBoard()
     * -------------
     * DESCRIPTION: This method places the symbol on the game board.
     * PARAMS: An integer for position, a string for player to place the valid symbol
     * and another String of player 2 for alternating between 'X' and 'O'.
     * PRE-CONDITION: Postion, player and pl2 MUST be valid
     * POST-CONDITION: The appropriate symbol gets placed on the game board.
     */
    public void setNewBoard(int position, String player, String pl2){ 
        char symbol='X';
        if(player.equals(pl2))symbol='O';

        switch (position){ //places the pice on board according to position
            case 1: gameboard[0][0]=symbol;
            break;
            case 2: gameboard[0][2]=symbol;
            break;
            case 3: gameboard[0][4]=symbol;
            break;
            case 4: gameboard[2][0]=symbol;
            break;
            case 5: gameboard[2][2]=symbol;
            break;
            case 6: gameboard[2][4]=symbol;
            break;
            case 7: gameboard[4][0]=symbol;
            break;
            case 8: gameboard[4][2]=symbol;
            break;
            case 9: gameboard[4][4]=symbol;
            break;
        }
    }

    /**
     * checkDraw()
     * -----------
     * DESCRIPTION: This is a helper method to check if the game was a "draw match".
     * PARAMS: None.
     * PRE-CONDITION: The game board must exist.
     * POST-CONDITION: Returns true if all the positions on the game board are filled
     */
    public boolean checkDraw(){ 
        if(gameboard[0][0]!=' ' && gameboard[0][2]!=' ' && gameboard[0][4]!=' ' && 
        gameboard[2][0]!=' ' && gameboard[2][2]!=' ' && gameboard[2][4]!=' ' && 
        gameboard[4][0]!=' ' && gameboard[4][2]!=' ' && gameboard[4][4]!=' ') return true;
        else return false;
    }

    public static void main(String[] args){  //for testing
        TTT_GameBoard t= new TTT_GameBoard();
        System.out.println("Testing TTT_GameBoard:\n");
        t.printGameBoard();
        System.out.println();
        t.printPositions();
        System.out.println();
        t.setNewBoard(5,"player", "player2");
        t.printGameBoard();
    }
}