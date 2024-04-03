package huster.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 100;
    public static final int ORIGIN_Y = 100;
    JPanel menu = new JPanel();

    public Menu(Stack<JFrame> screenHistory) {
        Container contentPane = getContentPane(); // Sử dụng getContentPane() để lấy contentPane của JFrame

        setSize(X, Y);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("The MENU");
        contentPane.setLayout(new BorderLayout());

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        System.setProperty("BLACK_menu", "0x222222");
        Color BLACK_menu = Color.getColor("BLACK_menu");
        
        menu.setLayout(new FlowLayout(15));
        menu.setSize(1440,101);
        menu.setBackground(BLACK_menu);

        ImageIcon menuIcon = new ImageIcon("news-aggregator\\resource\\assets\\menuIcon.png" );
        JButton menuButton = new JButton(menuIcon);
        menuButton.setPreferredSize(new Dimension(65,65));
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
        homeButton.setPreferredSize(new Dimension(65,65));
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
            }
        });

        ImageIcon userIcon = new ImageIcon("news-aggregator\\resource\\assets\\userIcon.png");
        JButton userButton = new JButton(userIcon);
        userButton.setPreferredSize(new Dimension(65,65));
        userButton.setBorderPainted(false);
        userButton.setFocusPainted(false);
        userButton.setContentAreaFilled(false);
        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
            }
        });

        ImageIcon searchIcon = new ImageIcon("news-aggregator\\resource\\assets\\searchIcon.png");
        JButton searchButton = new JButton(searchIcon);
        searchButton.setPreferredSize(new Dimension(65,65));
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchUI searchUI = new SearchUI(screenHistory);
                searchUI.setVisible(true);
                setVisible(false);
                screenHistory.push(new Menu(screenHistory));
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

        Font font = new Font("ARIAL",Font.BOLD,25);

        ImageIcon articleIcon = new ImageIcon("news-aggregator\\resource\\assets\\articleIcon.png");
        ImageIcon BigarticleIcon = new ImageIcon("news-aggregator\\resource\\assets\\BigarticleIcon.png");

        // JButton jButton_article = new JButton("<html><div style='text-align: center;'>Bé Xuân Mai lon ton</div></html>");
        // jButton_article.setFont(font);
        // jButton_article.setIcon(BigarticleIcon);
        // jButton_article.setForeground(Color.BLUE);
        // jButton_article.setBounds(15,72,600,492);
        // // jButton_article.setPreferredSize(new Dimension(600,492));
        // jButton_article.setBorderPainted(false);
        // jButton_article.setFocusPainted(false);
        // jButton_article.setContentAreaFilled(false);
        // jButton_article.setVerticalTextPosition(SwingConstants.CENTER);
        // jButton_article.setHorizontalTextPosition(SwingConstants.LEFT);
        // jButton_article.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e){
                
        //     }
        // });
        JButton jButton_article = new JButton("<html><div style='text-align: center;'>Bé Xuân Mai lon ton</div></html>");
        jButton_article.setFont(font);
        jButton_article.setIcon(BigarticleIcon);
        jButton_article.setForeground(Color.BLUE);
        jButton_article.setBounds(15, 72, 600, 540);
        // jButton_article.setPreferredSize(new Dimension(600, 492));
        jButton_article.setBorderPainted(false);
        jButton_article.setFocusPainted(false);
        jButton_article.setContentAreaFilled(false);
        jButton_article.setVerticalTextPosition(SwingConstants.BOTTOM);
        jButton_article.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton_article.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                JFrame hotFrame = new JFrame("Hot News");
                hotFrame.setSize(800,800);
                hotFrame.setLocationRelativeTo(null);
                // hotFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);




                hotFrame.setVisible(true);
                contentPane.setVisible(false);
                hotFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        contentPane.setVisible(true);
                    }
                });
          }
        });
        
        JButton jButton_article1 = new JButton(articleIcon);
        jButton_article1.setBounds(825,72,465,132);

        jButton_article1.setPreferredSize(new Dimension(465,132));
        jButton_article1.setBorderPainted(false);
        jButton_article1.setFocusPainted(false);
        jButton_article1.setContentAreaFilled(false);
        jButton_article1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                News news = new News(screenHistory);
                news.setVisible(true);
                dispose();
            }
        });
        JButton jButton_article2 = new JButton(articleIcon);
        jButton_article2.setBounds(825,252,465,132);

        jButton_article2.setPreferredSize(new Dimension(465,132));
        jButton_article2.setBorderPainted(false);
        jButton_article2.setFocusPainted(false);
        jButton_article2.setContentAreaFilled(false);
        jButton_article2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                News news = new News(screenHistory);
                news.setVisible(true);
                dispose();
            }
        });
        JButton jButton_article3 = new JButton(articleIcon);
        jButton_article3.setBounds(825,432,465,132);

        jButton_article3.setPreferredSize(new Dimension(465,132));
        jButton_article3.setBorderPainted(false);
        jButton_article3.setFocusPainted(false);
        jButton_article3.setContentAreaFilled(false);
        jButton_article3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                News news = new News(screenHistory);
                news.setVisible(true);
                dispose();
            }
        });
        JButton jButton_article4 = new JButton(articleIcon);
        jButton_article4.setBounds(825,612,465,132);

        jButton_article4.setPreferredSize(new Dimension(465,132));
        jButton_article4.setBorderPainted(false);
        jButton_article4.setFocusPainted(false);
        jButton_article4.setContentAreaFilled(false);
        jButton_article4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                News news = new News(screenHistory);
                news.setVisible(true);
                dispose();
            }
        });
        JButton jButton_article5 = new JButton(articleIcon);
        jButton_article5.setBounds(825,792,465,132);

        jButton_article5.setPreferredSize(new Dimension(465,132));
        jButton_article5.setBorderPainted(false);
        jButton_article5.setFocusPainted(false);
        jButton_article5.setContentAreaFilled(false);
        jButton_article5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                News news = new News(screenHistory);
                news.setVisible(true);
                dispose();
            }
        });
        JButton jButton_article6 = new JButton(articleIcon);
        jButton_article6.setBounds(825,972,465,132);

        jButton_article6.setPreferredSize(new Dimension(465,132));
        jButton_article6.setBorderPainted(false);
        jButton_article6.setFocusPainted(false);
        jButton_article6.setContentAreaFilled(false);
        jButton_article6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                News news = new News(screenHistory);
                news.setVisible(true);
                dispose();
            }
        });
        JButton jButton_article7 = new JButton(articleIcon);
        jButton_article7.setBounds(56,612,465,132);

        jButton_article7.setPreferredSize(new Dimension(465,132));
        jButton_article7.setBorderPainted(false);
        jButton_article7.setFocusPainted(false);
        jButton_article7.setContentAreaFilled(false);
        jButton_article7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                News news = new News(screenHistory);
                news.setVisible(true);
                dispose();
            }
        });
        JButton jButton_article8 = new JButton(articleIcon);
        jButton_article8.setBounds(56,792,465,132);

        jButton_article8.setPreferredSize(new Dimension(465,132));
        jButton_article8.setBorderPainted(false);
        jButton_article8.setFocusPainted(false);
        jButton_article8.setContentAreaFilled(false);
        jButton_article8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                News news = new News(screenHistory);
                news.setVisible(true);
                dispose();
            }
        });
        JButton jButton_article9 = new JButton(articleIcon);
        jButton_article9.setBounds(56,972,465,132);

        jButton_article9.setPreferredSize(new Dimension(465,132));
        jButton_article9.setBorderPainted(false);
        jButton_article9.setFocusPainted(false);
        jButton_article9.setContentAreaFilled(false);
        jButton_article9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                News news = new News(screenHistory);
                news.setVisible(true);
                dispose();
            }
        });
    

        JPanel articlePanel = new JPanel();
        articlePanel.setPreferredSize(new Dimension(1440, 1200));
        articlePanel.setLayout(null);

        articlePanel.add(jButton_article);
        articlePanel.add(jButton_article1);
        articlePanel.add(jButton_article2);
        articlePanel.add(jButton_article3);
        articlePanel.add(jButton_article4);
        articlePanel.add(jButton_article5);
        articlePanel.add(jButton_article6);
        articlePanel.add(jButton_article7);
        articlePanel.add(jButton_article8);
        articlePanel.add(jButton_article9);





        JScrollPane scrollPane_suggested = new JScrollPane(articlePanel);
        scrollPane_suggested.setPreferredSize(new Dimension(1440, 800));
        scrollPane_suggested.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        
        contentPane.add(menu, BorderLayout.NORTH);
        contentPane.add(scrollPane_suggested,BorderLayout.CENTER);  
        this.pack();        
        
    }

}

