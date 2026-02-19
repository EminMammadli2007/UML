package library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> books;
    private List<Member> members;
    private List<Loan> loans;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public Loan borrowBook(String isbn, int memberId) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null) { System.out.println("Book not found."); return null; }
        if (member == null) { System.out.println("Member not found."); return null; }
        if (!book.isAvailable()) { System.out.println("Book not available."); return null; }
        if (getActiveLoanCountFor(member) >= 3) { System.out.println("Loan limit reached."); return null; }

        Loan loan = new Loan(book, member);
        book.setAvailable(false);
        loans.add(loan);
        System.out.println("Loan created: " + loan);
        return loan;
    }

    public void returnBook(String isbn, int memberId) {
        for (Loan loan : loans) {
            if (loan.getBook().getIsbn().equals(isbn) &&
                loan.getMember().getMemberId() == memberId &&
                !loan.isReturned()) {
                loan.returnBook();
                System.out.println("Book returned: " + loan.getBook().getTitle());
                return;
            }
        }
        System.out.println("Active loan not found.");
    }

    public int getActiveLoanCountFor(Member member) {
        int count = 0;
        for (Loan loan : loans)
            if (loan.getMember().getMemberId() == member.getMemberId() && !loan.isReturned())
                count++;
        return count;
    }

    public void printAllBooks() {
        System.out.println("=== Books in " + name + " ===");
        for (Book b : books) System.out.println(b);
    }

    public void printOverdueLoans() {
        System.out.println("=== Overdue Loans ===");
        for (Loan l : loans)
            if (l.isOverdue()) System.out.println(l);
    }

    private Book findBook(String isbn) {
        for (Book b : books) if (b.getIsbn().equals(isbn)) return b;
        return null;
    }

    private Member findMember(int memberId) {
        for (Member m : members) if (m.getMemberId() == memberId) return m;
        return null;
    }
}