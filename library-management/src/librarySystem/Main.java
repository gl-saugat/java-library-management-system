package librarySystem;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        LibraryService service = new LibraryService();
//
//        service.addBook("1","Monte Crisco", "Monty");
//        service.addBook("2","Monty Python", "Monte");
//
//        service.listBooks().values().forEach(System.out::println);
//
//        service.searchBook("Monte").ifPresent(System.out::println);

        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface(scanner);
        ui.start();

    }
}
