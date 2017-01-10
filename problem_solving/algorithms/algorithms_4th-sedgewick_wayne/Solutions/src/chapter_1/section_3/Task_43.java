package chapter_1.section_3;

import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import utils.CliUtils;

/*
 * Task 1.3.43: Listing files.
 * 
 * A folder is a list of files and folders. Write a program that takes the name of a folder as
 * a command-line argument and prints out all of the files contained in that folder, with the
 * contents of each folder recursively listed (indented) under that folder's name.
 * _Hint_: Use a queue, and see java.io.File.
 */
public class Task_43 {

    public static void listFiles(File file, Queue<? super String> f_names, int indent) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null)
                return;
            Arrays.stream(files).forEach(f -> listFiles(f, f_names, indent + 2));
        } else if (file.isFile()) {
            String format_str = String.format("%%%ds%%s\n", indent);
            f_names.enqueue(String.format(format_str, "", file.getName()));
        }
    }
    
    public static void cliMode() {
        while (true) {
            StdOut.print("=> Enter folder name: ");
            String folder_name = StdIn.readString();
            Queue<String> fnames = new Queue<>();
            listFiles(new File(folder_name), fnames, 0);
            StdOut.println("<= Files: ");
            StdOut.println(CliUtils.iterableToString(fnames));
            StdOut.println();
        }

    }
    
    public static void main(String [] args) {
        if (args.length < 1) {
            StdOut.println("Args: <dir>");
            System.exit(-1);
        }
        
        Queue<String> fnames = new Queue<>();
        listFiles(new File(args[0]), fnames, 0);
        StdOut.println(CliUtils.iterableToString(fnames));
    }
}
