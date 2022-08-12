import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise05 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Loop over each BoardGame in `games` and print games with the "Adventure" category.
        //FILTERING

        for(BoardGame toCheck : games){
            if(toCheck.getCategory().equals("Adventure")){
                System.out.println(toCheck);
                //System.out.println(toCheck.getName());
            }
        }
    }
}
