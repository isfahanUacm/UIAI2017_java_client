/**
 * Created by MSN on 5/16/2017.
 */
public class Put_strategy {
    public static void execte(Game game) {
        // write your put strategy here and at the end call game.put() function
        game.put(game.get_board().get_emptyCells().get(0).get_pos());
    }
}
