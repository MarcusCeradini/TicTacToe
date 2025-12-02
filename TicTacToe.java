import java.util.Scanner;

/**
 * CSC-151 Scratch-Off Simulator
 *
 * A complete lottery scratch-off ticket simulator supporting three ticket types:
 * Lucky Sevens, Match Pairs, and Lucky Sevens Multiplier.
 */
public class ScratchOff {

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
        //getValidYesNo();
    }

    /**
     * Sets up starting game values
     */
    public static void initializeGame() {
        balance = 50;
        totalSpent = 0;
        totalWinnings = 0;
        scanner  = new Scanner(System.in);
        System.out.println("Welcome to the CSC-151 Scratch-Off Simulator!");
        System.out.println("Starting balance: $" + balance);
    }

    /**
     *
     */
    public static void runGame() {
        String UserChoice;
        System.out.println("\nCurrent balance: $" + balance);
        System.out.print("Do you want to buy a ticket (y/n): ");
        boolean yesNo = getValidYesNo();
        System.out.println(yesNo);
        UserChoice = scanner.nextLine();
        if(getValidYesNo() == true){
            System.out.print("Choose Ticket " + canAffordTicket(balance) + ": ");
        } else {
            return;
        }
    }

    /**
     *
     */
    public static void playRound() {
        System.out.println("");
    }

    /**
     *
     */
    public static void printGameSummary() {

    }

    /**
     *
     */
    public static boolean askPlayAgain() {
        System.out.print("Would you like to play again: " + getValidYesNo());
        //getValidYesNo();
        return false;
    }

    /**
     * Checks if the player's current balance is sufficient to afford a ticket.
     *
     */
    public static boolean canAffordTicket(int ticketCost) {
        if (ticketCost >= 8){
            System.out.print("(a) Lucky Sevens, (b) Match Pairs, (c) Multiplier");
        } else if (ticketCost >= 3) {
            System.out.print("(a) Lucky Sevens, (b) Match Pairs, (c) Multiplier");
        } else if (ticketCost >= 2){
            System.out.print("(a) Lucky Sevens, (b) Match Pairs, (c) Multiplier");
        } else {
            System.out.print("Sorry You can't afford this ticket");
        }
        return false;
    }

    /**
     *
     */
    public static boolean getValidYesNo() {
        Scanner input = new Scanner(System.in);
        String yesOrNo;

        yesOrNo = input.nextLine();
        System.out.println(yesOrNo);

        while(!yesOrNo.toLowerCase().equals("y") || !yesOrNo.toLowerCase().equals("n")){
            yesOrNo = input.next();
        }

        if (yesOrNo.toLowerCase().equals("y")){
            System.out.print("Do you want to buy a ticket: y");
            return true;
        } else if (yesOrNo.toLowerCase().equals("n")) {
            System.out.print("Do you want to buy a ticket: n");
            return false;
        }
        return true;
    }

    /**
     *
     */
    public static int getValidInt(int min, int max) {
        return 0;
    }

    /**
     * Generates a 3×3 grid (stored as int[9]) for Lucky Sevens.
     * Each value is a random integer from 1 to 9.
     *
     */
    public static int[] generateLuckySevensGrid() {
        return new int[9];
    }

    /**
     * Generates a grid of 8 random integers (1–15) for Match Pairs.
     *
     */
    public static int[] generateMatchPairsGrid() {
        return new int[8];
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
        return 0;
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
    public static int evaluateLuckySevens(int[] grid) {
        return 0;
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
        return 0;
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

    }

    /**
     * Deducts the ticket cost from the player's balance.
     * Updates totalSpent accordingly.
     *
     */
    public static void updateBalanceForPurchase(int amount) {

    }

    /**
     * Adds the prize winnings to the player's balance.
     * Updates totalWinnings accordingly.
     *
     */
    public static void updateBalanceForPrize(int amount) {

    }

    /**
     * Tracks the total amount spent and won across all rounds.
     *
     */
    public static void trackTotals(int spent, int winnings) {

    }

}
