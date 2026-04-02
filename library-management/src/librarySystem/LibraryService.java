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
                .filter(book -> book.getAuthor().contains(text) || book.getTitle().contains(text))
                .findFirst();
    }

    public boolean hasUserCrossedLimit(User user){
        if(this.bookCollection.size() >= 3){
            System.out.println("You've crossed limit for borrowing. Please return a book first.");
            return true;
        }
        return false;
    }

    public void borrowBook(String id, User user){

        Map<String, Book> list = this.bookCollection;

        if(list.isEmpty()){
            System.out.println("We don't have any books at the moment to lend.");
            return;
        }


        if(list.get(id).isItAvailable()){
            list.get(id).letBeBorrowed();
            user.addToMyList(list.get(id));
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
            System.out.println("You haven't borrowed anything yet.");
        }
        return list;
    }

    public User addUser(String name){
        User newUser = new User(name);
        users.add(newUser);
        return newUser;
    }

}
