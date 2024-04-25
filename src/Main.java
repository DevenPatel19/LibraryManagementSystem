import java.util.*;

public class Main {
    public static void main(String[] args) {
        LibrarySystem.main(args);
    }
}

class Book {
    private String title;
    private String author;
    private int quantity;

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class LibrarySystem {
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            // Display the library system menu
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Add Books");
            System.out.println("2. Borrow Books");
            System.out.println("3. Return Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Try to parse user input as an integer
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Handle the case where user input cannot be parsed as an integer
                System.out.println("Invalid input. Please enter a number.");
                continue; // Continue to the next iteration of the loop
            }

            // Perform actions based on user's choice
            switch (choice) {
                case 1:
                    addBooks(scanner);
                    break;
                case 2:
                    borrowBooks(scanner);
                    break;
                case 3:
                    returnBooks(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        } while (choice != 4);
    }

    private static void addBooks(Scanner scanner) {
        System.out.println("\nAdd Books:");
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        int quantity = 0;
        boolean validInput = false;
        do {
            // Prompt user to enter the quantity of the book
            System.out.print("Enter the quantity of the book: ");
            try {
                // Try to parse user input as an integer
                quantity = Integer.parseInt(scanner.nextLine());
                validInput = true; // If parsing succeeds, set validInput to true
            } catch (NumberFormatException e) {
                // Handle the case where user input cannot be parsed as an integer
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!validInput); // Repeat until valid input is provided

        // Check if the book already exists and update its quantity, or add it to the library
        boolean bookExists = false;
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                book.setQuantity(book.getQuantity() + quantity);
                bookExists = true;
                break;
            }
        }

        if (!bookExists) {
            books.add(new Book(title, author, quantity));
        }

        System.out.println("Book(s) added successfully!");
    }

    private static void borrowBooks(Scanner scanner) {
        System.out.println("\nBorrow Books:");
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        int quantity = 0;
        boolean validInput = false;
        do {
            // Prompt user to enter the number of books to borrow
            System.out.print("Enter the number of books to borrow: ");
            try {
                // Try to parse user input as an integer
                quantity = Integer.parseInt(scanner.nextLine());
                validInput = true; // If parsing succeeds, set validInput to true
            } catch (NumberFormatException e) {
                // Handle the case where user input cannot be parsed as an integer
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!validInput); // Repeat until valid input is provided

        // Check if the requested number of books is available and update the quantity
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                if (book.getQuantity() >= quantity) {
                    book.setQuantity(book.getQuantity() - quantity);
                    System.out.println("Book(s) borrowed successfully!");
                    return;
                } else {
                    System.out.println("Not enough copies available to borrow.");
                    return;
                }
            }
        }

        System.out.println("Book not found in the library.");
    }

    private static void returnBooks(Scanner scanner) {
        System.out.println("\nReturn Books:");
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        int quantity = 0;
        boolean validInput = false;
        do {
            // Prompt user to enter the number of books to return
            System.out.print("Enter the number of books to return: ");
            try {
                // Try to parse user input as an integer
                quantity = Integer.parseInt(scanner.nextLine());
                validInput = true; // If parsing succeeds, set validInput to true
            } catch (NumberFormatException e) {
                // Handle the case where user input cannot be parsed as an integer
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!validInput); // Repeat until valid input is provided

        // Check if the books being returned belong to the library system and update the quantity
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                book.setQuantity(book.getQuantity() + quantity);
                System.out.println("Book(s) returned successfully!");
                return;
            }
        }

        System.out.println("Book not found in the library.");
    }
}
