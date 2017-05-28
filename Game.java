import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Alireza on 5/10/2017.
 */
public class Game {
    private Board board;
    private String msg_send, msg_recieved, server_address, teamname;
    private int sock, server_port, myinhandcheckernum, oppinhandcheckernum, cycle;
    private boolean dooz;
    private BufferedWriter os = null;
    private BufferedReader is = null;

    public Game() {
        board = new Board();
        msg_send = "";
        msg_recieved = "";
    }

    public Game(String serveraddress, int serverport, String name) {
        board = new Board();
        msg_send = "";
        msg_recieved = "";
        sock = -1;
        server_address = serveraddress;
        server_port = serverport;
        teamname = name;
    }

    public Board get_board() {
        return board;
    }

    public boolean start_client() {
        try {
            Socket myClient = new Socket(server_address, server_port);
            os = new BufferedWriter(new OutputStreamWriter(myClient.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
            return false;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
            return false;
        }
        return true;
    }

    public void start() {
        try {
            os.write("REGISTER " + teamname + '\n');
            os.flush();
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                msg_send = "";
                if(responseLine.equals("FINISH"))
                    break;
                play_round(responseLine);
                msg_send += "\n";
                os.write(msg_send);
                os.flush();
            }
        } catch (SocketException e) {
            System.out.println("Connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void play_round(String message) throws IOException {
        dooz = false;
        board.update(message.substring(0, 47));
        message = message.substring(48);
        String[] ss = message.split(",");
        myinhandcheckernum = Integer.parseInt(ss[0]);
        oppinhandcheckernum = Integer.parseInt(ss[1]);
        cycle = Integer.parseInt(ss[2]);

        if (myinhandcheckernum > 0)
            Put_strategy.execte(this);
        else
            Move_strategy.execte(this);

        if (dooz)
            Pop_strategy.execte(this);
        os.flush();
    }

    public int getMyinhandcheckernum() {
        return myinhandcheckernum;
    }

    public int getOppinhandcheckernum() {
        return oppinhandcheckernum;
    }

    public void put(Pos p) {
        msg_send = "put " + p.getX() + "," + p.getY();
        board.get_cell(p).set_checker(p.getX(),p.getY(),'m');
        update_cell_arrays();
        check_dooz(p);
    }

    public void pop(Checker c) {
        msg_send += " " + c.get_pos().getX() + "," + c.get_pos().getY();
        board.get_cell(c.get_pos()).set_checker(null);
    }

    public void move(Checker c, Pos newpos) {
        msg_send = "mov " + c.get_pos().getX() + "," + c.get_pos().getY() + "," + newpos.getX() + "," + newpos.getY();
        board.get_cell(c.get_pos()).set_checker(null);
        board.get_cell(newpos).set_checker(newpos.getX(),newpos.getY(),'m');
        update_cell_arrays();
        check_dooz(newpos);
    }

    private void check_dooz(Pos p) {
        boolean status = true;
        for (int j = 0; j < 3; j++)
            if (board.get_cell(p.getX(),j).get_checker() == null || !board.get_cell(p.getX(),j).get_checker().isMyChecker()) {
                status = false;
                break;
            }
        if (status) {
            dooz = true;
            return;
        }

        status = true;
        if (p.getX() % 2 == 0) {
            for (int i = 0; i < 3; i++)
                if (board.get_cell((p.getX() + i) % 8, p.getY()).get_checker() == null || !board.get_cell((p.getX() + i) % 8, p.getY()).get_checker().isMyChecker())
                    status = false;
            if(status)
            {
                dooz = true;
                return;
            }
            status = true;
            for (int i = 0; i < 3; i++)
                if (board.get_cell((p.getX() - i + 8) % 8, p.getY()).get_checker() == null || !board.get_cell((p.getX() - i + 8) % 8, p.getY()).get_checker().isMyChecker())
                    status = false;
        }
        else
        {
            for (int i = -1; i < 2; i++)
                if (board.get_cell((p.getX() + i) % 8, p.getY()).get_checker() == null || !board.get_cell((p.getX() + i) % 8, p.getY()).get_checker().isMyChecker())
                    status = false;

        }
        if (status)
        {
            dooz = true;
        }
    }

    private void update_cell_arrays()
    {
        board.get_emptyCells().clear();
        board.get_myCells().clear();
        board.get_oppCells().clear();

        for(int i=0;i<8;i++)
            for(int j=0;j<3;j++)
            {
                if(board.get_cell(i,j).get_checker()==null)
                    board.get_emptyCells().add(board.get_cell(i,j));
                else if(board.get_cell(i,j).get_checker().isMyChecker())
                    board.get_myCells().add(board.get_cell(i,j));
                else
                    board.get_oppCells().add(board.get_cell(i,j));
            }
    }

    public int get_cycle() {
        return cycle;
    }
}
