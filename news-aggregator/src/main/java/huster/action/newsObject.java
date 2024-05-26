package huster.action;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.google.gson.JsonObject;

import huster.gui.News;
import huster.gui.ScreenHistory;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class newsObject {
    
    private String authorName;
    private String link;
    private String content;
    private String linkImage;
    private String title;
    private String summary;
    private String postingDate;


    public newsObject(JsonObject jsonObject){
        this.authorName = jsonObject.get("author").getAsString();
        this.content = jsonObject.get("content").getAsString();
        this.link = jsonObject.get("link").getAsString();
        this.linkImage = jsonObject.get("linkImage").getAsString();
        this.title = jsonObject.get("title").getAsString();
        this.summary = jsonObject.get("summary").getAsString();
        this.postingDate = jsonObject.get("datetimeCreation").getAsString();
    }

    public String getPostingDate() {
        return postingDate;
    }

    public String getAuthorName() {
        return authorName;
    }

 
    public String getLink() {
        return link;
    }

    public String getContent() {
        return content;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public JPanel setAsJPanel() {
        JPanel jPanel = new JPanel();
        JButton articleJButton = new JButton();


        articleJButton.setContentAreaFilled(false);
        articleJButton.setBorderPainted(false);
        articleJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                News news = new News(getAuthorName(), getPostingDate(), getLink(), getContent(), getTitle());
                news.setHeader(getTitle());
                news.setVisible(true);
                
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(articleJButton);
                ScreenHistory.getInstance().pushScreen(frame);
                frame.dispose();
            }
        });

        try {
            Image image =  GeneralHandle.resizeImage(this.getLinkImage());
            articleJButton.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel articleLable = new JLabel(this.getSummary());
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.setPreferredSize(new Dimension(477, 268));
        jPanel.setVisible(true);
        jPanel.add(articleJButton);
        jPanel.add(articleLable);
        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                News news = new News(getAuthorName(), getPostingDate(), getLink(), getContent(), getTitle());
                news.setHeader(getTitle());
                news.setVisible(true);
                
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(articleJButton);
                ScreenHistory.getInstance().pushScreen(frame);
                frame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        return jPanel;
    }
}
