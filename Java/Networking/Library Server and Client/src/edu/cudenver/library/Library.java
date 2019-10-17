package edu.cudenver.library;

import edu.cudenver.exception.AuthorNotFoundException;
import edu.cudenver.exception.PublisherNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private List<Author> authors;
    private List<Publisher> publishers;


    public Library(){
        this.books=new ArrayList<>();
        this.authors=new ArrayList<>();
        this.publishers=new ArrayList<>();
    }




    public void addAuthor(String name){
        authors.add(new Author(name));
    }

    public void addPublisher(String name, String address){
        publishers.add( new Publisher(name,address));
    }

    public void addBook(String title,String location, int yearPub, String authorName, String publisherName ) throws AuthorNotFoundException, PublisherNotFoundException {
        //Find Author
        Author author=null;
        for (Author a:authors){
            if (a.getName().equals(authorName)){
                author = a;
                break;
            }
        }
        if (author==null) {
            throw new AuthorNotFoundException("Author is not in the system");
        }

        //Find Publisher
        Publisher publisher=null;
        for (Publisher p:publishers){
            if (p.getName().equals(publisherName)){
                publisher=p;
                break;
            }
        }
        if (publisher==null){
            throw new PublisherNotFoundException("Publisher is not in the system");
        }


        books.add(new Book(title, location,yearPub,author,publisher));
    }

    public List<Book> getBooks(){return books;}
    public List<Author> getAuthors(){return authors;}
    public List<Publisher> getPublishers(){return publishers;}

}
