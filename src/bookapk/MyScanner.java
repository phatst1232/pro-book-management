/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookapk;

import static bookapk.Menu.sc;

/**
 *
 * @author phats
 */
public class MyScanner {

    static boolean retry = false;

    public static boolean containsIgnoreCase(String src, String what) {
        final int length = what.length();
        if (length == 0) {
            return true; // Empty string is contained
        }
        final char firstLo = Character.toLowerCase(what.charAt(0));
        final char firstUp = Character.toUpperCase(what.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp) {
                continue;
            }

            if (src.regionMatches(true, i, what, 0, length)) {
                return true;
            }
        }

        return false;
    }

    public static String getString(String msg) {
        String s = "";
        do {
            try {
                System.out.println(msg);
                s = sc.nextLine();
                if (s.isEmpty()) {
                    System.out.println("Input is empty! Please, try again!");
                    retry = true;
                } else {
                    retry = false;
                }
            } catch (Exception e) {
                System.out.println("Input is not valid! Try again!");
                retry = true;
            }
        } while (retry);
        return s;
    }

    public static boolean getBoolean(String msg) {
        String asw = "";
        boolean redo = true;
        boolean b = true;

        do {
            try {
                System.out.println(msg);
                asw = sc.nextLine();
                if (asw.equalsIgnoreCase("YES") || asw.equalsIgnoreCase("1") || asw.equalsIgnoreCase("TRUE") || asw.equalsIgnoreCase("Y")) {
                    b = true;
                    redo = false;
                } else if (asw.equalsIgnoreCase("NO") || asw.equalsIgnoreCase("0") || asw.equalsIgnoreCase("FALSE") || asw.equalsIgnoreCase("N")) {
                    b = false;
                    redo = false;
                } else {
                    redo = true;
                }
            } catch (Exception e) {
                System.out.println("Input is not valid! Try again!");
                redo = true;
            }
        } while (redo);
        return b;
    }

    public static double getDouble(String msg, double min, double max) {
        double d = 0;
        do {
            try {
                System.out.println(msg);
                d = Double.parseDouble(sc.nextLine().trim());
                if (d < min || d > max) {
                    System.out.println("Entered value is out of range!");
                    retry = true;
                } else {
                    retry = false;
                }
            } catch (Exception e) {
                System.out.println("Entered value is not valid! Input real number only!");
                retry = true;
            }
        } while (retry);
        return d;
    }

    public static double getDouble(String msg, double max) {
        double d = 0;
        do {
            try {
                System.out.println(msg);
                d = Double.parseDouble(sc.nextLine().trim());
                if (d < 0 || d > max) {
                    System.out.println("Entered value is out of range!");
                    retry = true;
                } else {
                    retry = false;
                }
            } catch (Exception e) {
                System.out.println("Entered value is not valid! Input real number only!");
                retry = true;
            }
        } while (retry);
        return d;
    }

    public static String getauthorID(String msg) {
        String id = "";
        do {
            try {
                System.out.println("\n" + msg);
                System.out.println("The Author ID model patterm is: AU*****");
                System.out.println("'AU' is constant character, ***** is five random number or alphabet charator.");
                System.out.println("Enter 5 charactor: ");
                id = sc.nextLine();
                if (id.matches("\\w{5}")) {    //*******************
                    retry = false;
                } else {
                    retry = true;
                }
            } catch (Exception e) {
                System.out.println("The 5 charactor contain only alphabet and nuber charactor!");
                retry = true;
            }
        } while (retry);
        return "AU" + id;
    }

    public static String getbookID(String msg) {
        String id = "";
        do {
            try {
                System.out.println("\n" + msg);
                System.out.println("The Book ID model patterm is: B*****");
                System.out.println("'B' is constant character, ***** is five random number or alphabet charator.");
                System.out.println("Enter 5 charactor: ");
                id = sc.nextLine();
                if (id.matches("\\w{5}")) {    //*******************
                    retry = false;
                } else {
                    retry = true;
                }
            } catch (Exception e) {
                System.out.println("The 5 charactor contain only alphabet and nuber charactor!");
                retry = true;
            }
        } while (retry);
        return "B" + id;
    }

    public static String getISBN(String msg) {
        String isbn = "";

        do {
            try {
                System.out.println("\n" + msg);
                System.out.println("ISBN series is a string of 10 or 13 numbers:");
                isbn = sc.nextLine().replaceAll("\\s+", "");
                if (isbn.matches("\\d{10}")) {
                    retry = false;
                } else if (isbn.matches("\\d{13}")) {
                    retry = false;
                } else {
                    System.out.println("Input is invalid!");
                    retry = true;
                }
            } catch (Exception e) {
                System.out.println("Input is invalid!");
                retry = true;
            }
        } while (retry);
        return isbn;
    }
}
