import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * CSC-151 Scratch-Off Simulator
 *
 * A complete lottery scratch-off ticket simulator supporting three ticket types:
 * Lucky Sevens, Match Pairs, and Lucky Sevens Multiplier.
 */
public class ScratchOff {

    Scanner input = new Scanner(System.in);

    // State variables
    private static int balance;
    private static int totalSpent;
    private static int totalWinnings;
    private static Scanner scanner;

    /**
     *
     */
    public static void main(String[] args) {
        initializeGame();
        runGame();
        //printGameSummary();
        //askPlayAgain();

    }

    /**
     * Sets up starting game values
     */
    public static void initializeGame() {
        balance = 50;
        totalSpent = 0;
        totalWinnings = 0;
        scanner = new Scanner(System.in);
        System.out.println("Welcome to the CSC-151 Scratch-Off Simulator!");
        System.out.println("Starting balance: $" + balance);
    }

    /**
     * Starts up the scratch off game
     */
    public static void runGame() {
        System.out.println("\nCurrent balance: $" + balance);

        System.out.print("Do you want to buy a ticket (y/n): " );
        //boolean yesNo = getValidYesNo();

        // checks for yes to buy ticket
       while (getValidYesNo()) {
        if (canAffordTicket(balance)) {
            playRound();
        } else {
            System.out.println("Sorry you don't have enough money");
            break;
        }
    }


    }


    /**
     *
     */
    public static void playRound() {
        System.out.print("Choose ticket: (a) Lucky Sevens, (b) Match Pairs, (c) Multiplier: ");
        String input = scanner.nextLine();

            // calls Lucky Sevens
            if (input.toLowerCase().equals("a")){
                totalSpent += 2;
                System.out.println(balance);

                int[] grid = generateLuckySevensGrid();
                updateBalanceForPurchase(2);
                evaluateLuckySevens(grid);

                if (askPlayAgain() == true){
                    playRound();
                }

                //calls Match Pairs
            } else if (input.toLowerCase().equals("b")){
                balance -= 8;
                totalSpent += 8;
                System.out.println(balance);

                generateMatchPairsGrid();
                askPlayAgain();

                //calls lucky sevens weighted multiplier
            } else if (input.toLowerCase().equals("c")){
                balance -= 3;
                System.out.println(balance);

                getWeightedMultiplier();
                askPlayAgain();
            }else {
                System.out.println("Invalid input. Please try again");
                playRound();
            }
            
    }

    /**
     *
     */
    public static void printGameSummary() {
        System.out.println("--- Game Summary ---");
        System.out.println("Total money spent: " + totalSpent);
        System.out.println("Total winnings: " + totalWinnings);
        System.out.println("Final balance" + balance);
        System.out.println("Thanks for playing!");
    }

    /**
     *
     */
    public static boolean askPlayAgain() {
        System.out.print("Would you like to play again (y/n): ");
        return getValidYesNo();
    }

    /**
     * Checks if the player's current balance is sufficient to afford a ticket.
     *
     */
    public static boolean canAffordTicket(int ticketCost) {
        if (balance >= 2){
            return true;
        } else if (balance >= 3) {
            return true;
        } else if (balance >= 8){
            return true;
        }else {
            System.out.println("Sorry you don't have enough money");
            return false;
        }
    }

    /**
     * Gives a true or false response from prompt listed from other method's yes or no questions
     */
    public static boolean getValidYesNo(){
        String input = scanner.nextLine();

        if (input.toLowerCase().equals("y")){
            return true;
        } else if (input.toLowerCase().equals("n")){
            return false;
        } else {
            System.out.print("This input is invalid. Please try again: ");
            return getValidYesNo();
        }
    }

    /**
     * Used to verify number inputs if used
     */
//    public static int getValidInt(int min, int max) {
//
//        return 0;
//    }

    /**
     * Generates a 3×3 grid (stored as int[9]) for Lucky Sevens.
     * Each value is a random integer from 1 to 9.
     *
     */
    public static int[] generateLuckySevensGrid() {
        int[] LuckySevensGrid = new int[9];
        Random randInt;
        for (int i = 0; i < LuckySevensGrid.length; i++){
            randInt = new Random();
            LuckySevensGrid[i] = randInt.nextInt(9)+1;
        }
        System.out.println(Arrays.toString(LuckySevensGrid));
        return LuckySevensGrid;
    }

    /**
     * Generates a grid of 8 random integers (1–15) for Match Pairs.
     *
     */
    public static int[] generateMatchPairsGrid() {
        int[] MatchPairsGrid = new int[8];
        Random randInt;
        for (int i = 0; i < MatchPairsGrid.length; i++){
            randInt = new Random();
            MatchPairsGrid[i] = randInt.nextInt(15)+1;
        }
        System.out.println(Arrays.toString(MatchPairsGrid));
        return MatchPairsGrid;
    }

