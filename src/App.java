import library.*;

public class App {
    public static void main(String[] args) {

        // Create library
        Library library = new Library("City Library");

        // Create and add books
        Book b1 = new Book("111", "Clean Code", "Robert Martin");
        Book b2 = new Book("222", "The Pragmatic Programmer", "Andrew Hunt");
        Book b3 = new Book("333", "Design Patterns", "Gang of Four");
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        // Create and register members
        Member m1 = new Member(1, "Alice", "alice@email.com");
        Member m2 = new Member(2, "Bob", "bob@email.com");
        library.registerMember(m1);
        library.registerMember(m2);

        // Print all books
        library.printAllBooks();
        System.out.println();

        // Borrow books
        library.borrowBook("111", 1); // Alice borrows Clean Code
        library.borrowBook("222", 2); // Bob borrows Pragmatic Programmer
        library.borrowBook("111", 2); // Bob tries to borrow already borrowed book
        System.out.println();

        // Print books again to see availability change
        library.printAllBooks();
        System.out.println();

        // Return a book
        library.returnBook("111", 1); // Alice returns Clean Code
        System.out.println();

        // Print books again to confirm return
        library.printAllBooks();
        System.out.println();

        // Check overdue (none expected since loans are brand new)
        library.printOverdueLoans();
    }
}