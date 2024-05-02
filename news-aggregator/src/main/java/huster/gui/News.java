package huster.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import huster.action.GeneralHandle;
import huster.action.JHyperlink;

import java.awt.*;
import java.awt.event.*;


public class News extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 100;
    public static final int ORIGIN_Y = 100;
    // header of news UI
    Header menu = new Header(); 
    
    private JLabel jLabel_news_header;
    // private JTextArea jTextArea_news_header;
    private JTextArea jTextArea_news_center;
    private JTextArea jTextArea_news_author;
    private JTextArea jTextArea_news_postingDate;
    private JTextArea jTextArea_news_link;
    private JLabel jLabel_image;
    // private ScreenHistory historyStack;


    public News(String nameAuthor, String postingDate, String link, String textContent, String title) {
        ScreenHistory.getInstance();
        ScreenHistory.getInstance().pushScreen(this);
        
        setSize(X, Y);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("The MENU");

        // setDefaultCloseOperation(EXIT_ON_CLOSE);

        System.setProperty("BLACK_menu", "0x222222");

        Font font30B = new Font("Arial", Font.BOLD, 30);
        Font font20 = new Font("Arial", Font.PLAIN, 20);
        Font font15I = new Font("Arial", Font.ITALIC, 15);

        menu.addHomeButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new Menu().setVisible(true);;
                dispose();
            }
        });

        menu.addSearchButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchUI searchUI = new SearchUI();
                // ScreenHistory.getInstance().pushScreen(new News());
                searchUI.setVisible(true);
                dispose();;
            }
        });

        // create centre panel
        JPanel jPanel_news = new JPanel();
        jPanel_news.setLayout(new BorderLayout());

        ImageIcon ngotUpBo = new ImageIcon("news-aggregator\\resource\\assets\\ngotUpBo350_180.jpg");
        jLabel_image = new JLabel(ngotUpBo, JLabel.RIGHT);
        jLabel_image.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        jLabel_news_header = new JLabel(title, JLabel.CENTER);
        jLabel_news_header.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel_news_header.setFont(font30B);

        JPanel jPanel_news_header = new JPanel();
        jPanel_news_header.setLayout(new BorderLayout());

        jPanel_news_header.add(jLabel_news_header, BorderLayout.CENTER);

        setAuthor(nameAuthor);
        setPostingDate(postingDate);
        setLink(link);
        
        JPanel jPanel_news_center = new JPanel(new BorderLayout());
        JPanel jPanel_news_source = new JPanel();
        jPanel_news_source.setLayout(new GridLayout(1, 2));
        
        JPanel jPanel_news_source_left = new JPanel(new GridLayout(3, 1));
        jPanel_news_source_left.add(jTextArea_news_author);
        jPanel_news_source_left.add(jTextArea_news_postingDate);
        jPanel_news_source_left.add(jTextArea_news_link);

        jPanel_news_source.add(jPanel_news_source_left);
        // jPanel_news_source.add(jLabel_image);

        
        String text = textContent;

        jTextArea_news_center = new JTextArea();
        jTextArea_news_center.setText(text);

        // Đặt khoảng cách lề cho JTextArea
        jTextArea_news_center.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.BLACK), // Lề ngoài
            new EmptyBorder(10, 0, 0, 0) // Lề bên trong
        ));
        jTextArea_news_center.setFont(font20);
        jTextArea_news_center.setLineWrap(true);
        jTextArea_news_center.setWrapStyleWord(true);
        jTextArea_news_center.setEditable(false);

        jPanel_news_center.add(jPanel_news_source, BorderLayout.NORTH);
        jPanel_news_center.add(jTextArea_news_center, BorderLayout.CENTER);


        jPanel_news.add(jLabel_news_header, BorderLayout.NORTH);
        jPanel_news.add(jPanel_news_center, BorderLayout.CENTER);



        JScrollPane scrollPane_News = new JScrollPane(jPanel_news);
        scrollPane_News.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane_News.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_News.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));



        // add into this
        this.setLayout(new BorderLayout());
        this.add(menu, BorderLayout.NORTH);
        this.add(scrollPane_News, BorderLayout.CENTER);
    }

    public void setHeader(String s) {
        jLabel_news_header.setText(s);
    }

    public void setImage(ImageIcon icon) {
        jLabel_image.setIcon(icon);
    }

    public void setAuthor(String authorName) {
        jTextArea_news_author = new JTextArea("Author: " + authorName);
        jTextArea_news_author.setBackground(jLabel_image.getBackground());
        jTextArea_news_author.setFont(new Font("Arial", Font.ITALIC, 15));
        jTextArea_news_author.setEditable(false);
        jTextArea_news_author.setLineWrap(true);
        jTextArea_news_author.setWrapStyleWord(true);
    }

    public void setPostingDate(String postingDate) {
        jTextArea_news_postingDate = new JTextArea("Posting date: " + postingDate);
        jTextArea_news_postingDate.setBackground(jLabel_image.getBackground());
        jTextArea_news_postingDate.setFont(new Font("Arial", Font.ITALIC, 15));
        jTextArea_news_postingDate.setEditable(false);
        jTextArea_news_postingDate.setLineWrap(true);
        jTextArea_news_postingDate.setWrapStyleWord(true);
    }

    public void setLink(String link) {
        jTextArea_news_link = new JTextArea(GeneralHandle.clickableLink(link));
        jTextArea_news_link.setBackground(jLabel_image.getBackground());
        jTextArea_news_link.setFont(new Font("Arial", Font.ITALIC, 15));
        jTextArea_news_link.setEditable(false);
        jTextArea_news_link.setLineWrap(true);
        jTextArea_news_link.setWrapStyleWord(true);
    }

    public void setNewsContent(String s) {
        jTextArea_news_center.setText(s);
    }
}


