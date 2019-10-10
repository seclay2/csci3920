import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class ObjectServer {
    
     public static void main(String[] args){
        try {
            ServerSocket socketServer = new ServerSocket(9888, 5);
            Socket connection = null;

            ObjectInputStream  input  = null;
            ObjectOutputStream output = null;

            while(true){    // Infinite loop to receive connections.
                try{
                    System.out.println(time()+"Waiting For a Connection");
                    connection = socketServer.accept();
                    System.out.println(time()+ "Connection from: " + connection.getInetAddress().getHostName());
                    

                    System.out.println(time()+"Getting Data Streams");
                    output =  new ObjectOutputStream(connection.getOutputStream());
                    output.flush();

                    input  =  new ObjectInputStream(connection.getInputStream());

                    output.writeObject(time()+"Connected to Java server.");
                    output.flush();

                    try {
                        Person p = (Person) input.readObject();
                        System.out.println(time()+"Received>>>\n"+p);

                        Thread.sleep(3000); //wait for 3 seconds (do processing.)

                        p.setName("This Person was modified by the server.");
                        System.out.println(time()+"Sending>>>\n"+p);
                        output.writeObject(p);
                        output.flush();
                    }
                    catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                catch (Exception e){
                    System.out.println(time()+"\n================\nServer Terminated.");
                }
                finally{
                    System.out.println(time()+"\nTerminating connection");
                    try {
                        output.close();
                        input.close();
                        connection.close();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println(time()+"\n================\nServer cannot be opened.");
            e.printStackTrace();
        }
    }

    public static String time(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        Calendar calendar = new GregorianCalendar();
        return sdf.format(calendar.getTime())+"::";
    }


}
