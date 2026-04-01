package librarySystem;

import java.util.*;

public class LibraryService {
    HashMap<String, Book> bookCollection = new HashMap<>();

    public void  addBook(String id, String title, String author){
        Book newBook = new Book(id, title, author);
        this.bookCollection.putIfAbsent(id,newBook);
    }

    public Map<String, Book> listBooks(){
        return bookCollection;
    }

    public Optional<Book> searchBook(String text){
        return bookCollection.values().stream()
                .filter(book -> book.getTitle().contains(text))
                .findFirst();
    }

    public void borrowBook(String id){
        if(bookCollection.get(id).isItAvailable()){
            bookCollection.get(id).letBeBorrowed();
        }else{
            System.out.println("Sorry book isn't available.");
        }
    }

    public void returnBook(String id){
        bookCollection.get(id).letItReturned();

    }

}
