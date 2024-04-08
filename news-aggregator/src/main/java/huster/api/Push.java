package huster.api;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Push {
    public static JButton setNews(String imgPath, int x, int y, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(imgPath);
        JButton news = new JButton(imageIcon);
        news.setBounds(x, y, width, height);

        news.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(imgPath);
                
            }
        });
        return news;
    }

}
