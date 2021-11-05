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
import java.util.StringTokenizer;

/**
 *
 * @author phats
 */
public class AuthorList extends ArrayList<Author> {

    public String fileAuthors = "author.dat";

    public Author chooseAuthor() {
        Menu mnu = this.createMenu();
        Author result=null;
        try {
            result=this.get(mnu.getUserChoice() - 1);
        } catch (Exception e) {
        }
        return result;
    }

    private Menu createMenu() {
        Menu mnu = new Menu("Choose Author: ");
        for (int i = 0; i < this.size(); i++) {
            mnu.add(this.get(i).name);
        }
        return mnu;
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
                    String authorID = stk.nextToken().trim();
                    String name = stk.nextToken().trim();
                    Author author;
                    author = new Author(authorID, name);
                    this.add(author);
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

    public int search(String authorID) {
        for (int i = 0; i < this.size(); i++) {
            if (authorID == this.get(i).ID) {
                return i;
            }
        }
        return -1;
    }
}
