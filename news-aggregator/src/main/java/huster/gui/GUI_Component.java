package huster.gui;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;

public class GUI_Component {
} 

class MenuAndSearchPanel extends JPanel{
    private JPanel menu = new JPanel();
    private JPanel menuLeft = new JPanel();
    private JPanel menuRight = new JPanel();
    public JButton closeButton = new JButton();
    public JButton homeButton = new JButton();
    public JButton searchButton = new JButton();
    public JButton userButton = new JButton();

    public MenuAndSearchPanel(){
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

         // Thêm các nút vào menuLeft
        ImageIcon closeIcon = new ImageIcon("news-aggregator\\resource\\assets\\closeIcon.png");
        closeButton.setIcon(closeIcon);
        closeButton.setPreferredSize(new Dimension(50, 50));
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        menuLeft.add(closeButton);

        ImageIcon homeIcon = new ImageIcon("news-aggregator\\resource\\assets\\homeIcon.png");
        homeButton.setIcon(homeIcon);
        homeButton.setPreferredSize(new Dimension(50, 50));
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        menuLeft.add(homeButton);

        // Thêm các nút vào menuRight
        ImageIcon searchIcon = new ImageIcon("news-aggregator\\resource\\assets\\searchIcon.png");
        searchButton.setIcon(searchIcon);
        searchButton.setPreferredSize(new Dimension(50, 50));
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        menuRight.add(searchButton);

        ImageIcon userIcon = new ImageIcon("news-aggregator\\resource\\assets\\userIcon.png");
        userButton.setIcon(userIcon);
        userButton.setPreferredSize(new Dimension(50, 50));
        userButton.setBorderPainted(false);
        userButton.setFocusPainted(false);
        userButton.setContentAreaFilled(false);
        menuRight.add(userButton);
        
        menu.add(menuLeft, BorderLayout.WEST);
        menu.add(menuRight, BorderLayout.EAST);
        this.add(menu);
    }
    public void addCloseButtonListener(ActionListener listener) {
        closeButton.addActionListener(listener);
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
}

