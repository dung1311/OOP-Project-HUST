package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;

public class SearchResults extends JFrame{
    private JTextField searchField;
    private JList<String> suggestionList;
    private DefaultListModel<String> suggestionListModel;
    private JScrollPane scrollPane;


    public SearchResults() {
        this.setTitle("Demo");
        this.setSize(360, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        Font font20 = new Font("Arial", Font.BOLD, 20);


        System.setProperty("greenForBackGround", "0xD4A792");
        Color greenForBackGround = Color.getColor("greenForBackGround");
        // System.setProperty("beigeColor", "0xf1f5db");
        // Color beigeColor = Color.getColor("beigeColor");



        // add menu button
        URL urlIconFilter = Home.class.getResource("/resource/button_filter.png");
        ImageIcon iconFilter = new ImageIcon(urlIconFilter);

        JButton jButton_filter = new JButton();
        jButton_filter.setBorderPainted(  false);
        jButton_filter.setFocusPainted(false);
        jButton_filter.setContentAreaFilled(false);
        jButton_filter.setBounds(14, 6, 30, 30);

        jButton_filter.setIcon(iconFilter);


        // add button_search
        URL urlIconSearch = Home.class.getResource("/resource/search_icon.png");
        ImageIcon iconSearch = new ImageIcon(urlIconSearch);

        JButton jButton_search = new JButton();
        jButton_search.setBorderPainted(  false);
        jButton_search.setFocusPainted(false);
        jButton_search.setContentAreaFilled(false);
        jButton_search.setBounds(272, 6, 30, 30);

        jButton_search.setIcon(iconSearch);


        // add button_home
        URL urlIconHome = Home.class.getResource("/resource/button_home.png");
        ImageIcon iconHome = new ImageIcon(urlIconHome);

        JButton jButton_home = new JButton();
        jButton_home.setPreferredSize( new Dimension(25, 25));
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



        // create scrollPane suggested news
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.WHITE);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));


        // add 1st suggestion
        JPanel jPanel_suggestion_1 = new JPanel();
        jPanel_suggestion_1.setPreferredSize(new Dimension(250, 125));
        jPanel_suggestion_1.setLayout(new BorderLayout());

        JButton jButton_suggestion_1 = new JButton("<html><div style='text-align: center;'>Hot: Quá vô đạo<br>bất lương</div></html>");
        jButton_suggestion_1.setFont(font20);
        jButton_suggestion_1.setBackground(Color.LIGHT_GRAY);
        jButton_suggestion_1.setBorderPainted(false);
        URL urlNgot = Home.class.getResource("/resource/ngotUpBo125.jpg");
        ImageIcon iconNgot = new ImageIcon(urlNgot);
        jButton_suggestion_1.setIcon(iconNgot);
        jButton_suggestion_1.setVerticalTextPosition(SwingConstants.CENTER);
        jButton_suggestion_1.setHorizontalTextPosition(SwingConstants.LEFT);

        jButton_suggestion_1.addActionListener(e -> {
            News news = new News();
            this.dispose();
            news.setTextJLabel_news_header(jButton_suggestion_1.getText());
            news.setIconJLabel_news_header(iconNgot);;
        });


        jPanel_suggestion_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel_suggestion_1.add(jButton_suggestion_1, BorderLayout.CENTER);
        jPanel.add(jPanel_suggestion_1);


        // add 2nd suggestion
        JPanel jPanel_suggestion_2 = new JPanel();
        jPanel_suggestion_2.setPreferredSize(new Dimension(250, 125));
        jPanel_suggestion_2.setLayout(new BoxLayout(jPanel_suggestion_2, BoxLayout.Y_AXIS));

        URL urlNgot2 = Home.class.getResource("/resource/ngotUpBo250_75.jpg");
        JButton jButton_suggestion_2 = new JButton("<html><div style='text-align: center;'><img src='" + urlNgot2 + "'><br><span style='display: block;'>Hot: Ngọt disband</span></div></html>");
        jButton_suggestion_2.setFont(font20);
        jButton_suggestion_2.setBackground(Color.PINK);
        jButton_suggestion_2.setBorderPainted(false);
        jButton_suggestion_2.setVerticalTextPosition(SwingConstants.BOTTOM);
        jButton_suggestion_2.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton_suggestion_2.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        jButton_suggestion_2.addActionListener(e -> {
            News news = new News();
            this.dispose();
            news.setTextJLabel_news_header(jButton_suggestion_2.getText());
        });

        jPanel_suggestion_2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel_suggestion_2.add(jButton_suggestion_2);
        jPanel.add(jPanel_suggestion_2);



        // add scrollPane suggested news
        JScrollPane scrollPane_suggestedNews = new JScrollPane(jPanel);
        scrollPane_suggestedNews.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane_suggestedNews.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_suggestedNews.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scrollPane_suggestedNews.setBounds(18, 50, 320, 555);



        // add into frame
        this.setBackground(greenForBackGround);
        this.setLayout(null);
        this.add(jButton_filter);
        this.add(jButton_search);
        this.add(searchField);
        this.add(scrollPane);
        scrollPane.setVisible(false);
        this.add(jButton_home);
        this.add(scrollPane_suggestedNews);
        // this.add(jButton_footer);

        
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

    public void setTextSearchField(String s){
        searchField.setText(s);
    }

    public static void main(String[] args) {
        new SearchResults();
    }
}
