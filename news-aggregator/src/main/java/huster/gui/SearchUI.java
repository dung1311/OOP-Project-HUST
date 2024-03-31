package huster.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchUI extends JFrame implements ActionListener, ItemListener {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 0;
    public static final int ORIGIN_Y = 0;

    public SearchUI(){
        Container contentPane = getContentPane();
        setSize(X, Y);
        setResizable(true);
        setLocation(ORIGIN_X, ORIGIN_Y);
        setTitle("UI_TIM_KIEM");
        contentPane.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel menu = new JPanel();
        System.setProperty("BLACK_menu", "0x222222");
        Color BLACK_menu = Color.getColor("BLACK_menu");
        menu.setLayout(new BorderLayout());
        menu.setSize(1440,101);
        menu.setBackground(BLACK_menu);
        
        JPanel menuLeft = new JPanel();
        menuLeft.setBackground(BLACK_menu);

        JPanel menuRight = new JPanel();
        menuRight.setBackground(BLACK_menu);

        JPanel menuCenter = new JPanel();
        menuCenter.setBackground(BLACK_menu);

        // Thiết lập layout cho các panel con
        menuLeft.setLayout(new FlowLayout());
        menuRight.setLayout(new FlowLayout());
        menuCenter.setLayout(new FlowLayout());

        // Thêm các nút vào menuLeft
        ImageIcon menuIcon = new ImageIcon("news-aggregator\\resource\\assets\\menuIcon.png");
        JButton menuButton = new JButton(menuIcon);
        menuButton.setPreferredSize(new Dimension(65,65));
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(false);
        menuLeft.add(menuButton);

        ImageIcon homeIcon = new ImageIcon("news-aggregator\\resource\\assets\\homeIcon.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.setPreferredSize(new Dimension(65,65));
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        menuLeft.add(homeButton);

        // Thêm các nút vào menuRight
        ImageIcon searchIcon = new ImageIcon("news-aggregator\\resource\\assets\\searchIcon.png");
        JButton searchButton = new JButton(searchIcon);
        searchButton.setPreferredSize(new Dimension(65,65));
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        menuRight.add(searchButton);

        ImageIcon userIcon = new ImageIcon("news-aggregator\\resource\\assets\\userIcon.png");
        JButton userButton = new JButton(userIcon);
        userButton.setPreferredSize(new Dimension(65,65));
        userButton.setBorderPainted(false);
        userButton.setFocusPainted(false);
        userButton.setContentAreaFilled(false);
        menuRight.add(userButton);

        

        // Thêm các panel con vào menu
        menu.add(menuLeft);
        menu.add(menuRight);
        

        contentPane.add(menu, BorderLayout.NORTH);
        setVisible(true);
    }    

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
