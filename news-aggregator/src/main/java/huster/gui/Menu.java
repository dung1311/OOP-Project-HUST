package huster.gui;

import javax.swing.*;

import com.google.gson.JsonObject;

import huster.action.GetData;
import huster.action.newsObject;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    
    public int number_News = 12;
    private int seeMoreButtonClickedCount = 0;

    // luu tru bai viet
    private List<JPanel> newsList = new ArrayList<>();
    private SearchResult news_ScrollPane = new SearchResult();
    
    Header menu = new Header();

    public int getNumberNews() {
        return number_News;
    }

    public Menu() {
        Container contentPane = getContentPane(); 
        menu.addButtonForMenu();

        setSize(X, Y);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("The MENU");
        contentPane.setLayout(new BorderLayout());

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        System.setProperty("BLACK_menu", "0x222222");
        // Color BLACK_menu = Color.getColor("BLACK_menu");
        System.setProperty("GREY_menu", "0x000000");
        Color GREY_menu = Color.getColor("GREY_menu");
        
        menu.addTrendButtonListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Tweet"};
                int choice = JOptionPane.showOptionDialog(
                    Menu.this,
                    "Click the button to crawl",
                    "Crawl Tweet",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
                );

                if (choice == 0) {
                    handleTweetChoice();
                } 
            }
        });
        
        menu.addSearchButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchUI().setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menu);
                ScreenHistory.getInstance().pushScreen(frame);
                // dispose();
                Menu.this.setVisible(false);
            }
        });

        menu.addUserButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
                System.out.println("clicked!!");
            }
        });

        menu.addBackButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ScreenHistory.getInstance().isEmpty()) {
                    JFrame previousScreen = ScreenHistory.getInstance().popScreen();
                    previousScreen.setVisible(true);
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menu);
                    ScreenHistory.getInstance().pushScreen(frame);
                    // dispose();
                    Menu.this.setVisible(false);
                }
            }
        });
        ImageIcon toparticleIcon = new ImageIcon("news-aggregator\\resource\\assets\\BigarticleIcon.png");

        JPanel toparticlePanel = new JPanel();
        toparticlePanel.setPreferredSize(new Dimension(1280,440));
        toparticlePanel.setLayout(new BorderLayout());

        JButton topArticleButton = new JButton(toparticleIcon);
        topArticleButton.setBackground(GREY_menu);
        topArticleButton.setOpaque(false);
        topArticleButton.setContentAreaFilled(false);
        topArticleButton.setBorderPainted(false);
        topArticleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(topArticleButton);
                
                News news = new News("null", "null", "null", "null", "null");
                news.setVisible(true);
                ScreenHistory.getInstance().pushScreen(frame);
                dispose();
            }
        });
        JLabel toparticleLabel_title = new JLabel("null");
        toparticleLabel_title.setHorizontalAlignment(JLabel.CENTER);
        toparticleLabel_title.setVerticalAlignment(JLabel.CENTER);
        toparticlePanel.add(topArticleButton,BorderLayout.NORTH);
        toparticlePanel.add(toparticleLabel_title,BorderLayout.CENTER);
        
        news_ScrollPane.seeMoreActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                number_News += 6;
                seeMoreButtonClickedCount++;
                news_ScrollPane.setLayoutAndSize(seeMoreButtonClickedCount);
                addNews();
                revalidate();
                hideSeeMoreBtn();
            }
        });

        this.setBackground(GREY_menu);
        contentPane.add(menu, BorderLayout.NORTH);
        contentPane.add(news_ScrollPane,BorderLayout.CENTER);  
        //Creates and displays news
        news_ScrollPane.setVisible();
        createNews();
        addNews();
        
        revalidate();
    }

    

    //Method to generates newsList
    public List<JPanel> createNews(){
        List<JsonObject> _JsonObjects = new GetData().getNewsElements();
        
        for(int i = 0; i < 30; i++){
            JPanel _JPanel = new newsObject(_JsonObjects.get(i)).setAsJPanel();
            newsList.add(_JPanel);
        }

        // for(int i = 0; i < number_News; i++){
        //     articlePanel.add(newsList.get(i));
        // }
        
        return newsList;
    }

    //Method to help display news
    public void addNews(){
        for(int i = 0; i < number_News; i++){
            news_ScrollPane.addArticleCenter(newsList.get(i));
        }
    }

    public void addBackButton() {
        menu.addBackButtonForMenu();
    }

    public void hideSeeMoreBtn() {
        if(seeMoreButtonClickedCount == 2) {
            news_ScrollPane.hideSeeMoreBtn();
        }
    }
    
    private void handleTweetChoice() {
        String keyword = JOptionPane.showInputDialog(this, "Input Tweet username for crawling:");
        if (keyword != null && !keyword.trim().isEmpty()) {
            
            // Add handling for the tweet keyword here, for example:
            // searchTweets(keyword);
        } else {
            JOptionPane.showMessageDialog(this, "Please input something !!!");
        }
    }
}




