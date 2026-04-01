package librarySystem;

public class Book {
    private String id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public boolean isItAvailable(){
        return this.isAvailable;
    }

    public void letBeBorrowed(){
        this.isAvailable = false;
    }

    public void letItReturned(){
        this.isAvailable = true;
    }



    public String toString(){
        return "ID: " + this.id + " Book Title: "+ this.title + " Author Name: " + this.author + " Borrowed: " + !this.isAvailable;
    }
}
