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
        setLocation(ORIGIN_X, ORIGIN_Y);
        setTitle("The MENU");
        contentPane.setLayout(new BorderLayout());

        // aButton = new JButton(nutech); // Khởi tạo và thiết lập văn bản cho nút A
        // aButton.setPreferredSize(new Dimension(50,30));
        // bButton = new JButton("Button B"); // Khởi tạo và thiết lập văn bản cho nút B
        // aButton.addActionListener(this); // Đăng ký ActionListener cho nút A
        // bButton.addActionListener(this); // Đăng ký ActionListener cho nút B

        // contentPane.add(aButton, BorderLayout.WEST); // Thêm nút A vào phía tây của contentPane
        // contentPane.add(bButton, BorderLayout.EAST); // Thêm nút B vào phía đông của contentPane

        // JScrollPane scrollText = new JScrollPane(textArea);
        // scrollText.setSize(200, 135);
        // contentPane.add(scrollText, BorderLayout.CENTER);

        // JPanel sinPanel = new JPanel(new FlowLayout());
        // sinBox = new JComboBox<>(SinItem);
        // sinBox.addItemListener(this);
        // sinPanel.add(sinBox);
        // contentPane.add(sinPanel, BorderLayout.NORTH);

        // buttonPanel.setLayout(new GridLayout(0,1));
        // for (int i = 1; i <= 20; i++) {
        //     JButton button = new JButton("Button " + i);
        //     buttonPanel.add(button);
        // }
        // JScrollPane scrollPane = new JScrollPane(buttonPanel);
        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // scrollPane.setPreferredSize(new Dimension(100, 20));
        // contentPane.add(scrollPane, BorderLayout.SOUTH);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true); // Hiển thị JFrame

        System.setProperty("BLACK_menu", "0x222222");
        Color BLACK_menu = Color.getColor("BLACK_menu");
        
        menu.setLayout(new FlowLayout(15));
        menu.setSize(1440,101);
        menu.setBackground(BLACK_menu);

        
        // ImageIcon userIcon = new ImageIcon("news-aggregator\\resource\\assets\\menuIcon.png");
        // ImageIcon searchIcon = new ImageIcon("news-aggregator\\resource\\assets\\menuIcon.png");
        // ImageIcon homeIcon = new ImageIcon("news-aggregator\\resource\\assets\\menuIcon.png");
 
        
        // JButton userButton = new JButton();
        // JButton searchButton = new JButton();
        // JButton homeButton = new JButton();

        ImageIcon menuIcon = new ImageIcon("news-aggregator\\\\resource\\\\assets\\\\menuIcon.png" );
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
            public void actionPerformed(ActionEvent e){
                
            }
        });
        // menu.add(menuButton);

        // menu.add(homeButton);

        // menu.add(searchButton);

        // menu.add(userButton);
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


        
        contentPane.add(menu, BorderLayout.NORTH);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // if (e.getSource() instanceof JButton) {
        //     JButton clickedButton = (JButton) e.getSource();
        //     if (clickedButton == aButton) {
        //         // Xử lý sự kiện khi nút A được nhấn
        //         JOptionPane.showMessageDialog(this, "Button A Clicked");
        //     } else if (clickedButton == bButton) {
        //         // Xử lý sự kiện khi nút B được nhấn
        //         JOptionPane.showMessageDialog(this, "Button B Clicked");
        //     }
        // }
        // else{
        //     String text = inputLine.getText();
        //     JOptionPane.showMessageDialog(this, text);
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