    /**
     * Returns a weighted random multiplier according to the prize rules:
     * 50% chance of 2x, 30% chance of 3x, 20% chance of 4x.
     *
     * Weighted probability means some outcomes are more likely than others.
     * You need to map ranges of random values to each multiplier:
     *
     * - 2x should occur 50% of the time (0.0 to 0.5)
     * - 3x should occur 30% of the time (0.5 to 0.8)
     * - 4x should occur 20% of the time (0.8 to 1.0)
     *
     */
    public static int getWeightedMultiplier() {
        int[] multipliers= {2, 2, 2, 2, 2, 3, 3, 3, 4, 4};
        int multiplier = (int)(Math.random() * 10);
        System.out.println("Your multiplier is: " + multipliers[multiplier]);
        return multipliers[multiplier];
    }

    /**
     * Evaluates a Lucky Sevens ticket grid and returns the prize amount.
     *
     * Prize rules (evaluated in order):
     * 1. All 9 are 7 → $500
     * 2. Any row/column/diagonal has exactly three 7s → $100
     * 3. Exactly three 7s anywhere → $20
     * 4. Exactly two 7s anywhere → $5
     * 5. Otherwise → $0
     *
     */
    public static int evaluateLuckySevens(int[] grid){
        int sevenCounter = countSevens(grid);
    

        if (sevenCounter == 9){
            return 100;
        } else if ((grid[0] == 7 && grid[4] == 7 && grid[8] == 7) || (grid[2] == 7 && grid[4] == 7 && grid[8] == 6)){
            updateBalanceForPrize(500);
            return 100;

        } else if (grid[0] == 7 && grid[1] == 7 && grid[2] == 7) {
            updateBalanceForPrize(100);
            return 100;

        } else if (grid[3] == 7 && grid[4] == 7 && grid[5] == 7){
            updateBalanceForPrize(100);
            return 100;

        } else if (grid[6] == 7 && grid[7] == 7 && grid[8] == 7){
            updateBalanceForPrize(100);
            return 100;

        } else if (sevenCounter == 3) {
            updateBalanceForPrize(20);
            return 20;

        } else if (sevenCounter == 2) {
            updateBalanceForPrize(5);
            return 5;
        } else {
            return 0;
        }
    }

    /**
     * Evaluates a Match Pairs ticket grid and returns the prize amount.
     *
     * Prize rules (evaluated in order):
     * 1. Exactly four distinct pairs → $300
     * 2. Exactly three distinct pairs → $75
     * 3. Exactly two distinct pairs → $25
     * 4. Otherwise → $0
     *
     * A pair is defined as a value that appears exactly twice in the grid.
     *
     */
    public static int evaluateMatchPairs(int[] grid) {
        return 0;
    }

    /**
     * Applies a multiplier to a base prize amount.
     * Multiplier only applies if the base prize is >= $5.
     * If the base prize is < $5, returns the base prize unchanged.
     *
     */
    public static int applyMultiplier(int basePrize, int multiplier) {
        return 0;
    }

    /**
     * Counts the number of sevens (7s) in a grid.
     *
     */
    public static int countSevens(int[] grid) {
        int sevenCounter = 0;
        for (int i = 0; i < grid.length; i++){
            if (grid[i] == 7){
                sevenCounter++;
            }
        }
        System.out.println(sevenCounter);
        return sevenCounter;
    }

    /**
     * Counts how many times a specific value appears in a grid.
     *
     */
    public static int countFrequencies(int[] grid, int value) {
        return 0;
    }

    /**
     * Displays a grid in the correct output format.
     * For Lucky Sevens and Lucky Sevens Multiplier (3×3 grid):
     * displays as 9 space-separated integers.
     *
     * For Match Pairs (8-element grid):
     * displays as 8 space-separated integers.
     *
     */
    public static void displayGrid(int[] grid, String ticketType) {
        //if (ticketType == )
    }

    /**
     * Deducts the ticket cost from the player's balance.
     * Updates totalSpent accordingly.
     *
     */
    public static void updateBalanceForPurchase(int amount) {
        balance =  balance - amount;
        System.out.println(balance);
    }

    /**
     * Adds the prize winnings to the player's balance.
     * Updates totalWinnings accordingly.
     *
     */
    public static void updateBalanceForPrize(int amount) {
        balance += amount;
        System.out.println(balance);
    }

    /**
     * Tracks the total amount spent and won across all rounds.
     *
     */
    public static void trackTotals(int spent, int winnings) {

    }

}
