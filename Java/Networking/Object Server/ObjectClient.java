import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ObjectClient {
    public static void main(String[] args){
        try {
            Socket             client = null;
            ObjectInputStream  input      = null;
            ObjectOutputStream output     = null;


            try{
                System.out.println(time()+"Attempting connection");

                client = new Socket("localhost", 9888);

                System.out.println(time()+"Connected");
                System.out.println(time()+"Getting Data Streams");

                output = new ObjectOutputStream(client.getOutputStream());
                input  = new ObjectInputStream(client.getInputStream());

                System.out.println(time()+"Waiting Server Response");

                try{
                    String response = (String) input.readObject();
                    System.out.println(response);
                    Person person = new Person();
                    person.setName("Client Person");

                    System.out.println(time()+"Sending>>>\n"+person);
                    output.writeObject(person);
                    output.flush();

                    System.out.println(time()+"Waiting Response...\n");
                    person = (Person) input.readObject();
                    System.out.println(time()+"Received>>>\n"+person);
                }
                catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
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

    public static String time(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        Calendar calendar = new GregorianCalendar();
        return sdf.format(calendar.getTime())+"::";
    }

}
