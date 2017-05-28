/**
 * Created by MSN on 5/16/2017.
 */
public class Put_strategy {
    static int i=0,j=0;
    public static void execte(Game game) {
        // write your put strategy here and at the end call game.put() function
        game.put(new Pos(i/3,j));
        i++;
        j++;
        j%=3;
    }
}