package huster.gui;

import javax.swing.*;

import huster.action.JHyperlink;
import huster.action.SearchData;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SearchResultUI extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;

    private int seeMoreButtonClickedCount = 0;
    private int number_News = 12;
    private static List<JPanel> listJPanels;
    private static List<String> listLinks;

    private JPanel tweetPanel = new JPanel();
    private SearchResult resPanel = new SearchResult();

    public SearchResultUI() {
        Container contentPane = getContentPane();
        setSize(X, Y);
        setResizable(false);
        setLocationRelativeTo(null);
        // setLocation(ORIGIN_X, ORIGIN_Y);
        setTitle("UI_TIM_KIEM");
        contentPane.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tweetPanel.setPreferredSize(new Dimension(1280, 2500));
        tweetPanel.setLayout(new BoxLayout(tweetPanel, BoxLayout.Y_AXIS));

        Header menuAndSearchPanel = new Header();
        menuAndSearchPanel.addButtonForSearchUI();
       
        menuAndSearchPanel.addBackButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ScreenHistory.getInstance().isEmpty()) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menuAndSearchPanel);
                    JFrame previousScreen = ScreenHistory.getInstance().popScreen();
                    previousScreen.setVisible(true);
                    ScreenHistory.getInstance().pushScreen(frame);
                    // frame.dispose();
                    frame.setVisible(false);
                }
            }
        });

        menuAndSearchPanel.addHomeButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menuAndSearchPanel);
                Menu previousScreen = MenuHistory.getInstance().peekScreen();
                previousScreen.setVisible(true);
                previousScreen.addBackButton();
                ScreenHistory.getInstance().pushScreen(frame);
                // frame.dispose();
                frame.setVisible(false);
            }
        });

        menuAndSearchPanel.addSearchButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menuAndSearchPanel);
                new SearchUI().setVisible(true);
                ScreenHistory.getInstance().pushScreen(frame);
                frame.dispose();
            }
        });
    
        resPanel.seeMoreActionListeners(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                seeMoreButtonClickedCount += 1;
                number_News += 6;
                resPanel.setLayoutAndSize(seeMoreButtonClickedCount);
                if(seeMoreButtonClickedCount > listJPanels.size() / 12 + 1){
                    resPanel.hideSeeMoreBtn();
                }
                addNews(number_News);
                revalidate();
            }
        });

        // // if(listJPanels.size() <= 12){
        //     resPanel.hideSeeMoreBtn();
        // }
        
       
        // Add components to the frame
        contentPane.add(menuAndSearchPanel, BorderLayout.NORTH);
        contentPane.add(resPanel, BorderLayout.CENTER);
        resPanel.setVisible();
        revalidate();
        repaint();
    }

    public static boolean listPanelIsNull(){
        return listJPanels.size() == 0;
            
    }
    public static void createNews(String s, String link){
        listJPanels = new SearchData(link).search(s);
    }
    
    public static void createLinks(String s, String path){
        listLinks = new SearchData(path).searchAsLink(s);
    }

    public void addNews(int n){
        if(listJPanels.size() < 12){
            n = listJPanels.size();
            for(int i = 0; i < n; i++) {
                resPanel.addArticleCenter(listJPanels.get(i));
            }
        }else{
            for(int i = 0; i < n; i++) {
                resPanel.addArticleCenter(listJPanels.get(i));
            }
        }
    }

    public void setUpTweet(){
        this.remove(resPanel);
        this.add(tweetPanel);
    }

    public void addLinks(){
        for(int i = 0; i < 20; i++){
            JLabel label = new JHyperlink(listLinks.get(i), listLinks.get(i), "Click here");
            tweetPanel.add(label);
            
        }
    }
}

// This class will display the result after search, can be used in menu aswell
// with consideration, contact me for more infomation
class SearchResult extends JScrollPane {
    private JPanel searchResult = new JPanel(new BorderLayout());
    private JPanel searchResult_Center;
    private JButton seeMoreButton = new JButton("See more!");

    public SearchResult() {
        setPreferredSize(new Dimension(1440, 2000));
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        setVisible(true);

        Font font30 = new Font("Arial", Font.PLAIN, 30);

        seeMoreButton.setFont(font30);
        // searchResultTweet = new JPanel();
        // searchResultTweet.setLayout(new BoxLayout(searchResultTweet,BoxLayout.Y_AXIS ));

        searchResult.setPreferredSize(new Dimension(1280, 2500));
        searchResult.setLayout(new BorderLayout());
        searchResult_Center = new JPanel();
        searchResult_Center.setLayout(new GridLayout(6, 2, 175, 0));
        searchResult_Center.setPreferredSize(new Dimension(1280, 1500));

        searchResult.add(searchResult_Center, BorderLayout.CENTER);
        searchResult.add(seeMoreButton, BorderLayout.SOUTH);

        // add(searchResult);
        setViewportView(searchResult);
    }

    public void setVisible() {
        searchResult_Center.setVisible(true);
    }

    public void seeMoreActionListeners(ActionListener e) {
        seeMoreButton.addActionListener(e);
    }

    public void hideSeeMoreBtn(){
        seeMoreButton.setVisible(false);
    }

    public void addArticleCenter(JPanel p) {
        searchResult_Center.add(p);
    }

    public void setLayoutAndSize(int n) {
        searchResult.setPreferredSize(new Dimension(1280, 2500 + 1200 * n));
        searchResult_Center.setLayout(new GridLayout(6 + 3 * n, 2, 175, 0));
        searchResult_Center.setPreferredSize(new Dimension(1280, 1500 + 1200 * n));
    }
}
