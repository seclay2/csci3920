package edu.cudenver.library;
/**
 * This is the class Author. It models Book Author.
 */
public class Author {
    private String name;

    /**
     * Author Constructor.
     * @param name Name of the author.
     */
    public Author(String name){
        //Todo: #1: initialize author attributes
        this.name = name;
    }

    /**
     *
     * @return author's name
     */
    public String getName(){
        //Todo: #2
        return name;
    }


    /**
     * Sets the author's name
     * @param name
     */
    public void setName (String name){
        //Todo: #3
        this.name = name;
    }

    /**
     * The toString method converts an object to a string. It is used to display the object in a println call.
     * @return
     */
    public String toString(){
        //Todo: #4 Should show: "<name> is an Author"
        return String.format("%s is an Author",name);
    }
}
