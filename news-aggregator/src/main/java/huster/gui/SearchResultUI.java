package huster.gui;

import javax.swing.*;

import huster.action.SearchData;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SearchResultUI extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 0;
    public static final int ORIGIN_Y = 0;

    public String articalNameJSON;
    private ImageIcon articleIcon;

    private String articleTitle;
    private String postingDate;

    private int seeMoreButtonClickedCount = 0;
    private SearchResult resPanel = new SearchResult();

    // private JPanel searchResult = new JPanel(new BorderLayout());
    // private JPanel searchResult_Center;
    // private JButton seeMoreButton = new JButton("See more!");

    public SearchResultUI(String findText) {
        Container contentPane = getContentPane();
        setSize(X, Y);
        setResizable(false);
        setLocationRelativeTo(null);
        // setLocation(ORIGIN_X, ORIGIN_Y);
        setTitle("UI_TIM_KIEM");
        contentPane.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
                    frame.dispose();
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
                frame.dispose();
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

        List<JPanel> listJPanels = new SearchData().search(findText);
        for (int i = 0; i < 12; i++) {
            resPanel.addArticle(listJPanels.get(i));
        }

        resPanel.seeMoreActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeMoreButtonClickedCount += 1;
                resPanel.setLayoutAndSize(seeMoreButtonClickedCount);
                for (int i = 0; i < 6; i++) {
                    resPanel.addArticle(listJPanels.get(i + 12 + (seeMoreButtonClickedCount - 1) * 6));
                }
            }
        });

        // tìm kiếm
        contentPane.add(menuAndSearchPanel, BorderLayout.NORTH);
        contentPane.add(resPanel, BorderLayout.CENTER);// Thay bằng class SearchResult
        resPanel.setVisible();
        revalidate();
        repaint();
    }

    public String getArticalNameJSON() {
        return articalNameJSON;
    }

    public void setArticalNameJSON(String articalNameJSON) {
        this.articalNameJSON = articalNameJSON;
    }

    public String getTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String s) {
        this.articleTitle = s;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String s) {
        this.postingDate = s;
    }

    public ImageIcon getArticleIcon() {
        return articleIcon;
    }

    public void setArticleIcon(ImageIcon articleIcon) {
        this.articleIcon = articleIcon;
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

    public void addArticle(JPanel p) {
        searchResult_Center.add(p);
    }

    public void setLayoutAndSize(int n) {
        searchResult.setPreferredSize(new Dimension(1280, 1500 + 1000 * n));
        searchResult_Center.setLayout(new GridLayout(6 + 3 * n, 2, 175, 0));
        searchResult_Center.setPreferredSize(new Dimension(1280, 1500 + 1000 * n));
    }
}
