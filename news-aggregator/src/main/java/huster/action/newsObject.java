package huster.action;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import com.google.gson.JsonObject;

import huster.gui.News;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newsObject {
    
    private String authorName;
    private String link;
    private String content;
    private String linkImage;
    private String title;
    private String summary;


    public newsObject(JsonObject jsonObject){
        this.authorName = jsonObject.get("author").getAsString();
        this.content = jsonObject.get("content").getAsString();
        this.link = jsonObject.get("link").getAsString();
        this.linkImage = jsonObject.get("linkImage").getAsString();
        this.title = jsonObject.get("title").getAsString();
        this.summary = jsonObject.get("summary").getAsString();
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
                News news = new News(getAuthorName(), "posting date", getLink(), getContent(), getTitle());
                news.setVisible(true);
                    
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

        return jPanel;
    }
}
