package librarySystem;

import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<Book> borrowedList = new ArrayList<>();

    public User(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Book> getBorrowedList() {

        return this.borrowedList;
    }

    public void addToMyList(Book book){
        this.borrowedList.add(book);
    }
}
