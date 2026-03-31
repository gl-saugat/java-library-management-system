package librarySystem;

import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<Book> borrowedList;

    public void setName(String name){
        this.name = name;
    }

    public void addToMyList(Book book){
        this.borrowedList.add(book);
    }
}
