package ui;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;


import java.util.List;
import java.util.Scanner;

public class GameController {
    Scanner console = new Scanner(System.in);
    Gomoku game;

    public void run() {
        do {
            setup();
            playGame(game);
        } while (playAgain());

        System.out.println("Goodbye!");

    }

    private void setup() {

        //Set player 1  and player 2
        Player p1 = getPlayer(1);
        Player p2 = getPlayer(2);

        //Instantiate a Gomoku object using the two `Player` objects as arguments.
        game = new Gomoku(p1, p2);

        System.out.println("(Randomizing)\n");
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
                    String humanPlayerOneName = readRequiredString("\nPlayer " + whichPlayer + ", enter your name: ");
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

    private void playGame(Gomoku game) {
        Result result = null;
        while (!game.isOver()) {
            Player currentPlayer = game.getCurrent();
            result = getPlayerMove(game, currentPlayer);
            printBoard();
        }

        if (result.getMessage().equals("Game ends in a draw")) {
            System.out.println("Game is a draw");
        } else {
            Player winner = game.getWinner();
            System.out.printf("%s wins! %n%n", winner.getName());
        }
    }

    private void printBoard() {

        final int COLUMN = 15;
        final int ROW = 15;

        //print top row
        System.out.print("  ");
        for (int col = 0; col < Gomoku.WIDTH; col++) {
            System.out.printf(" %02d ", col + 1);
        }
        System.out.println();

        for (int r = 0; r < ROW; r++) {
            System.out.printf("%02d", r + 1);
            for (int c = 0; c < COLUMN; c++) {

                Stone localStone = findStone(r, c);

                if (localStone == null) {
                    System.out.print("  - ");
                } else if (localStone.isBlack()) {
                    System.out.print("  X ");
                } else {
                    System.out.print("  O ");
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

    private Result getPlayerMove(Gomoku game, Player currentPlayer) {
        Result result = null;
        Stone stone;

        do {
            System.out.printf("%n%s's turn %n", currentPlayer.getName());

            stone = currentPlayer.generateMove(game.getStones());//Getting concrete stone from user
            if (stone == null) {//human
                int row = readInt("Please enter the row: ", 1, 15) - 1;
                int col = readInt("Please enter a column: ", 1, 15) - 1; //subtracting 1 because it's 0-indexed under the hood
                stone = new Stone(row, col, game.isBlacksTurn());
            }
            //No need to check on random
            System.out.printf("Stone placed at row %s and column %s%n", stone.getRow() + 1, stone.getColumn() + 1); // outputs row and column of random

            //Place
            result = game.place(stone);
            //validate result
            if (!result.isSuccess()) {
                System.out.printf("%n[ERROR] : %s%n%n", result.getMessage());
            }
        } while (!result.isSuccess());

        return result;
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
