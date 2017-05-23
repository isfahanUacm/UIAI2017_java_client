/**
 * Created by Alireza on 5/10/2017.
 */
public class Cell {

    private Pos pos;
    private Checker checker;
    public void set_checker ( Checker c)
    {checker = c;}

    public Cell (int x , int y , char c)
    {
        pos = new Pos(x,y);
        if(c=='e')
            checker = null;
        else
            checker = new Checker(x,y,c);
    }
    public Cell ()
    {
        pos = new Pos(0,0);
        checker = null;
    }
    public Checker get_checker()
    {
        return checker;
    }
    public Pos get_pos()
    {
        return pos;
    }

    public void set_pos(Pos p){pos = p;}

    public void set_checker(int x, int y, char c)
    {
        checker = new Checker(x,y,c);
    }

}
