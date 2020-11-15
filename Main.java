// @author Divya Gubba
// CS 3345.004, Spring 2019
// Project 4:
/*
    This Red Black Tree program takes 2 command line arguments and the 1st argument will be
    input file name, and second will be output file name.
    Program will be given input file and generate output file, if not given.
    Main class will do operations specified in input file: insert, contains, printTree for
        Red Black bst, and will return output file of successful or unsuccessful insertion,
        and print tree when needed
 */
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        // check for 2 required arguments
        if (args.length != 2) {
            // output error message if not 2 args
            System.out.println("Need 2 arguments from command line");
            System.exit(0);
        }

        boolean isInt = true;
        // var for line read from file
        String lineRead;
        // var holds data after split ":"
        int data = 0;
        // var holds string to compare the operations in switch case
        String strCompareOp;
        // if 2 required arguments provided,read from file
        PrintWriter pwOut = null;

        //read from file
        Scanner scInpFile = null;

        //output file is second argument
        File outpFile = new File(args[1]);

        try {
            // format output as text to second argument in command line : output file
            pwOut = new PrintWriter(new File(args[1]));
            //System.out.println(pwOut);

            // open file to read from , args[0] contains 1st argument in command line : input file
            scInpFile = new Scanner(new File(args[0]));

            // if file doesn't exist, create a file
            if (!outpFile.exists()) {
                outpFile.createNewFile();
            }

            // need to create new objects of class
            // takes in Integer and String
            RedBlackTree<Integer> intRBT = new RedBlackTree<>();
            RedBlackTree<String> strRBT = new RedBlackTree<>();

            lineRead = scInpFile.nextLine().trim();

            // indication of inserting Integer or String objects
            if (lineRead.compareTo("Integer") == 0) {
                isInt = true;
            } else if(lineRead.compareTo("String") == 0) {
                isInt = false;
            } else {
                pwOut.println("Can only accept 2 objects, String or Integer");
                pwOut.close();
                System.exit(0);
            }

            // Start reading file
            while (scInpFile.hasNextLine()) {
                // read line from file, trim whitespaces
                lineRead = scInpFile.nextLine().trim();
                String[] strOp = new String[lineRead.length()];

                if (lineRead.contains(":")) {
                    // split by ":", strLine[0] will hold first part
                    strOp = lineRead.split(":");
                } else {
                    // if line read is not
                    strOp[0] = lineRead;
                }

                // Use switch case to compare first part of string before ":" to operation
                switch (strOp[0]) {

                    // Insert operation for RBT , returns true or false
                    case "Insert": {
                        try {
                            if(strOp[1] == null){
                                pwOut.println("Error in Line: " + strOp[0]);
                            } else {
                                // for inserting integers
                                if (isInt) {
                                    // will return True or False based on data argument in insert function
                                    pwOut.println(intRBT.insert(Integer.parseInt(strOp[1])));
                                    // for inserting Strings, returns true or false
                                } else {
                                    pwOut.println(strRBT.insert(strOp[1]));
                                }
                            }
                            // if data is not an element in tree, show illegalArgumentException message
                        } catch (IllegalArgumentException ie) {
                            pwOut.println("Error in insert: IllegalArgumentException raised");
                        }
                        break;
                    }

                    // print tree elements including deleted with a star
                    case "PrintTree": {
                        // print tree with integer data
                        if(isInt) {
                            pwOut.println(intRBT.toString());
                            // print tree with string data
                        } else {
                            pwOut.println(strRBT.toString());
                        }
                        break;
                    }

                    // returns true or false
                    case "Contains": {
                        try {
                            if(strOp[1] == null){
                                pwOut.println("Error in Line: " + strOp[0]);
                            } else {
                                if (isInt) {
                                    // for Integer object
                                    pwOut.println(intRBT.contains(Integer.parseInt(strOp[1])));
                                } else {
                                    // for String object
                                    pwOut.println(strRBT.contains(strOp[1]));
                                }
                            }
                            // if data is not an element in tree, show illegalArgumentException message
                        } catch (IllegalArgumentException ie) {
                            pwOut.println(ie.getMessage());
                        }
                        break;
                    }

                    // if none of the other cases work, send message with line that had error
                    default: {
                        pwOut.println("Error in line: " + strOp[0]);
                        break;
                    }
                }
            }

        }catch(FileNotFoundException e){
            // default message for exception
            System.out.println(e.getMessage());
        }catch(IOException ie){
            // handles exception
            ie.printStackTrace();
        }
        //flush print writer
        pwOut.flush();
        //close print writer
        pwOut.close();
    }


}
