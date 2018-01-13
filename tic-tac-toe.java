package f17comp1030dec13;

import java.util.Scanner;

/**
 *
 * @author jwright
 */
public class TicTacToe {
    private static String[][] gameBoard;
    private static String currentPlayer;
    
    public static void main(String[] args)
    {
        gameBoard = new String[3][3];
        currentPlayer = "X";
        
        initializeGameBoard();
        
        do
        {
            displayGameBoard();
            makeMove();
        } while (!gameWon());
        
        displayGameBoard();
        
        
    } //end of main method
    
    
    /**
     * This method will check to see if there are 3 characters in a row
     * of the same value.  
     * @return true if the 3 characters match
     */
    public static boolean gameWon()
    {
        //check for a win on a row
        for (int row=0; row<gameBoard.length; row++)
        {
            if (gameBoard[row][0].equals(gameBoard[row][1]) 
                   && gameBoard[row][1].equals(gameBoard[row][2])
                   && !gameBoard[row][0].equals(" "))
                return true;
        }
        
        //check for a win on a col
        for (int col=0; col<gameBoard[0].length; col++)
        {
            if (gameBoard[0][col].equals(gameBoard[1][col]) 
                    && gameBoard[1][col].equals(gameBoard[2][col])
                    && !gameBoard[0][col].equals(" "))
                return true;
        }
        
        //check for a win upper left to lower right
        if (gameBoard[0][0].equals(gameBoard[1][1]) 
                && gameBoard[1][1].equals(gameBoard[2][2])
                && !gameBoard[0][0].equals(" "))
            return true;

        //check for a win lower left to upper right
        if (gameBoard[2][0].equals(gameBoard[1][1]) 
                && gameBoard[1][1].equals(gameBoard[0][2])
                && !gameBoard[2][0].equals(" "))
            return true;

        //check for a tie
        if (gameBoardFull())
            return true; 
        
        return false;
    }
    
    /**
     * This method will check to see if there are any spaces left in
     * the gameBoard. 
     * @return true if the board is full, false if there is a space left
     */
    public static boolean gameBoardFull()
    {
        for (int row=0; row<gameBoard.length; row++)
        {
            for (int col=0; col<gameBoard[row].length; col++)
               if (gameBoard[row][col].equals(" "))
                   return false;
        }
        
        return true;
    }
    
    
    
    /**
     * This method will prompt the user to enter row and column
     * positions, check if the space is in the grid and available, then
     * make the move
     */
    public static void makeMove()
    {
        int row, col;
        do
        {
            Scanner keyboard = new Scanner(System.in);
            //prompt the user for a row
            System.out.printf("Player %s, enter a row (1-3): ", currentPlayer);
            row = keyboard.nextInt();
            
            //prompt the user for a col
            System.out.printf("Player %s, enter a col (1-3): ", currentPlayer);
            col = keyboard.nextInt();
            
        } while (!validMove(--row, --col));
        
        //add the move to the gameBoard
        gameBoard[row][col]=currentPlayer;
        
        //change the current player the other player
        if (currentPlayer.equals("X"))
            currentPlayer="O";
        else
            currentPlayer="X";
    } //end of makeMove() 
    
    /**
     * This method will validate the following:
     * -row is in the range 0-2
     * -col is in the range 0-2
     * -if there is a " " character, the position is available
     */
    public static boolean validMove(int row, int col)
    {
        if (row<0 || row>2 || col<0 || col>2)
        {
            System.out.println("Sorry, row and columns must be in the range 1-3");
            return false;
        }
        
        //we know that it is a position inside the gameBoard
        if (gameBoard[row][col].equals(" "))
            return true;
        else
        {
            System.out.println("Sorry that position is already taken.");
            return false;
        }
            
    }
    
    /**
     * This method will display the gameBoard to the console as characters
     */
    public static void displayGameBoard()
    {
        for (int row=0; row<gameBoard.length; row++)
        {
            for (int col=0; col<gameBoard[row].length; col++)
            {
                if (col < 2)
                    System.out.printf(" %s |", gameBoard[row][col]);
                else
                    System.out.printf(" %s %n", gameBoard[row][col]);
            }
            if (row <2)
                System.out.println("------------");
        }
    }
    
    /**
     * This method will put a space character " " in every
     * position of the gameboard
     */
    public static void initializeGameBoard()
    {
        for (int row=0; row<gameBoard.length; row++)
        {
            for (int col=0; col<gameBoard[row].length; col++)
                gameBoard[row][col]=" ";
        }
    }
}
