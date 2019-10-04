import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class SimpleServer {
    
     public static void main(String[] args){
        try {
            ServerSocket socketServer = new ServerSocket(9888, 5);
            Socket connection = null;

            BufferedReader input =null;
            PrintWriter    output = null;

            while(true){    // Infinite loop to receive connections.
                try{
                    System.out.println("Waiting For a Connection");
                    connection = socketServer.accept();
                    System.out.println( "Connection from: " + connection.getInetAddress().getHostName());
                    

                    System.out.println("Getting Data Streams");
                    output =  new PrintWriter(connection.getOutputStream(),true);
                    input  =  new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    
                    Thread.sleep(3000); //wait for 3 seconds (do processing.)

                    output.println("Connected to Java server.");

                }
                catch (Exception e){
                    System.out.println("\n================\nServer Terminated.");
                }
                finally{
                    System.out.println("\nTerminating connection");
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
            System.out.println("\n================\nServer cannot be opened.");
            e.printStackTrace();
        }
    }
 
}
