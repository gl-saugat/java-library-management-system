package librarySystem;

import java.util.*;

public class LibraryService {
    private HashMap<String, Book> bookCollection = new HashMap<>();
    private ArrayList<User> users = new ArrayList<>();

    public void  addBook(String id, String title, String author){
        Book newBook = new Book(id, title, author);
        Optional<Book> checkList = this.bookCollection.values()
                .stream().filter(book -> book.equals(newBook))
                .findFirst();

        checkList.ifPresentOrElse(
                book -> System.out.println("Book already on the list"),
                () -> this.bookCollection.put(id, newBook)
        );
    }

    public Map<String, Book> listBooks(){
        if(bookCollection.isEmpty()){
            System.out.println("No books available at the moment.");
        }
        return bookCollection;
    }

    public List<Book> searchBook(String text){
        return bookCollection.values().stream()
                .filter(book -> book.getAuthor().contains(text.toLowerCase()) || book.getTitle().contains(text.toLowerCase()))
                .toList();
    }

    public boolean hasUserCrossedLimit(User user){
        if(user.getBorrowedList().size() >= 3){
            System.out.println("You've crossed limit for borrowing. Please return a book first.");
            return true;
        }
        return false;
    }

    public boolean borrowBook(String id, User user){

        Map<String, Book> list = this.bookCollection;

        if(list.isEmpty()){
            System.out.println("We don't have any books at the moment to lend.");
            return false;
        }

        Book book = list.get(id);

        if(book == null){
            System.out.println("Book not found.");
            return false;
        }


        if(book.isItAvailable()){
            book.letBeBorrowed();
            user.addToMyList(book);
            return true;
        }

        return false;


    }

    public void returnBook(String id, User user){
        Book book;
        if(bookCollection.containsKey(id)){
            book = bookCollection.get(id);
            book.letItReturned();
            user.getBorrowedList().remove(book);
            return;
        }
        System.out.println("Not our book.");

    }

    public List<Book> getBorrowedList(User user){
        List<Book> list = user.getBorrowedList();
        if(list.isEmpty()){
            System.out.println("You haven't borrowed anything yet.");
        }
        return list;
    }

    public boolean isBookWithUser(String bookId, User user){
        List<Book> userBooks = getBorrowedList(user);

        return userBooks.stream().anyMatch(book -> book.getId().equals(bookId));

    }

    public User addUser(String name){
        User newUser = new User(name);
        users.add(newUser);
        return newUser;
    }

    public ArrayList<User> userList(){
        return this.users;
    }

}
