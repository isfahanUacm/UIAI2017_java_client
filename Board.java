import java.util.ArrayList;

/**
 * Created by Alireza on 5/10/2017.
 */
public class Board {

    private ArrayList < ArrayList <Cell> > cells;
    private ArrayList<Cell> mycells;
    private ArrayList<Cell> oppcells;
    private ArrayList<Cell> emptycells;

    public Board()
    {
        cells = new ArrayList<ArrayList<Cell>>();
        mycells = new ArrayList<Cell>();
        oppcells = new ArrayList<Cell>();
        emptycells = new ArrayList<Cell>();
        for(int i=0;i<8;i++)
        {
            ArrayList<Cell> tmp = new ArrayList<Cell>();
            tmp.clear();
            for(int j=0;j<3;j++)
            {
                tmp.add(new Cell(i,j,'e'));
            }
            cells.add(tmp);
        }
    }

    public void update(String s)
    {
        mycells.clear();
        oppcells.clear();
        emptycells.clear();

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<3;j++)
            {
                cells.get(i).get(j).set_checker(null);
                if(s.charAt(0)!='e')
                {
                    cells.get(i).get(j).set_checker(new Checker(i,j,s.charAt(0)));
                    if(s.charAt(0)=='m')
                        mycells.add(cells.get(i).get(j));
                    else
                        oppcells.add(cells.get(i).get(j));
                }
                else
                    emptycells.add(cells.get(i).get(j));
                if(s.length()>=2)
                    s=s.substring(2);
            }
        }
    }

    public Cell get_cell(Pos p)
    {
        return cells.get(p.getX()).get(p.getY());
    }

    public void set_cell(int x, int y, char c)
    {
        cells.get(x).get(y).set_checker(x,y,c);
    }

    public Cell get_cell(int x , int y)
    {
        return cells.get(x).get(y);
    }

    public ArrayList <ArrayList <Cell> > get_cells()
    {
        return cells;
    }

    public ArrayList <Cell> get_myCells()
    {
        return mycells;
    }
    public ArrayList<Cell> get_oppCells()
    {
        return oppcells;
    }
    public ArrayList<Cell> get_emptyCells()
    {
        return emptycells;
    }

}