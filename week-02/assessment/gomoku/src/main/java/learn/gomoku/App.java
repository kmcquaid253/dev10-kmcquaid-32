package learn.gomoku;

import learn.gomoku.game.Gomoku;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;
import ui.GameController;

import java.io.Console;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        //Display Welcome message
        System.out.println("Welcome to Gomoku");
        System.out.println("==================");

        GameController game = new GameController();

        game.run();

    }
}
