import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise08 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        Exercise02.printAll(games);

        // 1. Remove the 5th and 10th game from `games`.
         games.remove(4);
         games.remove(8);
        // 2. Print `games`.
        Exercise02.printAll(games);

        //Another way we can do removals to not worry about shifting
        //remove the highest index
        games.remove(9);
        games.remove(4);
        //Print
        Exercise02.printAll(games);


    }
}
