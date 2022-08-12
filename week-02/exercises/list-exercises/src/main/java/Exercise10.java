import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise10 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Loop over `games` and remove any game that can be played by one person.
        for(int i = 0; i < games.size(); i--){
            BoardGame toCheck = games.get(i);
            if(toCheck.getMinPlayers() == 1){
                games.remove(i);
                //We shift the next value into position i
                //we also need to check the value
            }
        }
        // 2. Print `games` and confirm single-player games are removed.
    }
}
