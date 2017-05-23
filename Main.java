/**
 * Created by Alireza on 5/10/2017.
 */
public class Main {

    public static void main(String[] args) {
        Game game = new Game("127.0.0.1",9999,"team1");
        if(game.start_client()) {
            game.start();
        }
    }

}
