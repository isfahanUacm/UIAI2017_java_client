/**
 * Created by MSN on 5/16/2017.
 */
public class Move_strategy {
    public static void execte(Game game) {
        // write your move strategy here and at the end call game.move() function
        Pos p = new Pos(0,0);
        Checker c = new Checker(1,0,'m');
        game.move(c, p);
    }
}
