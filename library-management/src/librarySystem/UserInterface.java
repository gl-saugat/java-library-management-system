package librarySystem;

import java.util.Map;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner;
    LibraryService service;

    public UserInterface(Scanner scanner){
        this.service = new LibraryService();
        this.scanner = scanner;
    }

    public void start(){

        while(true){

            System.out.println("Welcome to our Library. Please enter your name to login.");
            System.out.println("Enter Name: ");
            String input = getInput();


            if(input.equals("c")){
                return;
            }

            if(checkForUser(input)){
                startLibrary();
            }else{
                System.out.println("User Not detected");
                System.out.println("Would you like to enroll? Y/N");
                String userEnrollWant = scanner.nextLine();
                if(userEnrollWant.equals("Y")){
                    service.addUser(input);
                }else{
                    return;
                }
            }

        }

    }


    public void startLibrary(){
        while(true){
            printMenu();
            int input = getMenuOption();

            switch (input){
                case 1:
                    System.out.println("Enter ID");
                    String id = getInput();
                    System.out.println("Enter Book name");
                    String name = getInput();
                    System.out.println("Enter author name");
                    String authorName = getInput();;
                    service.addBook(id, name, authorName);
                    break;

                case 2:
                    Map<String, Book> booklist = service.listBooks();
                    booklist.values().forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Any book you're looking for, enter it's name");
                    String book = scanner.nextLine();
                    service.searchBook(book).ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Sorry, the book isn't available."));
                    break;

                case 4:
                    System.out.println("Enter ID of the book you want.");
                    String searchingId = scanner.nextLine();
                    service.borrowBook(searchingId);
                    break;

                case 5:
                    System.out.println("Please enter the ID of the book you're returning.");
                    String returningId = scanner.nextLine();
                    service.returnBook(returningId);
                    break;

                case 6:
                    System.out.println("Thanks for visiting us");;
                    return;
            }

        }
    }

    public void printMenu(){
        System.out.println("""
                1. Add Book
                2. View Books
                3. Search Book
                4. Borrow Book
                5. Return Book
                6. Exit
                """);
    }

    public int getMenuOption(){
        int input = 0;
        while(true){
            try{
                input = Integer.parseInt(scanner.nextLine());
                if(input > 0 && input <=6){
                    return input;
                }
                System.out.println("Enter valid option, please.6");
            }catch (Exception e){
                System.out.println("Error is: " + e.getMessage());
            }
        }
    }

    public String getInput(){
        String input = "";
        while(true){
            try{
                input = scanner.nextLine();
                if(input.isEmpty()){
                    System.out.println("Can't leave Empty!!");
                }else{
                    return input;
                }

            }catch (Exception e){
                System.out.println("Error is: " + e.getMessage());
            }
        }
    }

    public boolean checkForUser(String input){
        return service.users.stream()
                .map(User::getName)
                .anyMatch(name -> name.equalsIgnoreCase(input));
    }

}
