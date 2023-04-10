package solarproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.security.CodeSource;

public class Files {

    public void clear() { //Clears all data from a text file
        PrintWriter clear;
        try {
            clear = new PrintWriter("src/files/appSettings.txt"); //getPath() + "\\appSettings.txt"
            clear.print("");
            clear.close();
        } catch (FileNotFoundException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data.");
        }
    }

    public void write(String data) { //Overwrites all data in file with data in parameter of method
        try {
            FileWriter writer = new FileWriter("src/files/appSettings.txt", true);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            Error err = new Error();
            err.sendError("There was a problem reading data.");
        }
    }

    public String read() { //Reads and returns all data from text file
        try {
            String lineContent = java.nio.file.Files.readAllLines(Paths.get("src/files/appSettings.txt")).get(0);
            return lineContent;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data.");
        }
        return null;
    }

    public String getPath() { //Finds the path that the running jar file is stored in so it can find the directory of the text file
        try {
            CodeSource codeSource = MainFrame.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParent();
            return jarDir;
        } catch (URISyntaxException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data.");
            return null;
        }
    }
}
