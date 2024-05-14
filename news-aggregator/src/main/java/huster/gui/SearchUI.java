package huster.gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SearchUI extends JFrame{
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 0;
    public static final int ORIGIN_Y = 0;

    public String articalNameJSON;
    private int seeMoreButtonClickedCount = 0;

    private SearchAndSuggestionPanel searchPanel = new SearchAndSuggestionPanel();

    private JPanel searchResult_center;
    private ImageIcon articleIcon;

    private AbstractButton articleButton;
    private ArticlePanel panelTin;

    private String articleTitle;
    private String postingDate;


    public SearchUI() {
        ScreenHistory.getInstance();

        Font font40 = new Font("Arial", Font.PLAIN, 40);

        Container contentPane = getContentPane();
        setSize(X, Y);
        setResizable(false);
        setLocationRelativeTo(null);
        // setLocation(ORIGIN_X, ORIGIN_Y);
        setTitle("UI_TIM_KIEM");
        contentPane.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Header menuAndSearchPanel = new Header();
        menuAndSearchPanel.addButtonForSearchUI();
       
        menuAndSearchPanel.addCloseButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ScreenHistory.getInstance().isEmpty()) {
                    JFrame previousScreen = ScreenHistory.getInstance().popScreen();
                    previousScreen.setVisible(true);
                    dispose();
                }
            }
        });

        menuAndSearchPanel.addHomeButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new Menu().setVisible(true);;
                dispose();
            }
        });
        
        menuAndSearchPanel.add(searchPanel);

        // ------------------------------------------------
        // Đây là phần thêm kết quả tìm kiếm
        JPanel searchResult = new JPanel(new BorderLayout());
        searchResult.setPreferredSize(new Dimension(1440, 2000));
        searchResult_center = new JPanel();

        // Thêm button See more
        JButton seeMoreButton = new JButton("See more!");
        seeMoreButton.setFont(font40);
        seeMoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeMoreButtonClickedCount++;
                searchResult.setPreferredSize(new Dimension(1440, 2000 + 900 * seeMoreButtonClickedCount));
                // createSearchResultPanels(6 + 3 * seeMoreButtonClickedCount);
                revalidate();
            }
        });
        searchResult.add(searchResult_center, BorderLayout.CENTER);
        searchResult.add(seeMoreButton, BorderLayout.SOUTH);

        JScrollPane scrollResult = new JScrollPane(searchResult);
        scrollResult.setPreferredSize(new Dimension(1440, 2000));
        scrollResult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollResult.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        searchResult_center.setLayout(null);

        // This ActionListener is crucial!! Just know that it helps display searching result
        ActionListener searchListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete everything from contentPane
                contentPane.removeAll();
                searchPanel.setVisible(false);
                
                // Add stuff back
                createSearchResultPanels(6);
                articalNameJSON = searchPanel.getSearchBarText();
                contentPane.add(menuAndSearchPanel, BorderLayout.NORTH);
                contentPane.add(scrollResult, BorderLayout.CENTER);//Thay bằng class SearchResult
                revalidate();
                repaint();

                // This helps when you want to keep searching
                menuAndSearchPanel.addSearchButtonListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new SearchUI().setVisible(true);
                        dispose();
                    }
                });
            }
        };
        // JButton seeMoreButton = new JButton("See more!");
        // seeMoreButton.setFont(font40);
        // seeMoreButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         if(seeMoreButtonClickedCount == 2) {
        //             seeMoreButton.setVisible(false);
        //             return ;
        //         }
                
        //         seeMoreButtonClickedCount++;
        //         number_News += 6;
        //         fullarticlePanel.setPreferredSize(new Dimension(1280, 2500 + 1200 * seeMoreButtonClickedCount));
        //         articlePanel.setPreferredSize(new Dimension(1280, 1500 + 1200 * seeMoreButtonClickedCount));
        //         articlePanel.setLayout(new GridLayout(6 + 3 * seeMoreButtonClickedCount,2,175,0));
        //         // createSmall_articlePanel(3);
        //         createNews();
        //         revalidate();
        //     }
        // });        
        

        // Thêm actionListener cho searchButton và searchBar khi chưa hiển thị kết quả tìm kiếm
        searchPanel.addSearchBarActionListenner(searchListener);
        menuAndSearchPanel.addSearchButtonListener(searchListener);

        contentPane.add(menuAndSearchPanel, BorderLayout.NORTH);
        ListOfCate catePanel = new ListOfCate();
        contentPane.add(catePanel, BorderLayout.CENTER);
    }

    public void createSearchResultPanels(int numberOfRows) {
        for (int i = 0; i < 2; i++) {
            for (int j = numberOfRows - 6; j < numberOfRows; j++) {
                articleIcon = new ImageIcon("news-aggregator\\resource\\assets\\articleIcon.png");
                articleButton = new ArticleButton();
                articleButton.setIcon(articleIcon);
                panelTin = new ArticlePanel(getTitle(), getPostingDate());
                panelTin.setBounds(100 + 715 * i, 72 + 300 * j, 465, 170);
                panelTin.add(articleButton, BorderLayout.NORTH);
                articleButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        News news = new News();
                        news.setVisible(true);
                        dispose();
                        news.setHeader(articleButton.getText());
                        revalidate();
                    }
                    
                });
                searchResult_center.add(panelTin);
            }
        }
    }

    public String getarticalNameJSON(){
        return articalNameJSON;
    }

    public String getTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String s) {
        this.articleTitle = s;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String s) {
        this.postingDate = s;
    }

    public ImageIcon getArticleIcon() {
        return articleIcon;
    }

    public void setArticleIcon(ImageIcon articleIcon) {
        this.articleIcon = articleIcon;
    }
}

