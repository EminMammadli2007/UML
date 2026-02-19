package library;

import java.time.LocalDate;

public class Loan {
    private Book book;
    private Member member;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean returned;

    public Loan(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(14);
        this.returned = false;
    }

    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isReturned() { return returned; }

    public boolean isOverdue() {
        return !returned && LocalDate.now().isAfter(dueDate);
    }

    public void returnBook() {
        this.returned = true;
        book.setAvailable(true);
    }

    @Override
    public String toString() {
        return "Loan: " + book.getTitle() + " -> " + member.getName() +
               " | Due: " + dueDate +
               (returned ? " [RETURNED]" : isOverdue() ? " [OVERDUE]" : "");
    }
}