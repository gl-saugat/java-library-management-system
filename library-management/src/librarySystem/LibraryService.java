package librarySystem;

import java.util.*;

public class LibraryService {
    HashMap<String, Book> bookCollection = new HashMap<>();
    ArrayList<User> users = new ArrayList<>();

    public void  addBook(String id, String title, String author){
        Book newBook = new Book(id, title, author);
        this.bookCollection.putIfAbsent(id,newBook);
    }

    public Map<String, Book> listBooks(){
        if(bookCollection.isEmpty()){
            System.out.println("No books available at the moment.");
        }
        return bookCollection;
    }

    public Optional<Book> searchBook(String text){
        return bookCollection.values().stream()
                .filter(book -> book.getTitle().contains(text))
                .findFirst();
    }

    public void borrowBook(String id, User user){
        if(bookCollection.get(id).isItAvailable()){
            bookCollection.get(id).letBeBorrowed();
            user.addToMyList(bookCollection.get(id));
            System.out.println("Added to your list!");
        }else{
            System.out.println("Sorry book isn't available.");
        }
    }

    public void returnBook(String id, User user){
        Book book;
        if(bookCollection.containsKey(id)){
            book = bookCollection.get(id);
            bookCollection.get(id).letItReturned();
            user.getBorrowedList().remove(book);
            return;
        }
        System.out.println("Sorry, that book isn't ours, if you want to, please add it to our list. Thank you!!");


    }

    public List<Book> getBorrowedList(User user){
        List<Book> list = user.getBorrowedList();
        if(list.isEmpty()){
            System.out.println("You haven't borrowed anthing yet.");
        }
        return list;
    }

    public User addUser(String name){
        User newUser = new User(name);
        users.add(newUser);
        return newUser;
    }

}
