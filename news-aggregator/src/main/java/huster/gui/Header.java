package huster.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class Header extends JPanel{
    private JPanel header = new JPanel();
    private JPanel headerLeft = new JPanel();
    private JPanel headerRight = new JPanel();
    public JButton trendButton = new JButton();
    public JButton homeButton = new JButton();
    public JButton searchButton = new JButton();
    public JButton userButton = new JButton();
    public JButton menuButton = new JButton();
    private JButton backButton = new JButton();

    public Header(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        System.setProperty("BLACK_menu", "0x222222");
        Color BLACK_menu = Color.getColor("BLACK_menu");
        header.setLayout(new BorderLayout());
        header.setSize(1440, 101);
        header.setBackground(BLACK_menu);

        headerLeft.setBackground(BLACK_menu);
        headerLeft.setLayout(new FlowLayout(FlowLayout.LEFT));

        headerRight.setBackground(BLACK_menu);
        headerRight.setLayout(new FlowLayout(FlowLayout.RIGHT));

        ImageIcon trendIcon = new ImageIcon("news-aggregator\\resource\\assets\\trendIcon.png");
        trendButton.setIcon(trendIcon);
        trendButton.setPreferredSize(new Dimension(50, 50));
        trendButton.setBorderPainted(false);
        trendButton.setFocusPainted(false);
        trendButton.setContentAreaFilled(false);

        ImageIcon backIcon = new ImageIcon("news-aggregator\\resource\\assets\\backIcon.png");
        backButton.setIcon(backIcon);
        backButton.setPreferredSize(new Dimension(50, 50));
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);

        ImageIcon homeIcon = new ImageIcon("news-aggregator\\resource\\assets\\homeIcon.png");
        homeButton.setIcon(homeIcon);
        homeButton.setPreferredSize(new Dimension(50, 50));
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);

        // Thêm các nút vào menuRight
        ImageIcon searchIcon = new ImageIcon("news-aggregator\\resource\\assets\\searchIcon.png");
        searchButton.setIcon(searchIcon);
        searchButton.setPreferredSize(new Dimension(50, 50));
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);

        ImageIcon userIcon = new ImageIcon("news-aggregator\\resource\\assets\\userIcon.png");
        userButton.setIcon(userIcon);
        userButton.setPreferredSize(new Dimension(50, 50));
        userButton.setBorderPainted(false);
        userButton.setFocusPainted(false);
        userButton.setContentAreaFilled(false);
        
        header.add(headerLeft, BorderLayout.WEST);
        header.add(headerRight, BorderLayout.EAST);

        this.add(header);
    }

    public void addButtonForNews() {
        headerLeft.add(backButton);
        headerLeft.add(homeButton);
        headerRight.add(searchButton);
        headerRight.add(userButton);
    }

    public void addButtonForMenu() {
        headerRight.add(trendButton);
        headerRight.add(searchButton);
        headerRight.add(userButton);
    }

    public void addButtonForSearchUI() {
        headerLeft.add(backButton);
        headerLeft.add(homeButton);
        headerRight.add(searchButton);
        headerRight.add(userButton);
    }

    public void addBackButtonForMenu() {
        headerLeft.add(backButton);
    }
    
    public void addHomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }
    
    public void addSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
    
    public void addUserButtonListener(ActionListener listener) {
        userButton.addActionListener(listener);
    }

    public void addMenuButtonListener(ActionListener listener) {
        menuButton.addActionListener(listener);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
