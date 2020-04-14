package amgs.utils;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Utils {

    public static String loadFileAsString(String path) {

        String content = "";

        try {
            // the URL.toString() methods appends a "file:" that must be removed
            Path pathJar = Paths.get(Utils.class.getResource(path).toString().replaceAll("file:",""));
            content = Files.readString( pathJar, StandardCharsets.UTF_8);

        } catch(IOException e) {
            e.printStackTrace();
        }
        
        return content;
    }

    public static int parseInt(String number) {
        int convertedInt;
        try {
            convertedInt = Integer.parseInt(number);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            convertedInt = 0;
        }
        return convertedInt;
    }
}