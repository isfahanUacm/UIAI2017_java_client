/**
 * Created by MSN on 5/16/2017.
 */
public class Pop_strategy {
    public static void execte(Game game ) {
        // write your pop strategy here and at the end call game.pop() function
        if(game.get_board().get_oppCells().size()>0)
            game.pop(game.get_board().get_oppCells().get(0).get_checker());
    }
}
