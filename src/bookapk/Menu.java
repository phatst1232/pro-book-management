/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookapk;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author phats
 */
public class Menu extends ArrayList<String> {

    String msg = "";
    public static Scanner sc = new Scanner(System.in);

    public Menu(String msg) {
        super();
        this.msg = msg;
    }

    public int getUserChoice() {
        boolean nhaptiep = true;
        int choice = 0;
        System.out.println(msg);
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + "-" + this.get(i));
        }
        do {
            try {
                System.out.println("Other: Quit- Choose: ");
                choice = Integer.parseInt(sc.nextLine());
                nhaptiep = false;
            } catch (NumberFormatException ex) {                  
                nhaptiep=false;
            }
        } while (nhaptiep == true);
        return choice;
    }
}
