package compitoottriaclient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
public class ServerReader extends Thread{
    Socket socket;
    static String str;
    public ServerReader(Socket socket){
        this.socket = socket;
    }
    
    @Override
    public void run(){
        try {
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            PrintWriter printWriter =  new PrintWriter(socket.getOutputStream(),true);
            while (true) {
                str = bufferedReader.readLine();
                getStr();
                System.out.println(str);
                if(getStr() == "@"){
                    System.out.println("Disconnessione, biglietti finiti");
                    printWriter.println(" ");
                    socket.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static String getStr(){
        return str;
    }
}