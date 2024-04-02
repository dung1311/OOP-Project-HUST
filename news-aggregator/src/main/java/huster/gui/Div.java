package huster.gui;

import javax.swing.*;
import java.awt.*;

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