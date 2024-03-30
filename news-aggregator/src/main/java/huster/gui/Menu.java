package huster.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener, ItemListener {
    private static final long serialVersionUID = 1L;
    public static final int X = 400;
    public static final int Y = 300;
    public static final int ORIGIN_X = 100;
    public static final int ORIGIN_Y = 100;
    public static final String[] SinItem = { "Lust", "Wrath", "Envy", "Sloth", "Pride", "Glut", "Gloom" };
    JTextField inputLine = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton aButton;
    JButton bButton;
    JComboBox<String> sinBox;
    JPanel buttonPanel = new JPanel();

    ImageIcon nutech = new ImageIcon("news-aggregator\\resource\\assets\\Picture3.jpg");

    public Menu() {
        Container contentPane = getContentPane(); // Sử dụng getContentPane() để lấy contentPane của JFrame

        setSize(X, Y);
        setResizable(true);
        setLocation(ORIGIN_X, ORIGIN_Y);
        setTitle("The MENU");
        contentPane.setLayout(new BorderLayout());

        aButton = new JButton(nutech); // Khởi tạo và thiết lập văn bản cho nút A
        aButton.setPreferredSize(new Dimension(50,30));
        bButton = new JButton("Button B"); // Khởi tạo và thiết lập văn bản cho nút B
        aButton.addActionListener(this); // Đăng ký ActionListener cho nút A
        bButton.addActionListener(this); // Đăng ký ActionListener cho nút B

        contentPane.add(aButton, BorderLayout.WEST); // Thêm nút A vào phía tây của contentPane
        contentPane.add(bButton, BorderLayout.EAST); // Thêm nút B vào phía đông của contentPane

        JScrollPane scrollText = new JScrollPane(textArea);
        scrollText.setSize(200, 135);
        contentPane.add(scrollText, BorderLayout.CENTER);

        JPanel sinPanel = new JPanel(new FlowLayout());
        sinBox = new JComboBox<>(SinItem);
        sinBox.addItemListener(this);
        sinPanel.add(sinBox);
        contentPane.add(sinPanel, BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(0,1));
        for (int i = 1; i <= 20; i++) {
            JButton button = new JButton("Button " + i);
            buttonPanel.add(button);
        }
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(100, 20));
        contentPane.add(scrollPane, BorderLayout.SOUTH);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true); // Hiển thị JFrame
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();
            if (clickedButton == aButton) {
                // Xử lý sự kiện khi nút A được nhấn
                JOptionPane.showMessageDialog(this, "Button A Clicked");
            } else if (clickedButton == bButton) {
                // Xử lý sự kiện khi nút B được nhấn
                JOptionPane.showMessageDialog(this, "Button B Clicked");
            }
        }
        else{
            String text = inputLine.getText();
            JOptionPane.showMessageDialog(this, text);
        }
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
