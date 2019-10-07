package edu.sdsu.cs;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Aleksandre Begiashvili
 * CS 310
 */
public class App {
    public static void main(String[] args) throws IOException, Exception {

        //File toFile = new File("src/main/java/edu/sdsu/cs/testWrite.txt");
        //File fromFile = new File("src/main/java/edu/sdsu/cs/test.txt");
        File mainDir = new File("D:/Intellij projects/CS310Project1");

        // STORING FILES WITH SIMILAR EXTENSIONS IN SEPARATE LISTS
        List<String> endWithJava = new ArrayList<>();
        List<String> endWithTxt = new ArrayList<>();
        search(".*\\.java", mainDir, endWithJava);
        search(".*\\.txt", mainDir, endWithTxt);


        // STORING A DATA AS LINES IN TWO SEPARATE LISTS
        List<String> listOfLinesJava = addLinesToList(endWithJava);
        List<String> listOfLinesTxt = addLinesToList(endWithTxt);

        // FORMAT LINES IN PREVIOUS LISTS, TRIM THEM AND REMOVE EXTRA WHITESPACE BETWEEN TOKENS
        formatLinesInList(listOfLinesJava);
        formatLinesInList(listOfLinesTxt);

        for (String s : listOfLinesTxt) {
            System.out.println(s);
        }
        for (String s : listOfLinesJava) {
            System.out.println(s);
        }

    }

    private static void formatLinesInList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).trim().replaceAll(" +", " "));
        }

    }

    // METHOD FOR ADDING LINES TO A LIST FROM A FILE, DOES NOT ADD EMPTY LINES
    private static List<String> addLinesToList(List<String> list) {
        List<String> listOfLines = new ArrayList<>();
        try {
            String delimiter = "##########";
            String previousIteration = "";
            for (String s : list) {
                File f = new File(s);
                Scanner scnr = new Scanner(f);
                while (scnr.hasNext()) {
                    String str = scnr.nextLine();
                    if (!s.equals(previousIteration)) {
                        listOfLines.add(delimiter);
                    }
                    if (!str.isEmpty()) {
                        listOfLines.add(str);
                    }

                    previousIteration = s;
                }

            }

            //listOfLines.add(delimiter);
            //return listOfLines;
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return listOfLines;
    }

    // METHOD FOR RECURSIVELY SEARCHING AND LISTING ALL FILES WITH GIVEN EXTENSIONS HAVING *folder* AS A ROOT
    private static void search(final String pattern, final File folder, List<String> result) {
        try {
            for (final File f : folder.listFiles()) {
                if (f.isDirectory()) {
                    search(pattern, f, result);
                }

                if (f.isFile()) {
                    if (f.getName().matches(pattern)) {
                        result.add(f.getAbsolutePath());
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }

    }
}


/*   THIS CODE SNIPPET READS FROM ONE FILE AND WRITES TO ANOTHER
        Scanner scnr = new Scanner(fromFile);
        String token1="", token2="", token3="", token4="", token5="";
        while(scnr.hasNext()) {
            token1 = scnr.next();
            token2 = scnr.next();
            token3 = scnr.next();
            token4 = scnr.next();
            token5 = scnr.next();
            System.out.println(token1 + " " + token2 + " " + token3 + " " + token4 + " " + token5);
        }
        scnr.close();

        FileWriter output = new FileWriter(toFile);
        output.write(token1 + token2 + token3 + token4 + token5);
        output.close();
*/
