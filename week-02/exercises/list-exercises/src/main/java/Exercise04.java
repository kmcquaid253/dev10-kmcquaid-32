import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise04 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Instantiate a new ArrayList<BoardGame>.
        ArrayList<BoardGame> spanishGames = new ArrayList<>();
        // 2. Add three BoardGames to the new list.
        spanishGames.add(new BoardGame("Loteria", 2, 15, "Gambling"));
        spanishGames.add(new BoardGame("Escalera", 2, 6, "Puzzle"));
        spanishGames.add(new BoardGame("Canicas", 2, 8, "Skill"));
        // 3. Print the new list.
        System.out.println(spanishGames);
        // 4. Add items in the new list to `games` with the `addAll` method.
        games.addAll(spanishGames);
        // 5. Print `games`.
        System.out.println(games);
    }
}
