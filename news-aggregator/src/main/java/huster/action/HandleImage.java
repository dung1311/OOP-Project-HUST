package huster.action;

import java.awt.Image;

import java.net.URL;
import java.net.URLDecoder;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class HandleImage {

    public static Image resizeImage(String urlString) {
        Image image = null;
        try {
            image = ImageIO.read(new URL(urlString));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image newImage = image.getScaledInstance(476, 268, Image.SCALE_SMOOTH);

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
            image = HandleImage.resizeImage(decodeURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ImageIcon(image);
    }

}

