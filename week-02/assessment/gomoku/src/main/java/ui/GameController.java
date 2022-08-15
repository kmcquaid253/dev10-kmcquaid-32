package ui;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;


import java.util.Scanner;

public class GameController {
    Scanner console = new Scanner(System.in);
    Gomoku game;

    public void run() {
        setup();
        play();
        playAgain();
    }

    private void setup() {

        Player p1 = getPlayer(1);
        Player p2 = getPlayer(2);

        System.out.println("(Randomizing)\n");

        //Instantiate a Gomoku object using the two `Player` objects as arguments.
        game = new Gomoku(p1, p2);


        System.out.println(game.getCurrent().getName() + " goes first.\n");

    }

    public Player getPlayer(int whichPlayer) {
        String input;

        Player toReturn = null;

        do {
            System.out.println("Player " + whichPlayer + " is:");
            System.out.println("1. Human");
            System.out.println("2. Random Player");
            System.out.print("Select [1-2]: ");
            input = console.nextLine();


            switch (input) {
                case "1":
                    //If human, collect a name from the user.
                    System.out.print("\nPlayer " + whichPlayer + ", enter your name: ");
                    String humanPlayerOneName = console.nextLine();
                    //use name to instantiate a `HumanPlayer`.
                    toReturn = new HumanPlayer(humanPlayerOneName);
                    break;
                case "2":
                    //If random, instantiate a `RandomPlayer`. A random player's name is auto-generated.
                    toReturn = new RandomPlayer();
                    System.out.println("\nPlayer " + whichPlayer + ", your name is: " + toReturn.getName() + '\n');
                    break;
                default:
                    System.out.println("\nI don't understand " + input + "." + '\n');
                    break;
            }
        } while (!input.equals("1") && !input.equals("2"));
        return toReturn;
    }

    private void printBoard() {

        final int COLUMN = 15;
        final int ROW = 15;

        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COLUMN; c++) {

                Stone localStone = findStone(r, c);

                if (localStone == null) {
                    System.out.print("-");
                } else if (localStone.isBlack()) {
                    System.out.print("x");
                } else {
                    System.out.print("o");
                }
            }
            System.out.println();
        }
    }


    private Stone findStone(int targetRow, int targetCol) {
        for (Stone toCheck : game.getStones()) {
            if (toCheck.getRow() == targetRow && toCheck.getColumn() == targetCol) {
                return toCheck;
            }
        }
        return null;
    }


    private void play() {

        Result result;
        Stone stone;
        while(!game.isOver()){
////          - display board
            printBoard();
//            - display current player
            System.out.println(game.getCurrent().getName() + " turn.");

//            - generate a stone from the current player
                game.getCurrent().generateMove(game.getStones());

//            - place the stone and collect the result
            game.place(game.getCurrent().generateMove(game.getStones()));
            int row = readInt("Please enter the row: ", 1, 15) - 1;
            int col = readInt("Please enter a column: ", 1, 15) - 1; //subtracting 1 because it's 0-indexed under the hood
//
//            - display result
            game.getStones();

        } //repeat until game over
//            - display win or draw



//            - uses: `printBoard`, `readInt` (for row and column)

    }


    private String readRequiredString(String message) {

        Scanner console = new Scanner(System.in);
        String input;

        do {
            System.out.print(message);
            input = console.nextLine();
        } while (input.isBlank());

        return input;
    }


    private int readInt(String message, int min, int max) {
        int input;

        do {
            input = Integer.parseInt(readRequiredString(message));
        } while (input < min || input > max);

        return input;
    }

    private boolean playAgain() {
        /*
            - asks the user if they want to play again
            - returns `true` if yes, `false` for any other input
            - may use: `readRequiredString`
         */
        boolean repeat = false;

        String input = readRequiredString("Play again? [y/n]: ");

        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            repeat = true;
        } else {
            repeat = false;
        }
        return repeat;
    }




}