class ArticlePanel extends JPanel {
    public ArticlePanel(String a, String b) {
        setPreferredSize(new Dimension(465, 170));
        setLayout(new BorderLayout());
        String content = "<html><body>" + a + "<br>" + b + "</body></html>";
        JLabel articleName = new JLabel(content);
        add(articleName, BorderLayout.SOUTH);
        
    }
}

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

//This contains the categories about BlockChain topics
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

class ArticleButton extends JButton {
    public void articleButton() {
        setPreferredSize(new Dimension(465, 132));
        setBackground(Color.WHITE);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setBorderPainted(false);
                        
    }
}

// This class basically creates the searchbar with some suggestion for users
class SearchAndSuggestionPanel extends JPanel{
    private SearchBar searchBar = new SearchBar(10);
    private boolean isSuggestionPanelVisible = false;
    private JList<String> suggestionList;
    private DefaultListModel<String> listModel;
    public String selectedSuggestion;

    public SearchAndSuggestionPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel suggestionPanel = new JPanel();
        suggestionList = new JList<>();
        listModel = new DefaultListModel<>();
        suggestionList.setModel(listModel);

        suggestionPanel.setPreferredSize(new Dimension(1440, 40));
        suggestionPanel.setLayout(new BoxLayout(suggestionPanel, BoxLayout.Y_AXIS));
        suggestionPanel.add(new JScrollPane(suggestionList), BorderLayout.CENTER);
        suggestionPanel.setVisible(isSuggestionPanelVisible);
        
        // Sử dụng DocumentListener để lắng nghe sự kiện nhập liệu vào JTextField
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
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

