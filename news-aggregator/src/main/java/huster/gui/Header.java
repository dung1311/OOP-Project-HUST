package huster.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class Header extends JPanel{
    private JPanel menu = new JPanel();
    private JPanel menuLeft = new JPanel();
    private JPanel menuRight = new JPanel();
    public JButton closeButton = new JButton();
    public JButton homeButton = new JButton();
    public JButton searchButton = new JButton();
    public JButton userButton = new JButton();
    public JButton menuButton = new JButton();
    private JButton backButton = new JButton();

    public Header(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        System.setProperty("BLACK_menu", "0x222222");
        Color BLACK_menu = Color.getColor("BLACK_menu");
        menu.setLayout(new BorderLayout());
        menu.setSize(1440, 101);
        menu.setBackground(BLACK_menu);

        menuLeft.setBackground(BLACK_menu);
        menuLeft.setLayout(new FlowLayout(FlowLayout.LEFT));

        menuRight.setBackground(BLACK_menu);
        menuRight.setLayout(new FlowLayout(FlowLayout.RIGHT));

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
        
        menu.add(menuLeft, BorderLayout.WEST);
        menu.add(menuRight, BorderLayout.EAST);
        this.add(menu);
    }

    public void addButtonForNews() {
        menuLeft.add(backButton);
        menuLeft.add(homeButton);
        menuRight.add(searchButton);
        menuRight.add(userButton);
    }

    public void addButtonForMenu() {
        menuRight.add(searchButton);
        menuRight.add(userButton);
    }

    public void addButtonForSearchUI() {
        menuLeft.add(backButton);
        menuLeft.add(homeButton);
        menuRight.add(searchButton);
        menuRight.add(userButton);
    }

    public void addBackButtonForMenu() {
        menuLeft.add(backButton);
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
