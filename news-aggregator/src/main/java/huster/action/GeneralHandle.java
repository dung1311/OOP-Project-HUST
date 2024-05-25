package huster.action;

import java.awt.Image;

import java.net.URL;
import java.net.URLDecoder;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GeneralHandle {

    public static Image resizeImage(String urlString) {
        Image image = null;
        try {
            image = ImageIO.read(new URL(urlString));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image newImage = image.getScaledInstance(600, 268, Image.SCALE_SMOOTH);

        return newImage;
    }

    public static ImageIcon readImage(String urlString) {

        String decodeURL = new String();
        try {
            decodeURL = URLDecoder.decode(urlString, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Image image = null;
        try {
            image = GeneralHandle.resizeImage(decodeURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ImageIcon(image);
    }

    public static String ReadURL(String urlString) {
        String decodeURL = new String();
        try {
            decodeURL = URLDecoder.decode(urlString, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decodeURL;
    }

    public static String clickableLink(String link) {
        return "<html><a href='https://www.codejava.net/java-se/swing/how-to-create-hyperlink-with-jlabel-in-java-swing'>click here</a></html>";
    }

}
