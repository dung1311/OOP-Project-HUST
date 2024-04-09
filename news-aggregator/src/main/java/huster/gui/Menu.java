package huster.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 100;
    public static final int ORIGIN_Y = 100;
    JPanel menu = new JPanel();

    private int seeMoreButtonClickedCount = 0;
    private JButton articleButton;
    private JLabel articleLabel;
    private JPanel small_articlePanel;
    private JPanel labelPanel;
    private JPanel articlePanel;
    private ImageIcon articleIcon;
    private ScreenHistoryManager screenHistoryManager;

    public Menu() {
        screenHistoryManager.pushFrame(this);
        Container contentPane = getContentPane(); // Sử dụng getContentPane() để lấy contentPane của JFrame

        setSize(X, Y);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("The MENU");
        contentPane.setLayout(new BorderLayout());

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        System.setProperty("BLACK_menu", "0x222222");
        Color BLACK_menu = Color.getColor("BLACK_menu");
        System.setProperty("GREY_menu", "0xFFFFFF");
        Color GREY_menu = Color.getColor("GREY_menu");
        
        menu.setLayout(new FlowLayout(15));
        menu.setSize(1440,101);
        menu.setBackground(BLACK_menu);

        ImageIcon menuIcon = new ImageIcon("news-aggregator\\\\resource\\\\assets\\\\menuIcon.png" );
        JButton menuButton = new JButton(menuIcon);
        menuButton.setPreferredSize(new Dimension(50,50));
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

            }
        });

        menu.add(menuButton);

        ImageIcon homeIcon = new ImageIcon("news-aggregator\\resource\\assets\\homeIcon.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.setPreferredSize(new Dimension(50,50));
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
            }
        });

        ImageIcon userIcon = new ImageIcon("news-aggregator\\resource\\assets\\userIcon.png");
        JButton userButton = new JButton(userIcon);
        userButton.setPreferredSize(new Dimension(50,50));
        userButton.setBorderPainted(false);
        userButton.setFocusPainted(false);
        userButton.setContentAreaFilled(false);
        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
            }
        });

        ImageIcon searchIcon = new ImageIcon("news-aggregator\\resource\\assets\\searchIcon.png");
        JButton searchButton = new JButton(searchIcon);
        searchButton.setPreferredSize(new Dimension(50,50));
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchUI searchUI = new SearchUI();
                searchUI.setVisible(true);
                dispose();
            }
        });

        menu.setLayout(new BorderLayout());

        JPanel jPanel_left=new JPanel();
        jPanel_left.setLayout(new FlowLayout(2));
        jPanel_left.add(menuButton);
        jPanel_left.add(homeButton);
        jPanel_left.setBackground(BLACK_menu);
        menu.add(jPanel_left,BorderLayout.WEST);

        JPanel jPanel_right=new JPanel();
        jPanel_right.setLayout(new FlowLayout(2));
        jPanel_right.add(searchButton);
        jPanel_right.add(userButton);
        jPanel_right.setBackground(BLACK_menu);

        menu.add(jPanel_right,BorderLayout.EAST);

        Font font40 = new Font("ARIAL",Font.PLAIN,40);

        articleIcon = new ImageIcon("news-aggregator\\resource\\assets\\articleIcon.png");
        ImageIcon BigarticleIcon = new ImageIcon("news-aggregator\\resource\\assets\\BigarticleIcon.png");

      
        JPanel toparticlePanel = new JPanel();
        toparticlePanel.setPreferredSize(new Dimension(1280,625));
        toparticlePanel.setLayout(new BorderLayout());

        JPanel nothingPanel = new JPanel();
        nothingPanel.setPreferredSize(new Dimension(1280,75));

        JButton BigarticleButton = new JButton(BigarticleIcon);
        BigarticleButton.setBackground(GREY_menu);
        BigarticleButton.setOpaque(false);
        BigarticleButton.setContentAreaFilled(false);
        BigarticleButton.setBorderPainted(false);
        BigarticleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                News news = new News();
                news.setVisible(true);
                dispose();
            }
        });
        JLabel BigarticleLabel_title = new JLabel("Be Xuan Mai");
        BigarticleLabel_title.setHorizontalAlignment(JLabel.CENTER);
        BigarticleLabel_title.setVerticalAlignment(JLabel.CENTER);
        toparticlePanel.add(BigarticleButton,BorderLayout.NORTH);
        toparticlePanel.add(BigarticleLabel_title,BorderLayout.CENTER);
        toparticlePanel.add(nothingPanel,BorderLayout.SOUTH);

        articlePanel = new JPanel();
        articlePanel.setPreferredSize(new Dimension(1280, 1500));
        articlePanel.setLayout(new GridLayout(6,2,175,0));
        createSmall_articlePanel(6);

        
        JPanel fullarticlePanel = new JPanel();
        fullarticlePanel.setPreferredSize(new Dimension(1280,2500));
        fullarticlePanel.setLayout(new BorderLayout());
        fullarticlePanel.add(toparticlePanel,BorderLayout.NORTH);
        fullarticlePanel.add(articlePanel,BorderLayout.CENTER);


        JButton seeMoreButton = new JButton("See more!");
        seeMoreButton.setFont(font40);
        seeMoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeMoreButtonClickedCount++;
                fullarticlePanel.setPreferredSize(new Dimension(1280, 2500 + 1200 * seeMoreButtonClickedCount));
                articlePanel.setPreferredSize(new Dimension(1280, 1500 + 1200 * seeMoreButtonClickedCount));
                articlePanel.setLayout(new GridLayout(6 + 3 * seeMoreButtonClickedCount,2,175,0));
                createSmall_articlePanel(3);
                revalidate();
            }
        });
        fullarticlePanel.add(seeMoreButton, BorderLayout.SOUTH);



        JScrollPane scrollPane_suggested = new JScrollPane(fullarticlePanel);
        scrollPane_suggested.setPreferredSize(new Dimension(1280, 800));
        scrollPane_suggested.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_suggested.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_suggested.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));




        // contentPane.setBackground(GREY_menu);
        this.setBackground(GREY_menu);
        contentPane.add(menu, BorderLayout.NORTH);
        // contentPane.add(toparticlePanel, BorderLayout.CENTER);

        contentPane.add(scrollPane_suggested,BorderLayout.CENTER);  
    
        // this.pack();        
        
    }

    public void createSmall_articlePanel(int numberOfRows) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                articleButton = new JButton(articleIcon);
                // articleButton.setBackground(GREY_menu);
                articleButton.setContentAreaFilled(false);
                articleButton.setBorderPainted(false);
                articleButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        News news = new News();
                        news.setVisible(true);
                        dispose();
                    }
                });

                articleLabel = new JLabel("Be Xuan Mai");
                articleLabel.setHorizontalAlignment(JLabel.CENTER);
                articleLabel.setVerticalAlignment(JLabel.CENTER);

                small_articlePanel = new JPanel();
                small_articlePanel.setLayout(new BoxLayout(small_articlePanel, BoxLayout.Y_AXIS));
                small_articlePanel.add(articleButton);

                labelPanel = new JPanel();
                labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                labelPanel.add(articleLabel);
                small_articlePanel.add(labelPanel);

                small_articlePanel.setPreferredSize(new Dimension(465, 180));

                articlePanel.add(small_articlePanel);
            }
        }

    }
}