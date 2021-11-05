/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookapk;

/**
 *
 * @author phats
 */
public class Book implements Comparable<Book> {

    String bookID;
    String ISBN;
    String title;
    double price;
    Author author;

    public Book() {
        this.bookID = "none";
        this.ISBN = "none";
        this.author = new Author();
        this.price = -1;
        this.title = "none";
    }

    public Book(String bookID, String ISBN, String title, double price, Author author) {
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookID() {
        return bookID;
    }

    public void showAll() {
        System.out.println("Book ID: " + bookID);
        System.out.println("ISBN: " + ISBN);
        author.showAll();
        System.out.println("Title: " + title);
        System.out.println("Price: " + (double)Math.round(price * 10) / 10 + "($)");
    }

    @Override
    public int compareTo(Book o) {
        return this.bookID.compareTo(o.bookID);
    }

    @Override
    public String toString() {
        return bookID + ";" + ISBN + ";" + title + ";" + price + ";" + author.toString();
    }

}
