import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private int port;
    private int backlog;
    private int connectionCounter;

    private ServerSocket socketServer;

    public Server(int port, int backlog) {
        this.port    = port;
        this.backlog = backlog; //Incoming Connection Queue size

        this.connectionCounter = 1; //Total numebr of connections handled.
    }

    /**
     * Starts and Runs the Server.
     */
    public void runServer(){
        try {
            socketServer = new ServerSocket(this.port, this.backlog);
            Socket connection = null;

            BufferedReader input =null;
            PrintWriter    output = null;


            while(true){
                // Infinite loop to receive connections.
                try{
                    connection = waitForConnection();

                    displayMessage("Getting Data Streams");

                    output = getOutputStream(connection);
                    input  = getInputStream(connection);

                    sendMessage("Connected to Java server.", output);

                    displayMessage("Processing Connection");
                    processConnection(connection, input, output);
                }
                catch (EOFException e){
                    displayMessage("\n================\nServer Terminated.");
                }
                finally{
                    closeConnection(connection, input, output);
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


    private PrintWriter getOutputStream(Socket connection) throws IOException{

        return new PrintWriter(connection.getOutputStream(),true);

    }

    private BufferedReader getInputStream(Socket connection) throws IOException{

        InputStreamReader stream = new InputStreamReader(connection.getInputStream());
        return new BufferedReader(stream);

    }


    private void processConnection(Socket connection, BufferedReader input, PrintWriter output) throws IOException{
        String message="";

//        sendMessage("Connection Successful", output);

        do {
            message = input.readLine();
            displayMessage(message);
            sendMessage("["+message.replace("\n","")+"]", output);
        } while (! message.equals("CLIENT>> TERMINATE"));
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
            output.println("SERVER>> "+ msg);
            displayMessage("SERVER>> "+ msg.replace("\n",""));
    }

    private void displayMessage(String message){
        System.out.println(message);
    }


    public static void main(String[] args){
        Server server = new Server(50000, 100);
        server.runServer();
    }
}
