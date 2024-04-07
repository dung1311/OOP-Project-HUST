package huster.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Div {

}

class CreateSearchPanel extends JPanel {
    public CreateSearchPanel() {
        setPreferredSize(new Dimension(1440, 40));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }
}



// Cài đặt cấu tạo cho searchBar
class SearchBar extends JTextField {
    public SearchBar(int columns) {
        super(columns);
        setupSearchBar();
    }

    private void setupSearchBar() {
        // Cấu hình các thuộc tính cho searchBar ở đây
        Font font = new Font("Arial", Font.PLAIN, 30);
        setFont(font);
        setPreferredSize(new Dimension(1440, 60));
    }
}

// Có thể thêm một đống JLabel vào để chọn chủ đề?
class ListOfCate extends JPanel {
    public ListOfCate() {
        System.setProperty("BLACK_menu", "0x222222");
        Color BLACK_menu = Color.getColor("BLACK_menu");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1440, 620));
        setBackground(BLACK_menu);
        setFont(new Font("Arial", Font.PLAIN, 14));

        for (int i = 0; i < 10; i++) {
            JLabel label1 = new JLabel("Blockchain" + i);
            label1.setForeground(Color.WHITE);

            Font font = new Font("Arial", Font.BOLD, 14);
            label1.setFont(font);
            label1.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(label1);

            // Gắn sự kiện cho các dòng chữ
            label1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Xử lý sự kiện khi dòng chữ được click
                    System.out.println("Bạn đã click vào Blockchain.");
                }
            });
        }
    }
}
//Phần Panel tin của search UI
class ArticleButton extends JButton {
    public void articleButton() {
        setPreferredSize(new Dimension(465, 132));
        setBackground(Color.WHITE);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setBorderPainted(false);
                        
    }
}