            private void updateSuggestions() {
                String searchText = searchBar.getText();
                // Thực hiện tìm kiếm trong dữ liệu và cập nhật danh sách gợi ý tìm kiếm
                listModel.clear();
                if (!searchText.isEmpty()) {
                    String[] suggestions = searchSuggestions(searchText);
                    for (String suggestion : suggestions) {
                        listModel.addElement(suggestion);
                    }
                }
            }
        });

        suggestionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Kiểm tra xem sự kiện có phải là sự kiện kết thúc việc chọn không
                if (!e.getValueIsAdjusting()) {
                    // Lấy dòng được chọn từ suggestionList
                    String selectedSuggestion = suggestionList.getSelectedValue();
                    // Đặt nội dung của dòng được chọn vào searchBar
                    searchBar.setText(selectedSuggestion);
                    // Ẩn suggestionPanel sau khi chọn
                    suggestionPanel.setVisible(false);
                    // Đặt lại trạng thái của biến isSuggestionPanelVisible
                    isSuggestionPanelVisible = false;
                    searchBar.requestFocusInWindow();
                }
            }
        });

        searchBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Kiểm tra nếu suggestionPanel đang hiển thị thì ẩn nó đi, ngược lại thì hiển
                // thị
                if (isSuggestionPanelVisible) {
                    suggestionPanel.setVisible(false);
                    isSuggestionPanelVisible = false;
                } else {
                    suggestionPanel.setVisible(true);
                    isSuggestionPanelVisible = true;
                }
            }
        });

        // suggestionList.addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         if (e.getClickCount() == 1) {
        //             // Lấy từ được chọn
        //             selectedSuggestion = suggestionList.getSelectedValue();
        //             if (selectedSuggestion != null) {
        //                 // Tạo một instance mới của News
        //                 News news = new News();
        //                 // Thiết lập tiêu đề cho News
        //                 news.setHeader(selectedSuggestion);
        //                 // Hiển thị News
        //                 news.setVisible(true);
        //                 // Đóng cửa sổ hiện tại
        //                 SwingUtilities.getWindowAncestor(SearchAndSuggestionPanel.this).dispose();
        //             }
        //         }
        //     }
        // });

        add(searchBar);
        add(suggestionPanel);
    }
    
    //Dungx cho các tên bài báo vào đây ở dạng String
    private String[] searchSuggestions(String searchText) {
        // Dữ liệu gợi ý
        String[] suggestions = { "Bitcoin", "Ethereum", "Blockchain", "Cryptocurrency" };
        // Danh sách chứa các gợi ý phù hợp
        ArrayList<String> relatedSuggestions = new ArrayList<>();

        // Lặp qua từng gợi ý
        for (String suggestion : suggestions) {
            // Nếu từ khóa được nhập vào là một phần của gợi ý
            if (suggestion.toLowerCase().startsWith(searchText.toLowerCase())) {
                // Thêm gợi ý này vào danh sách các gợi ý phù hợp
                relatedSuggestions.add(suggestion);
            }
        }

        // Chuyển danh sách các gợi ý phù hợp thành một mảng và trả về
        return relatedSuggestions.toArray(new String[0]);
    }

    public void addSearchBarActionListenner(ActionListener e){
        searchBar.addActionListener(e);
    }

    public String getSearchBarText(){
        return searchBar.getText();
    }

    public void setListMouseListener(MouseAdapter a){
        suggestionList.addMouseListener(a);
    }
}

//This class will display the result after search, can be used in menu aswell with consideration, contact me for more infomation

class SearchResult extends JScrollPane{
    private JPanel searchResult = new JPanel(new BorderLayout());
    private JPanel searchResult_Center;
    private JButton seeMoreButton = new JButton("See more!");
    public SearchResult(){
        setPreferredSize(new Dimension(1440, 2000));
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        
        Font font40 = new Font("Arial", Font.PLAIN, 40);
        
        seeMoreButton.setFont(font40);

        searchResult.setPreferredSize(new Dimension(1280, 1500));
        searchResult.setLayout(new BorderLayout());
        searchResult_Center = new JPanel();
        searchResult_Center.setLayout(new GridLayout(6,2,175,0));
        searchResult_Center.setPreferredSize(new Dimension(1280, 1500));

        searchResult.add(searchResult_Center, BorderLayout.NORTH);
        searchResult.add(seeMoreButton, BorderLayout.SOUTH);

        add(searchResult);
    }

    public void seeMoreActionListeners(ActionListener e){
        seeMoreButton.addActionListener(e);
    }

    public void addArticle(JPanel p){
        searchResult_Center.add(p);
    }

    public void setLayoutAndSize(int n){
        searchResult.setPreferredSize(new Dimension(1280, 1500 + 1200 * n));
        searchResult_Center.setLayout(new GridLayout(6 + 3 * n,2,175,0));
        searchResult_Center.setPreferredSize(new Dimension(1280, 1500 + 1200 * n));
    }
}
