package amgs.utils;

import java.io.*;
import java.net.*;
import java.nio.charset.*;

import javax.imageio.*;
import java.awt.image.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

import javax.sound.sampled.*;

public class Utils {

    public static String loadFileAsString(String path) {
        String content = "";
        // try to get path
        // WARN: cannot use Utils.class.getRessource(), not supported with .jar files
        try {
            // get file as InputStream and convert it to String with StringBuilder
            // more info: https://www.baeldung.com/convert-input-stream-to-string#java
            InputStream inputStream = Utils.class.getResourceAsStream(path);
            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader
            (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            content = textBuilder.toString();
            
        } catch(Exception e) {
            System.out.println("path generation failed in amgs.utils.Utils.java");
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

    public static void saveBufferedImageAsPNG(BufferedImage image) {
        try {
            File outputfile = new File("spritemap_test.png");
            ImageIO.write(image, "png", outputfile);
            System.out.println(outputfile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Saving BufferedImage as a file failed");
            e.printStackTrace();
        }
    }

    public static void saveStringAsFile(String content) {
        // java path problems: https://stackoverflow.com/questions/320542/how-to-get-the-path-of-a-running-jar-file
        // create a file in jar: https://www.journaldev.com/825/java-create-new-file
        // write to a File: https://www.tutorialspoint.com/javaexamples/file_write.htm
        // other methods to write to a file: https://www.baeldung.com/java-write-to-file

        String pathFromJarRoot = "/res/saves/score.txt";
        try {
            // get the path from .jar root
            String rawPathOfJar = Utils.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
            String pathOfJar = URLDecoder.decode(rawPathOfJar, "UTF-8");
            
            // get usable path
            String usablePath = pathOfJar + pathFromJarRoot;
            System.out.println(" usablePath = "+usablePath);

            // create a new file on location
            File outputfile = new File(usablePath);
            if (!outputfile.exists()) {
                outputfile.createNewFile();
            }

            // write to the file
            FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            /*
            byte[] strToBytes = fileString.getBytes();
            Files.write(usablePath, strToBytes, CREATE);*/

        } catch (Exception e) {
            System.out.println("Saving string as a file failed");
            e.printStackTrace();
        }
    }

    public static void paintCenterString(Graphics gImage, String text, Rectangle rect, Font font) {
        // from StackOverflow : https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
        FontRenderContext frc = new FontRenderContext(null, true, true);

        if(text == null || text == "") {
            text = "---";
        }

        Rectangle2D r2D = font.getStringBounds(text, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (rect.width / 2) - (rWidth / 2) - rX;
        int b = (rect.height / 2) - (rHeight / 2) - rY;

        gImage.setFont(font);
        gImage.drawString(text, rect.x + a, rect.y + b);
    }

    public static Clip loadSound(String pathFromJarRoot) {
        try {
            // Set up an audio input stream piped from the sound file.
            // StackOverflow : https://stackoverflow.com/questions/27854171/playing-music-in-a-java-game 
            // StackOverflow : https://stackoverflow.com/questions/5529754/java-io-ioexception-mark-reset-not-supported 
            InputStream audioSrc = Utils.class.getResourceAsStream(pathFromJarRoot);
            if(audioSrc == null) {
                System.out.println(" inputStream == null ");
            }
            //add buffer for mark/reset support
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);

            // Get a clip resource.
            Clip clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);

            return clip;
        } catch(Exception e) {
            System.out.println(" Problem with Utils.loadSound() ");
            e.printStackTrace();
            return null;
        }
    }

}
