/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import bookapk.Author;
import bookapk.AuthorList;
import bookapk.BookList;
import bookapk.Menu;
import bookapk.MyScanner;

/**
 *
 * @author phats
 */
public class BookMng {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AuthorList authorlist = new AuthorList();
        authorlist.loadFromFile(authorlist.fileAuthors);

        BookList booklist = new BookList();
        booklist.loadFromFile(booklist.fileBooks);

        booklist.authorNamelist = authorlist;

        booklist.sortById();
        Menu mainmenu = new Menu("Managing book - Main menu:");
        mainmenu.add("Show the book list");
        mainmenu.add("Add new book");
        mainmenu.add("Update book");
        mainmenu.add("Delete book");
        mainmenu.add("Search book");
        mainmenu.add("Store data to file");
        mainmenu.add("Delete Author");
        Menu searchMenu = new Menu("Search Menu:");
        searchMenu.add("Search by book name");
        searchMenu.add("Search by Author name");
        int mainChoice, searchChoice;
        boolean changed = false;
        boolean retry = true;
        boolean response = true;
        do {
            System.out.println("---Welcome to HKT Book Store---");
            mainChoice = mainmenu.getUserChoice();
            switch (mainChoice) {
                case 1:
                    booklist.showAll();
                    break;
                case 2:
                    booklist.addBook();
                    changed = true;
                    break;
                case 3:
                    booklist.updateBook();
                    changed = true;
                    break;
                case 4:
                    booklist.removeBook();
                    changed = true;
                    break;
                case 5:
                    searchChoice = searchMenu.getUserChoice();
                    if (searchChoice == 1) {
                        do {
                            String text = MyScanner.getString("Enter some thing from that book title: ");
                            System.out.println("\nThere is result for '" + text + "' :");
                            booklist.searchByTitle(text);
                            retry = MyScanner.getBoolean("Do you want to find some thing else?(y/n)");
                        } while (retry);
                    } else {
                        do {
                            Author author = authorlist.chooseAuthor();
                            booklist.searchByAuthor(author);
                            retry = MyScanner.getBoolean("Do you want to find with other author?(y/n)");
                        } while (retry);
                    }
                    break;
                case 6:
                    response = MyScanner.getBoolean("Save book data to file?(y/n)");
                    if (response == true) {
                        booklist.writeToFile(booklist.fileBooks);
                        System.out.println("Write to file SUCCESS...");
                    } else {
                        System.out.println("Save canceled!");
                        System.out.println("Back to main menu...");
                    }
                    break;
                case 7:
                    do {
                        System.out.println("Choose author to delete!");
                        Author author = authorlist.chooseAuthor();
                        if (!authorlist.isEmpty()) {
                            if (author != null) {
                                if (booklist.searchByAuthor(author)) {
                                    if (retry = MyScanner.getBoolean("Are you sure delete '" + author.getName() + "' author?(y/n)")) {
                                        authorlist.remove(author);
                                        System.out.println("Author deleted!");
                                        changed=true;
                                    }
                                } else {
                                    System.out.println("This author has a book in the store, you cannot delete this author");                                   
                                }
                            }
                            retry = MyScanner.getBoolean("Do you want to delete other author?(y/n)");
                        } else {
                            System.out.println("Author list is empty!");
                            retry = false;
                        }
                    } while (retry);

                    break;
                default:
                    if (changed) {
                        response = MyScanner.getBoolean("Save changes to file?(y/n)");
                        if (response == true) {
                            booklist.writeToFile(booklist.fileBooks);
                            authorlist.writeToFile(authorlist.fileAuthors);
                        } else {
                            System.out.println("Data has not been saved!");
                        }
                    }
            }
        } while (mainChoice > 0 && mainChoice <= mainmenu.size());
    }

}
