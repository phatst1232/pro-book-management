/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookapk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author phats
 */
public class BookList extends ArrayList<Book> {

    public AuthorList authorNamelist;
    public String fileBooks = "book.dat";

    Menu updateMenu = new Menu("Choose an update option:");

    public String getFileBook() {
        return fileBooks;
    }

    public void setFileBook(String fileBook) {
        this.fileBooks = fileBook;
    }

    public void searchByTitle(String text) {
        BookList searchList = new BookList();
        for (int i = 0; i < this.size(); i++) {
            if (MyScanner.containsIgnoreCase(this.get(i).title, text)) {
                searchList.add(this.get(i));
            }
        }
        if (searchList.isEmpty()) {
            System.out.println("No book is matched! There is no result for '" + text + "' !");
        } else {
            for (int i = 0; i < searchList.size(); i++) {
                searchList.showAll();
            }
        }
    }

    public int searchByBookID(String bookID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).bookID.equalsIgnoreCase(bookID)) {
                return i;
            }
        }
        return -1;
    }

    public boolean searchByAuthor(Author author) {
        BookList searchList = new BookList();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).author.ID.equalsIgnoreCase(author.ID)) {
                searchList.add(this.get(i));
            }
        }
        if (searchList.isEmpty()) {
            System.out.println("Not found any book by '"+author.name+"' in library!");
        } else {
            for (int i = 0; i < searchList.size(); i++) {
                searchList.showAll();
            }
        }
        return searchList.isEmpty();
    }
    
    public int searchByISBN(String ISBN) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).ISBN.equalsIgnoreCase(ISBN)) {
                return i;
            }
        }
        return -1;
    }

    public boolean loadFromFile(String fName) {
        FileReader fr = null;
        BufferedReader br = null;
        String line = null;
        StringTokenizer stk = null;
        try {
            fr = new FileReader(fName);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                line.trim();
                if (line.length() > 0) {
                    stk = new StringTokenizer(line, ";");
                    String bookID = stk.nextToken().trim();
                    String isbn = stk.nextToken().trim();
                    String title = stk.nextToken().trim();
                    double price = Double.parseDouble(stk.nextToken().trim());
                    Author author = new Author();
                    author.setID(stk.nextToken().trim()); 
                    author.setName(stk.nextToken().trim());
                    Book book;
                    book = new Book(bookID, isbn, title, price, author);
                    this.add(book);
                }
            }
            br.close();
            fr.close();
            System.out.println("Load from file DONE!");
        } catch (Exception e) {
            System.out.println("Load from file error!");
        }
        return true;
    }

    public boolean writeToFile(String fName) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(fName);
            pw = new PrintWriter(fName);
            for (int i = 0; i < this.size(); i++) {
                pw.println(this.get(i).toString());
            }
            pw.flush();
            System.out.println("Writing file: Done.");
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Write to file error!");
        }
        return true;
    }

    public void addBook() {
        int no = 0;
        Book b = new Book();
        Author author = null;
        boolean retry = false;
        do {
            try {
                System.out.println("Who is the Author of this book?");
                author = this.authorNamelist.chooseAuthor();
                if (author != null) {
                    String bookID = MyScanner.getbookID("Enter Book ID: ");
                    if (this.searchByBookID(bookID) >= 0) {
                        retry = MyScanner.getBoolean("This ID is existed! Try again?(y/n)");
                    } else {
                        String ISBN = MyScanner.getISBN("Enter book ISBN:");
                        if (this.searchByISBN(ISBN) >= 0) {
                            retry = MyScanner.getBoolean("This ISBN is existed! Try again?(y/n)");
                        } else {

                            b.bookID = bookID;
                            b.ISBN = ISBN;
                            b.setTitle(MyScanner.getString("Enter title: ").toUpperCase());
                            b.setPrice(MyScanner.getDouble("Enter price: ", 1000000));
                            b.setAuthor(author);
                            this.add(b);
                            System.out.println("Added!");
                            retry = MyScanner.getBoolean("Add a new book?(y/n)");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Add book cancel!");
                retry = MyScanner.getBoolean("Add another book?(y/n)");
            }
        } while (retry);
        //this.writeToFile(fileBooks);
    }

    public void removeBook() {
        String bookID = "";
        boolean redo = false;
        do {
            bookID = MyScanner.getbookID("Input book ID you want to remove: ");
            if (this.searchByBookID(bookID) < 0) {
                redo = MyScanner.getBoolean("ID not found!Do you want to try with another ID?(yes/no)");
                if (redo == false) {
                    System.out.println("Removed not thing!");
                }
            } else {
                if (MyScanner.getBoolean("Are you sure to remove book with ID: " + bookID + " ?(y/n)")) {
                    this.remove(this.searchByBookID(bookID));
                    //this.writeToFile(fileBooks);
                    System.out.println("Remove DONE!");
                    redo = MyScanner.getBoolean("Do you want delete another book?(yes/no)");
                } else {
                    redo = MyScanner.getBoolean("Do you want delete another book?(yes/no)");
                }
            }
        } while (redo);
    }

    public void updateBook() {
        boolean redo = false;
        String id;
        int opt;
        int cur;

        if (updateMenu.isEmpty()) {
            updateMenu.add("Update ISBN");
            updateMenu.add("Update title");
            updateMenu.add("Update price");
        }
        do {
            id = MyScanner.getbookID("Enter book ID you want to update:");
            cur = this.searchByBookID(id);
            if (cur < 0) {
                redo = MyScanner.getBoolean("Book does not exist! Do you want to try another ID?(y/n)");
            } else {
                do {
                    opt = updateMenu.getUserChoice();
                    switch (opt) {
                        case 1:
                            this.get(cur).ISBN = MyScanner.getISBN("Enter new ISBN: ");
                            System.out.println("Updated!");
                            break;
                        case 2:
                            this.get(cur).title = MyScanner.getString("Enter new title: ");
                            System.out.println("Updated!");
                            break;
                        case 3:
                            this.get(cur).price = MyScanner.getDouble("Enter new price: ", 100000);
                            System.out.println("Updated!");
                            break;
                    }
                } while (opt > 0 && opt < 4);
            }
        } while (redo);
        //this.writeToFile(fileBooks);
    }

    public void showAll() {
        if (this.isEmpty()) {
            System.out.println("Book list is empty!");
        } else {
            for (int i = 0; i < this.size(); i++) {
                System.out.println("----------------------------------------");
                this.get(i).showAll();
            }
            System.out.println(">----------------------------------------<");
        }
    }

    public void sortById() {
        if (!this.isEmpty()) {
            Collections.sort(this);
        }
    }

}
