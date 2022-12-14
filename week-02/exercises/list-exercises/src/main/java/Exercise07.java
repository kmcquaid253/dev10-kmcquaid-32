import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;
import java.util.List;

public class Exercise07 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Instantiate a new ArrayList<BoardGame> and call it `economicGames`.
        // 2. Loop over `games`. Add each game with the "Economic" category to `economicGames`.
        // 3. Print `economicGames`.

        //List to hold answers
        List<BoardGame> economicGames = new ArrayList<>();

        for(BoardGame toCheck : games){
            if(toCheck.getCategory().equals("Economic")){
                economicGames.add(toCheck);
            }
        }

    }
}
