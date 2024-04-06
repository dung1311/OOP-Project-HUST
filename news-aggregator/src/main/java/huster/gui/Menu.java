package huster.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener, ItemListener {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 100;
    public static final int ORIGIN_Y = 100;
    JPanel menu = new JPanel();

    public Menu() {
        Container contentPane = getContentPane(); // Sử dụng getContentPane() để lấy contentPane của JFrame

        setSize(X, Y);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("The MENU");
        contentPane.setLayout(new BorderLayout());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true); 

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
            public void actionPerformed(ActionEvent e){
                
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

        

                // articleButton.setBackground(Color.WHITE);
                // articleButton.setHorizontalTextPosition(SwingConstants.CENTER);
                // articleButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                // articleButton.setBorderPainted(false);
                // articleButton.setBounds(100 + 715 * i, 72 + 180 * j, 465, 135);
                
        // JButton jButton_article = new JButton("<html><div style='text-align: center;'>Bé Xuân Mai lon ton vip pro no1</div></html>");
        // jButton_article.setFont(font);
        // jButton_article.setIcon(BigarticleIcon);
        // jButton_article.setForeground(Color.BLUE);
        // jButton_article.setBounds(-10, 72, 600, 535);
        // // jButton_article.setPreferredSize(new Dimension(600, 492));
        // jButton_article.setBorderPainted(false);
        // jButton_article.setFocusPainted(false);
        // jButton_article.setContentAreaFilled(false);
        // jButton_article.setVerticalTextPosition(SwingConstants.BOTTOM);
        // jButton_article.setHorizontalTextPosition(SwingConstants.CENTER);
        // jButton_article.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        //         JFrame hotFrame = new JFrame("Hot News");
        //         hotFrame.setSize(800,800);
        //         hotFrame.setLocationRelativeTo(null);
        //         // hotFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);




        //         hotFrame.setVisible(true);
        //         contentPane.setVisible(false);
        //         hotFrame.addWindowListener(new java.awt.event.WindowAdapter() {
        //             @Override
        //             public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        //                 contentPane.setVisible(true);
        //             }
        //         });
        //   }
        // });
        
        // JButton jButton_article1 = new JButton(articleIcon);
        // jButton_article1.setBounds(825,72,465,132);

        // jButton_article1.setPreferredSize(new Dimension(465,132));
        // jButton_article1.setBorderPainted(false);
        // jButton_article1.setFocusPainted(false);
        // jButton_article1.setContentAreaFilled(false);
        // jButton_article1.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e){
                
        //     }
        // });

      
        JPanel toparticlePanel = new JPanel();
        toparticlePanel.setPreferredSize(new Dimension(800,800));
        toparticlePanel.setLayout(new BorderLayout());

        JButton BigarticleButton = new JButton(BigarticleIcon);
        BigarticleButton.setBackground(GREY_menu);
        BigarticleButton.setContentAreaFilled(false);
        BigarticleButton.setBorderPainted(false);
        JLabel BigarticleLabel = new JLabel("Be Xuan Mai");
        BigarticleLabel.setHorizontalAlignment(JLabel.CENTER);
        BigarticleLabel.setVerticalAlignment(JLabel.CENTER);
        toparticlePanel.add(BigarticleButton,BorderLayout.CENTER);
        toparticlePanel.add(BigarticleLabel,BorderLayout.SOUTH);

        JPanel articlePanel = new JPanel();
        articlePanel.setPreferredSize(new Dimension(1280, 1520));
        articlePanel.setLayout(new GridLayout(6,2,75,50));

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                
                JButton articleButton = new JButton(articleIcon);
                articleButton.setBackground(GREY_menu);
                        articleButton.setContentAreaFilled(false);

                        articleButton.setBorderPainted(false);
                JLabel articleLabel = new JLabel("Be Xuan Mai");
                articleLabel.setHorizontalAlignment(JLabel.CENTER);
                articleLabel.setVerticalAlignment(JLabel.CENTER);
                JPanel small_articlePanel = new JPanel();
                small_articlePanel.setLayout(new BorderLayout());
                small_articlePanel.add(articleButton,BorderLayout.CENTER);
                small_articlePanel.add(articleLabel,BorderLayout.SOUTH);
                small_articlePanel.setPreferredSize(new Dimension(465,180));
                articlePanel.add(small_articlePanel);
            }
        }






        JScrollPane scrollPane_suggested = new JScrollPane(articlePanel);
        scrollPane_suggested.setPreferredSize(new Dimension(1280, 1520));
        scrollPane_suggested.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        // contentPane.setBackground(GREY_menu);
        this.setBackground(GREY_menu);
        contentPane.add(menu, BorderLayout.NORTH);
        // contentPane.add(toparticlePanel, BorderLayout.CENTER);

        contentPane.add(scrollPane_suggested,BorderLayout.CENTER);  
    
        // this.pack();        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        }
    

    public void itemStateChanged(ItemEvent e) {
        String state;
        if (e.getStateChange() == ItemEvent.SELECTED) {
            state = "is selected ";
        } else {
            state = "is deselected ";
        }
        JOptionPane.showMessageDialog(this, "JComboBox Item '" + e.getItem() + "' " + state);
    }
}
