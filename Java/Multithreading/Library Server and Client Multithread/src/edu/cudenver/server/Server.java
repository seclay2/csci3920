package edu.cudenver.server;

import edu.cudenver.exception.AuthorNotFoundException;
import edu.cudenver.exception.PublisherNotFoundException;
import edu.cudenver.library.Author;
import edu.cudenver.library.Book;
import edu.cudenver.library.Library;
import edu.cudenver.library.Publisher;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    public final static String RSP_OK = "0|Ok";
    public final static String RSP_NOT_IMPLEMENTED = "1|Not Implemented";
    public final static String RSP_AUTHOR = "2|Author not found";
    public final static String RSP_PUBLISHER = "3|Publisher not found";
    public final static String RSP_INVALID_CMD = "4|Invalid Message Format";

    private int port;
    private int backlog;
    private int connectionCounter;

    private ServerSocket socketServer;

    private Library myLibrary;

    public Server(int port, int backlog) {
        this.port    = port;
        this.backlog = backlog; //Incoming Connection Queue size

        this.connectionCounter = 1; //Total number of connections handled.

        this.myLibrary=new Library();
        this.loadFile();
    }

    /**
     * Starts and Runs the Server.
     */
    public void runServer(){
        try {
            socketServer = new ServerSocket(this.port, this.backlog);
            Socket connection = null;

            while(true){// Infinite loop to receive connections.
                try{
                    connection = waitForConnection();

                    ExecutorService executorService = Executors.newCachedThreadPool();

                    ClientWorker cw = new ClientWorker(this,connection);

                    executorService.execute(cw);

                }
                catch (IOException e){
                    displayMessage("\n================\nServer Terminated.");
                }
                finally{

                }
            }
        }
        catch (IOException e){
            displayMessage("\n================\nServer cannot be opened.");
            e.printStackTrace();
        }
    }


    /**
     * Waits for a connection, and returns it.
     * @return the Socket object representing the connection.
     * @throws IOException
     */
    private Socket waitForConnection() throws IOException {
        displayMessage("Waiting For a Connection");

        Socket connection = socketServer.accept();

        displayMessage( "Connection "+ this.connectionCounter +
                        " sReceived from: " + connection.getInetAddress().getHostName());

        return connection;
    }


    private void displayMessage(String message){
        System.out.println(message);
    }


    private void loadFile(){
        try{
            Scanner file = new Scanner(new File("default.txt"));
            System.out.println("Reading File");
            while (file.hasNextLine()){
                String line = file.nextLine();
                System.out.println(processClientMessage(line));
            }
            System.out.println("Done");
        }
        catch (FileNotFoundException e){
            System.out.println("File does not exists.");
        }

    }


    /**
     * Process the client messages.
     * @param msg
     * @return
     */
    protected synchronized String processClientMessage(String msg){
        String[] arguments = msg.split("\\|");
        String response;
        int args=arguments.length;

        System.out.println(msg);
        switch (arguments[0]){
            case "A":
                if (args>= 2){ //check the message has the correct number of arguments for the command
                    myLibrary.addAuthor(arguments[1]);
                    return RSP_OK;
                }
                else { return RSP_INVALID_CMD;}
            case "P":
                if (args>= 3){
                    myLibrary.addPublisher(arguments[1],arguments[2]);
                    return RSP_OK;
                }
                else { return RSP_INVALID_CMD;}

            case "B":
                if (args>= 6){
                    try{
                        myLibrary.addBook(arguments[1],arguments[2],Integer.parseInt(arguments[3]),arguments[4],arguments[5]);
                        return RSP_OK;
                    }
                    catch (AuthorNotFoundException ae){
                        return RSP_AUTHOR;
                    }
                    catch (PublisherNotFoundException pe){
                        return RSP_PUBLISHER;
                    }
                }
                else { return RSP_INVALID_CMD;}

            case "L": //books
                response ="0|";
                for (Book b : myLibrary.getBooks()){
                    response += b.getTitle() +"|";
                }
                return response;

            case "U": // Publishers
                response="0|";
                for (Publisher p : myLibrary.getPublishers()){
                    response += p.getName() +"|";
                }
                return response;

            case "T": // authors
                response="0|";
                for (Author a : myLibrary.getAuthors()){
                    response += a.getName() +"|";
                }
                return response;

            default:
                return RSP_NOT_IMPLEMENTED;
        }


    }


    /**
     * Main program that launches the Server.
     * Can and should be moved out to another class.
     * @param args
     */
    public static void main(String[] args){
        Server server = new Server(50000, 100);
        server.runServer();
    }
}
