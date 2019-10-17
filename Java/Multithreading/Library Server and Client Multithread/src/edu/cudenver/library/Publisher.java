package edu.cudenver.library;

public class Publisher {
    //Todo: Publisher has a name and an address.
    private String name;
    private String address;

    public Publisher(String name){
        //Todo: #1: initialize attributes. Address should be set to the string 'unknown'
        //          reuse as much as possible.
        this.name = name;
        this.address = "unknown";
    }

    public Publisher(String name, String address){
        //Todo: #2: initialize attributes
        this.name = name;
        this.address = address;
    }

    public String getName() {
        //Todo: #3
        return name;
    }
    public void setName(String name) {
        //Todo: #4
        this.name = name;
    }

    public String getAddress() {
        //Todo: #5
        return address;
    }
    public void setAddress(String address) {
        //Todo: #6
        this.address = address;
    }

    /**
     * The toString method converts an object to a string. It is used to display the object in a println call.
     * @return
     */
    public String toString(){
        //Todo: #7. Should show "Publisher '<name>' at '<address>'"
        return String.format("Publisher '%s' at '%s'",name,address);
    }
}
