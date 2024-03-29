package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;

public class News extends JFrame{
    private JTextField searchField;
    private JList<String> suggestionList;
    private DefaultListModel<String> suggestionListModel;
    private JScrollPane scrollPane;
    private JLabel jLabel_news_header;


    public News() {
        this.setTitle("Demo");
        this.setSize(360, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        Font font20 = new Font("Arial", Font.BOLD, 20);
        Font font15 = new Font("Arial", Font.BOLD, 15);


        System.setProperty("greenForBackGround", "0xD4A792");
        Color greenForBackGround = Color.getColor("greenForBackGround");
        // System.setProperty("beigeColor", "0xf1f5db");
        // Color beigeColor = Color.getColor("beigeColor");



        // add back button
        URL urlIconBack = Home.class.getResource("/resource/button_back.png");
        ImageIcon iconBack = new ImageIcon(urlIconBack);

        JButton jButton_back = new JButton();
        jButton_back.setBorderPainted(  false);
        jButton_back.setFocusPainted(false);
        jButton_back.setContentAreaFilled(false);
        jButton_back.setBounds(14, 6, 30, 30);

        jButton_back.setIcon(iconBack);


        // add button_search
        URL urlIconSearch = Home.class.getResource("/resource/search_icon.png");
        ImageIcon iconSearch = new ImageIcon(urlIconSearch);

        JButton jButton_search = new JButton();
        jButton_search.setBorderPainted(  false);
        jButton_search.setFocusPainted(false);
        jButton_search.setContentAreaFilled(false);
        jButton_search.setBounds(272, 6, 30, 30);

        jButton_search.setIcon(iconSearch);


        // add button_account
        URL urlIconHome = Home.class.getResource("/resource/button_home.png");
        ImageIcon iconHome = new ImageIcon(urlIconHome);

        JButton jButton_home = new JButton();
        jButton_home.setBorderPainted(false);
        jButton_home.setFocusPainted(false);
        jButton_home.setContentAreaFilled(false);
        jButton_home.setBounds(307, 6, 30, 30);

        jButton_home.setIcon(iconHome);

        jButton_home.addActionListener(e -> {
            Home home = new Home();
            this.dispose();
        });


        // add searchField + searchSuggestList 
        searchField = new JTextField();
        searchField.setBounds(55, 6, 220, 30);
        searchField.setFont(font20);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });

        // Tạo danh sách gợi ý
        suggestionListModel = new DefaultListModel<>();
        suggestionList = new JList<>(suggestionListModel);

        // Xử lý sự kiện khi một gợi ý được chọn
        suggestionList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedSuggestion = suggestionList.getSelectedValue();
                if (selectedSuggestion != null) {
                    searchField.setText(selectedSuggestion);
                }
            }
        });

        scrollPane = new JScrollPane(suggestionList);
        scrollPane.setBounds(55, 36, 220, 45);



        // add scrollPane news
        JPanel jPanel_news = new JPanel();
        jPanel_news.setLayout(new BorderLayout());

        jLabel_news_header = new JLabel("header", JLabel.CENTER);
        jLabel_news_header.setFont(font20);

        String text = "Như đã thông báo lúc nào đó, team đã tạm hoãn nấu content láo nửa buổi để có thêm thời gian xem xét và xác định các dữ liệu bất thường phát sinh ở các bài post đã đăng. Từ đó, chúng tôi sẽ đối chiếu với những quy định nội bộ đã ban hành để đưa ra các quyết định nhằm đảm bảo tính minh bạch.\n"
                    + "Dựa trên những kết quả kiểm tra ban đầu, Ngũ đại quyết định công khai tình trạng hiện tại của các thành viên sau đây:\n"
                    + "\n"
                    + "Lều báo: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "Diêm Thống Nhất: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "\n"
                    + "Sau thời gian tạm dừng, chỉ đạo, những thực thể này sẽ vẫn tiếp tục tham gia bình thường và còn lót tích hơn nữa so với trước đây.\n"
                    + "Trong quá trình thực hiện việc kiểm tra tuân thủ, chúng tôi sẽ cho phép các thành viên của Ngũ đại (những cá nhân không kể trên thì không được được phép) bổ sung thông tin hoặc phản hồi quyết định của BTC.\n"
                    + "Những biện pháp này được thực hiện với sự nghiêm túc nhất để bảo đảm tính lót tích lành mạnh.\n"
                    + "Chúng tôi sẽ sớm cập nhật thêm thông tin liên quan đến quá trình bắt đầu lót tích trở lại. Hãy tiếp tục theo dõi các kênh chính thức của Ngũ đại để nhận thông tin mới nhất. Xin chân thành cảm ơn sự đồng hành và ủng hộ của các fan diêm dành cho chúng tôi!\n";
        
        JTextArea jTextArea_news_center = new JTextArea(text);
        // Đặt khoảng cách lề cho JTextArea
        jTextArea_news_center.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.BLACK), // Lề ngoài
            new EmptyBorder(10, 0, 0, 0) // Lề bên trong
        ));
        jTextArea_news_center.setFont(font15);
        jTextArea_news_center.setLineWrap(true);
        jTextArea_news_center.setWrapStyleWord(true);
        jTextArea_news_center.setEditable(false);


        jPanel_news.add(jLabel_news_header, BorderLayout.NORTH);
        jPanel_news.add(jTextArea_news_center, BorderLayout.CENTER);



        JScrollPane scrollPane_News = new JScrollPane(jPanel_news);
        scrollPane_News.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane_News.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_News.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scrollPane_News.setBounds(18, 50, 320, 555);



        // add into frame
        this.setBackground(greenForBackGround);
        this.setLayout(null);
        this.add(jButton_back);
        this.add(jButton_search);
        this.add(searchField);
        this.add(scrollPane);
        scrollPane.setVisible(false);
        this.add(jButton_home);
        this.add(scrollPane_News);

        
        this.setVisible(true);
    }


    private void updateSuggestions() {
        String searchText = searchField.getText();
        List<String> suggestions = getSuggestions(searchText);
        suggestionListModel.clear();
    
        for (String suggestion : suggestions) {
            suggestionListModel.addElement(suggestion);
        }
    
        boolean hasMatchingSuggestions = !suggestions.isEmpty() && !searchText.isEmpty();
        scrollPane.setVisible(hasMatchingSuggestions);
    }


    private List<String> getSuggestions(String searchText) {
        // Đây chỉ là ví dụ đơn giản
        List<String> suggestions = new ArrayList<>();
        suggestions.add("Apple");
        suggestions.add("Banana");
        suggestions.add("Orange");
        suggestions.add("Grapes");
        suggestions.add("Pineapple");

        List<String> filteredSuggestions = new ArrayList<>();
        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().startsWith(searchText.toLowerCase())) {
                filteredSuggestions.add(suggestion);
            }
        }

        return filteredSuggestions;
    }

    public void setTextJLabel_news_header (String s) {
        jLabel_news_header.setVerticalTextPosition(SwingConstants.CENTER);
        jLabel_news_header.setHorizontalTextPosition(SwingConstants.LEFT);
        jLabel_news_header.setText(s);
    }

    public void setIconJLabel_news_header (ImageIcon icon) {
        jLabel_news_header.setIcon(icon);
    }

    public static void main(String[] args) {
        new News();
    }
}
