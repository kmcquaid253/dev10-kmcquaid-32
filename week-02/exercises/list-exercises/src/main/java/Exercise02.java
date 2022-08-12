import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;
import java.util.List;

public class Exercise02 {

    // 1. Create a method to print all BoardGames in an ArrayList<BoardGame>.
    // Consider making it `public` so you can use it in other exercises.
//    public static void printAll() {
//        ArrayList<BoardGame> games = GameRepository.getAll();
//
//        for (BoardGame gr : games) {
//            System.out.println(gr);
//        }
//    }

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();




        // 2. Print `games` using your "print all" method.
        printAll(games);
    }

    public static void printAll(List<BoardGame>allGames){
        for(BoardGame toPrint: allGames){
            System.out.println(toPrint);
        }
    }

}
