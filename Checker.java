/**
 * Created by Alireza on 5/10/2017.
 */
public class Checker {

    private Pos pos;
    private boolean ismychecker;

    public Checker (int x , int y , char c)
    {
        pos = new Pos(x,y);
        ismychecker = (c=='m') ? true : false;
    }
    public boolean isMyChecker()
    {
        return ismychecker;
    }
    public Pos get_pos()
    {
        return pos;
    }
}
