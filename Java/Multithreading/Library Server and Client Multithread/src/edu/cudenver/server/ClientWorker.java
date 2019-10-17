package edu.cudenver.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWorker implements Runnable {

    private Server server;
    private Socket connection;


    public ClientWorker(Server server, Socket connection){
        this.server=server;
        this.connection=connection;
    }


    @Override
    public void run() {
        try {
            displayMessage("Getting Data Streams");

            BufferedReader input =  getInputStream(connection);
            PrintWriter    output = getOutputStream(connection);

            displayMessage("Processing Connection");
            try {
                String message="";
                do {
                    message = input.readLine();
                    displayMessage(message);

                    String response = this.server.processClientMessage(message);

                    sendMessage(response, output);
                } while (! message.equals("CLIENT>> TERMINATE"));
            }
            catch (Exception e){
                displayMessage("Client terminated.");
                closeConnection(this.connection,input,output);
            }

        }
        catch (IOException e){
            displayMessage("\n================\nCannot get the Streams.");
            e.printStackTrace();
        }
    }




    private PrintWriter getOutputStream(Socket connection) throws IOException {

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
        catch (IOException e){
            e.printStackTrace();
        }
    }



    private void sendMessage(String msg, PrintWriter output){
        output.println(msg);
        displayMessage("SERVER>> "+ msg.replace("\n",""));
    }


    private void displayMessage(String message){
        System.out.println(message);
    }






}
