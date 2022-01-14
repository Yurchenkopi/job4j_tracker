package ru.job4j.pojo;

public class Library {
    public void printInfo(Book[] book) {
        for (int i = 0; i < book.length; i++) {
            System.out.println(i + " | " + "Текущая книга: " + book[i].getTitle() + ", количество страниц: " + book[i].getPageNum());
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void findInfo(Book[] book, String str) {
        for (int i = 0; i < book.length; i++) {
            if (book[i].getTitle().equals(str)) {
                System.out.println(i + " | " + "Книга найдена: " + book[i].getTitle() + ", количество страниц: " + book[i].getPageNum());
            }
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Library library = new Library();
        Book book1 = new Book("Clean Code", 1);
        Book book2 = new Book("War and Peace", 1000);
        Book book3 = new Book("Pride and Prejudice", 546);
        Book book4 = new Book("Great Gatsby", 457);
        Book[] book = new Book[4];
        book[0] = book1;
        book[1] = book2;
        book[2] = book3;
        book[3] = book4;
        library.printInfo(book);
        Book temp = book[0];
        book[0] = book[3];
        book[3] = temp;
        library.printInfo(book);
        library.findInfo(book, "Clean Code");
    }
}
