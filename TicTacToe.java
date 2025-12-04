import java.util.Scanner;

public class TicTacToe {
    public static Scanner input = new Scanner(System.in);
    public static char[][] board = new char[3][3];

    public static void main(String[] args) {
        initializeBoard();
        char currentPlayer = 'X';
        boolean gameOver = false;

        // Main game loop
        while (!gameOver) {
            printBoard();

            // Get player move
            System.out.println("Player " + currentPlayer + ", enter row (0-2): ");
            int row = input.nextInt();
            System.out.println("Player " + currentPlayer + ", enter column (0-2): ");
            int col = input.nextInt();

            // REVIEW 1: The program places the mark and checks if it’s valid.
            boolean validMove = placeMark(row, col, currentPlayer);
            if (!validMove) {
                System.out.println("Invalid move, try again.");
                continue; // skip rest of loop and ask again
            }

            // REVIEW 2: The program checks if the current player has won.
            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameOver = true;
            }
            // REVIEW 3: The program checks if the board is full, meaning a draw.
            else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                gameOver = true;
            }
            // REVIEW 4: The program switches to the other player for the next turn.
            else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    // Initializes the board with spaces
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Prints the board with grid lines
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // TODO 5: Complete this method
    // Purpose: Place the player's mark on the board if the space is empty.
    // Return true if successful, or false if the move is invalid.
    public static boolean placeMark(int row, int col, char mark) {
        // makes sure input says within bounds
        if (row > 2 || col > 2 || row < 0 || col < 0) return false;
        // checks if inputted submition is replacing new submition
        if (board[row][col] != 'X' && board[row][col] != 'O') {
            board[row][col] = mark;
            return true;
        } else{
            return false;
        }
    }

    // TODO 6: Complete this method
    // Purpose: Return true if the given player has three in a row.
    // Check all rows, columns, and both diagonals.
    public static boolean checkWin(char mark) {
        for (char[] chars : board) {
            //checks row wins
            if (chars[0] == mark && chars[1] == mark && chars[2] == mark) {
                return true;
            }
        }
        //checks column wins
        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == mark && board[1][col] == mark && board[2][col] == mark) {
                return true;
            }
        }
        //checks 1 diagonal
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark){
            return true;
        }
        //checks only other diagonal
        else return board[1][0] == mark && board[1][1] == mark && board[0][2] == mark;
    }

    // TODO 7: Complete this method
    // Purpose: Return true if the board is full (no spaces left).
    public static boolean isBoardFull() {
        for (int rowchars = 0; rowchars < board.length; rowchars++) {
            for (int col = 0; col < board.length; col++) {
                if (board[rowchars][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
