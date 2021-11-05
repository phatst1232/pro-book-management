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
public class Author {

    String ID;
    String name;

    public Author(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Author() {
        this.ID = "none";
        this.name = "none";
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showAll() {
        System.out.println("Athor ID: " + this.ID);
        System.out.println("Athor name: " + this.name);
    }

    @Override
    public String toString() {
        return ID + ";" + name;
    }

}
