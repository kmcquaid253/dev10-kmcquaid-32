import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise03 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Add three new games to `games` with the `add` method.
        games.add(new BoardGame("Mario Cart", 1, 4, "Racing"));
        // 2. Print `games` after each add.
        System.out.println(games);

        games.add(new BoardGame("Scrabble", 2, 4, "Board Game"));
        System.out.println(games);

        games.add(new BoardGame("Guess Who", 2, 10, "Gambling"));
        System.out.println(games);
    }
}
