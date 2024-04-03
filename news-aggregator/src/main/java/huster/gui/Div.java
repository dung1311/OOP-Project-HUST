package huster.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Div {

}

class CreateSearchPanel extends JPanel{
    public CreateSearchPanel(){
        setPreferredSize(new Dimension(1440, 40));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }
}
//Một phần của hiển thị kết quả tìm kiếm
class ArticlePanel extends JPanel {
    public ArticlePanel(ImageIcon articleIcon) {
        setPreferredSize(new Dimension(465, 132));
        setLayout(new BorderLayout());
        
        JLabel label = new JLabel(articleIcon);
        add(label, BorderLayout.CENTER);
    }
}
//Cài đặt cấu tạo cho searchBar
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
class ListOfCate extends JPanel{
    public ListOfCate(){
        System.setProperty("BLACK_menu", "0x222222");
        Color BLACK_menu = Color.getColor("BLACK_menu");
        setPreferredSize(new Dimension(1440, 620));
        setLayout(new FlowLayout());
        setBackground(BLACK_menu);

        for(int i = 0; i < 10; i++){
            JLabel label1 = new JLabel("Blockchain" + i);
            label1.setForeground(Color.WHITE);
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
