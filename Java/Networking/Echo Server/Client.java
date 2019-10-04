import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private int port;

    public Client(int port){
        this.port=port;

    }


    public void runClient(){
        try {
            Socket         client = null;
            BufferedReader input      = null;
            PrintWriter    output     = null;
            String         message    = "";
            Scanner scanner = new Scanner (System.in);

            try{
                client = connect();

                displayMessage("Getting Data Streams");

                output = getOutputStream(client);
                input  = getInputStream(client);

                String response = "";

                while(! message.equals("TERMINATE")) {
                    response = input.readLine();
                    displayMessage(response);

                    displayMessage("\nWhat To send? (TERMINATE to quit)>");

                    message = scanner.nextLine();

                    sendMessage(message, output);
                }
            }
            catch (EOFException e){
                displayMessage("\n================\nServer Terminated.");
            }
            finally{
                closeConnection(client, input, output);
            }

        }
        catch (IOException e){
            displayMessage("\n================\nServer cannot be opened.");
            e.printStackTrace();
        }



    }




    private Socket connect() throws IOException {
        displayMessage("Attempting connection");

        Socket connection = new Socket("localhost", 50000);

        displayMessage("Connected");

        return connection;
    }


    private PrintWriter getOutputStream(Socket connection) throws IOException{

        return new PrintWriter(connection.getOutputStream(),true);

    }

    private BufferedReader getInputStream(Socket connection) throws IOException{

        InputStreamReader stream = new InputStreamReader(connection.getInputStream());
        return new BufferedReader(stream);

    }



    private void closeConnection(Socket connection, BufferedReader input, PrintWriter output){
        displayMessage("\nTerminating connection");
        try {
            output.close();
            input.close();
            connection.close();
        }
        catch (IOException|NullPointerException e){
            e.printStackTrace();
        }

    }


    private void sendMessage(String msg, PrintWriter output){
        output.println("CLIENT>> "+ msg);
        displayMessage("CLIENT>> "+ msg);
    }


    private void displayMessage(String message){
        System.out.println(message);
    }


    public static void main(String[] args){
        Client client = new Client(50000);
        client.runClient();
    }
}
