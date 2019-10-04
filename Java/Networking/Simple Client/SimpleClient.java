import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
    public static void main(String[] args){       
        try {
            Socket         client = null;
            BufferedReader input      = null;
            PrintWriter    output     = null;
            String         message    = "";

            try{
                System.out.println("Attempting connection");

                client = new Socket("localhost", 9888);

                System.out.println("Connected");
                System.out.println("Getting Data Streams");

                output = new PrintWriter(client.getOutputStream(),true);
                input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                
                System.out.println("Waiting Server Response");

                String response = input.readLine();
                System.out.println(response);
            }
            catch (EOFException e){
                System.out.println("\n================\nServer Terminated.");
            }
            finally{
                System.out.println("\nTerminating connection");
                try {
                    output.close();
                    input.close();
                    client.close();
                }
                catch (IOException|NullPointerException e){
                    e.printStackTrace();
                }
                
            }

        }
        catch (IOException e){
            System.out.println("\n================\nServer cannot be opened.");
            e.printStackTrace();
        }
    }  
}
