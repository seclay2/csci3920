import java.io.*;
import java.util.*;

/**
 * This is a demo Person class that can be serialized.
 * The provided main method serializes the a person object to and from a .set file.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L; //version of the class code.

    // These attributes will be serialized;
    private String name ;
    private int    age;
    private List<String> livedInPlaces;
    // Note that String objects can be serialized as the String class implements the Serializable interface.
    // https://docs.oracle.com/javase/8/docs/api/java/lang/String.html

    // This list is transient, so it won't be serialized,
    // when retrieved it will be null
    private transient List<String> visitedPlaces;

    /**
     * Initializes the Person Object
     */
    public Person(){
        name = "The Person's name";
        age = 0;
        livedInPlaces = new ArrayList<>(); 
        livedInPlaces.add("NYC");         livedInPlaces.add("Denver");
        visitedPlaces = new ArrayList<>(); 
        visitedPlaces.add("Los Angeles"); visitedPlaces.add("San Antonio");
    }

    /**
     * Prints the object
     * @return a String with the object representation.
     */
    @Override
    public String toString(){
        return "===Person object===\nName:"+name+"\nAge:"+age+"\nLived in:"+
                livedInPlaces+"\nVisited:"+visitedPlaces+"\n-----------";
    }


    public String getName()             {return this.name;}
    public void setName(String name)    {this.name = name;}

    public int  getAge()            {return this.age;}
    public void setAge(int age)     {this.age = age; }





    /**
     * This is the main method to test the person
     * @param args
     * @throws IOException
     */
    public static void main( String[] args ) throws IOException  {
        File aFile = new File("personObject.ser"); //File to store the object

        Person aPerson = new Person();
        aPerson.setName("John Smith");
        aPerson.setAge(25);





        // *******************  WRITING  *************
        System.out.println("Serializing Person:\n"+aPerson);


        ObjectOutputStream oos;

        try {
            //Opening Stream to save objects to file.
            oos = new ObjectOutputStream( new FileOutputStream(aFile) );

            oos.writeObject( aPerson ); // write the object
            oos.close(); //After writing the object the output stream must be closed.
        }
        catch (NullPointerException e){ e.printStackTrace();}

        System.out.println("Object Saved to File: "+ aFile.getAbsolutePath());







        // *******************  READING **************

        System.out.println("");

        System.out.println("Reading Object from File: " + aFile.toString());
        ObjectInputStream ios=null;
        try{
            //Open Object Input File Stream from aFile to read objects
            ios = new ObjectInputStream(new FileInputStream(aFile));

            Person readInstance = (Person) ios.readObject(); //Read Object from File

            System.out.println("De-serialized Person:\n"+readInstance);
        }
        catch (ClassNotFoundException e){ e.printStackTrace();}
        finally{
            assert ios != null;
            ios.close();    //After processing the object (or cast error) the input stream must be closed.
        }


    }
}

