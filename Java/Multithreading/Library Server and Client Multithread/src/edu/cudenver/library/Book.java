package edu.cudenver.library;

/**
 * Represent books in our library
 */
public class Book {

    //Todo:
    // Book has a title, a location (string) a year of publication (int) a single author and a single publisher.
    private String title;
    private String locationCode;
    private int yearPub;

    private Author author;
    private Publisher publisher;

    /**
     * Initializes the book
     * @param title book's title
     * @param location book's location in the library. it's usually a code like G70.212.R54
     * @param yearPub year of publication
     * @param author author object.
     * @param publisher publisher object.
     */
    public Book ( String title, String location, int yearPub,Author author, Publisher publisher){
        //Todo: #1 Initialize the book attributes.
        this.title =title;
        this.locationCode = location;
        this.yearPub = yearPub;

        this.author = author;
        this.publisher = publisher;
    }

    public Book (String title, String location, int yearPub){
        //Todo: #2: initialize attributes. Author and publisher should be set to null.
        //          reuse as much as possible!

    }

    public Author getAuthor() {
        //Todo: #3
        return author;
    }

    public Publisher getPublisher() {
        //Todo: #4
        return publisher;
    }

    public int getYearPub() {
        //Todo: #5
        return yearPub;
    }

    public void setYearPub(int yearPub) {
        //Todo: #6
        this.yearPub = yearPub;
    }

    public String getLocationCode() {
        //Todo: #7
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        //Todo: #8
        this.locationCode = locationCode;
    }

    public String getTitle() {
        //Todo: #9
        return title;
    }

    public void setTitle(String title) {
        //Todo: #10
        this.title = title;
    }

    /**
     * The toString method converts an object to a string. It is used to display the object in a println call.
     * @return
     */
    public String toString(){
        //Todo: #11
        // should show: "A book called '<title>' written by '<author name>' and published in <year> by '<publisher name>'"
        // e.g. "A book called 'intro to java' written by 'John' and published in 2005 by 'Pearson'
        return String.format("A book called '%s' written by '%s' and published in %d by '%s'", title, author.getName(), yearPub,publisher.getName());
    }
}
