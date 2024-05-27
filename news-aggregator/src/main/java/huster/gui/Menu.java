package huster.gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.google.gson.JsonObject;

import huster.action.GetData;
import huster.action.newsObject;
import huster.crawl.crawlTweet.ServerClient;
import huster.crawl.crawlTweet.TweetItem;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu extends SearchResultUI {
    private static final long serialVersionUID = 1L;

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

        setSize(1440, 1024);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("The MENU");
        contentPane.setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { // when closing app
                ServerClient.shutDownServer();
                System.out.println("Close Menu window");
                dispose();
            }
        });

        System.setProperty("BLACK_menu", "0x222222");
        // Color BLACK_menu = Color.getColor("BLACK_menu");
        System.setProperty("GREY_menu", "0x000000");
        Color GREY_menu = Color.getColor("GREY_menu");

        menu.addTrendButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = { "Tweet" };
                int choice = JOptionPane.showOptionDialog(
                        Menu.this,
                        "Click the button to crawl",
                        "Crawl Tweet",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (choice == 0) {
                    Thread crawlTweetThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                handleCrawlChoice();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    crawlTweetThread.start();
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

        news_ScrollPane.seeMoreActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                number_News += 6;
                seeMoreButtonClickedCount++;
                news_ScrollPane.setLayoutAndSize(seeMoreButtonClickedCount);
                addNews();
                revalidate();
                if (seeMoreButtonClickedCount == 2) {
                    news_ScrollPane.hideSeeMoreBtn();
                }
            }
        });

        this.setBackground(GREY_menu);
        contentPane.add(menu, BorderLayout.NORTH);
        contentPane.add(news_ScrollPane, BorderLayout.CENTER);
        // Creates and displays news
        news_ScrollPane.setVisible();
        createNews();
        addNews();

        revalidate();
    }

    // Method to generates newsList
    public List<JPanel> createNews() {
        List<JsonObject> _JsonObjects = new GetData().getNewsElements();

        for (int i = 0; i < 30; i++) {
            JPanel _JPanel = new newsObject(_JsonObjects.get(i)).setAsJPanel();
            newsList.add(_JPanel);
        }

        return newsList;
    }

    // Method to help display news
    public void addNews() {
        for (int i = 0; i < number_News; i++) {
            news_ScrollPane.addArticleCenter(newsList.get(i));
        }
    }

    public void addBackButton() {
        menu.addBackButtonForMenu();
    }

    private void handleCrawlChoice() throws IOException {
        String keyword = JOptionPane.showInputDialog(this, "Input Tweet username for crawling:");
        if (keyword != null && !keyword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "The process will take several minutes. \nRelax and enjoy another of our articles. You can close this window but not Menu window");
            TweetItem tweet = new TweetItem(keyword);
            tweet.crawlTweet();
            tweet.drawChart();

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menu);
            ScreenHistory.getInstance().pushScreen(frame);

            SearchResultUI.createLinks(keyword, "news-aggregator\\resource\\data\\tweetData\\" + keyword + ".json");
            SearchResultUI searchTweet = new SearchResultUI();
            searchTweet.setUpTweet();
            searchTweet.addLinks();

            Menu.this.setVisible(false);
            searchTweet.setVisible(true);
            
            JPanel imagePanel = new JPanel() {
                private static final long serialVersionUID = 1L;
                private Image image;

                {
                    try {
                        image = ImageIO.read(new File("news-aggregator\\resource\\data\\tweetData\\"
                                + keyword + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (image != null) {
                        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            imagePanel.setPreferredSize(new Dimension(1200, 900));

            // Display news statistics
            JOptionPane.showMessageDialog(this, imagePanel, "Crawl Result",
                    JOptionPane.PLAIN_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(this, "Please input something !!!");
        }
    }
}
