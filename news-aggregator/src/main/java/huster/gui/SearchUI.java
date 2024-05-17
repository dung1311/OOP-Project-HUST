package huster.gui;

import javax.swing.*;
import javax.swing.event.*;

import com.google.gson.JsonObject;

import huster.action.GetData;
import huster.action.SearchData;
import huster.action.newsObject;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SearchUI extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 0;
    public static final int ORIGIN_Y = 0;
    // TODO
    public String articalNameJSON = "bitcoin";
    private int seeMoreButtonClickedCount = 0;

    private SearchAndSuggestionPanel searchPanel = new SearchAndSuggestionPanel();

    private JPanel searchResult_center;
    private ImageIcon articleIcon;

    private String articleTitle;
    private String postingDate;


    public SearchUI() {
        ScreenHistory.getInstance();

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
       
        menuAndSearchPanel.addBackButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ScreenHistory.getInstance().isEmpty()) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menuAndSearchPanel);
                    JFrame previousScreen = ScreenHistory.getInstance().popScreen();
                    previousScreen.setVisible(true);
                    ScreenHistory.getInstance().pushScreen(frame);
                    frame.dispose();
                }
            }
        });

        menuAndSearchPanel.addHomeButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menuAndSearchPanel);
                Menu previousScreen = MenuHistory.getInstance().peekScreen();
                previousScreen.setVisible(true);
                previousScreen.addBackButton();
                ScreenHistory.getInstance().pushScreen(frame);
                frame.dispose();
            }
        });

        menuAndSearchPanel.add(searchPanel);

        // ------------------------------------------------
        // Đây là phần thêm kết quả tìm kiếm
        // JPanel searchResult = new JPanel(new BorderLayout());
        // searchResult.setPreferredSize(new Dimension(1440, 2000));
        // searchResult_center = new JPanel();

        // // Thêm button See more
        // JButton seeMoreButton = new JButton("See more!");
        // seeMoreButton.setFont(font40);
        // seeMoreButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         seeMoreButtonClickedCount++;
        //         searchResult.setPreferredSize(new Dimension(1440, 2000 + 900 * seeMoreButtonClickedCount));
        //         // createSearchResultPanels(6 + 3 * seeMoreButtonClickedCount);
        //         revalidate();
        //     }
        // });
        // searchResult.add(searchResult_center, BorderLayout.CENTER);
        // searchResult.add(seeMoreButton, BorderLayout.SOUTH);

        // JScrollPane scrollResult = new JScrollPane(searchResult);
        // scrollResult.setPreferredSize(new Dimension(1440, 2000));
        // scrollResult.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // scrollResult.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        // searchResult_center.setLayout(null);

        // // This ActionListener is crucial!! Just know that it helps display searching
        // // result

        // TODO
        SearchResult hihi = new SearchResult(); 
        
        ListOfCate catePanel = new ListOfCate();

        // tìm kiếm
        searchPanel.addSearchBarActionListenner(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(catePanel);
                searchPanel.hiddenSuggestionPanel();
                
                articalNameJSON = searchPanel.getSearchBarText();
                contentPane.add(hihi, BorderLayout.CENTER);// Thay bằng class SearchResult
                revalidate();
                repaint();
            }    
        });

        contentPane.add(menuAndSearchPanel, BorderLayout.NORTH);
        contentPane.add(catePanel, BorderLayout.CENTER);
        
        // TODO
        List<JPanel> listJPanels = new SearchData().search("bitcoin");
        for(int i = 0; i < 10; i++) {
            hihi.addArticle(listJPanels.get(i));
        }
    }


    public String getarticalNameJSON() {
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

// This contains the categories about BlockChain topics
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
class SearchAndSuggestionPanel extends JPanel {
    private SearchBar searchBar = new SearchBar(10);
    private boolean isSuggestionPanelVisible = false;
    private JList<String> suggestionList;
    private DefaultListModel<String> listModel;
    public String selectedSuggestion;
    private JPanel suggestionPanel;

    public SearchAndSuggestionPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        suggestionPanel = new JPanel();
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

        add(searchBar);
        add(suggestionPanel);
    }

    // Dungx cho các tên bài báo vào đây ở dạng String
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

    public void addSearchBarActionListenner(ActionListener e) {
        searchBar.addActionListener(e);
    }

    public String getSearchBarText() {
        return searchBar.getText();
    }

    public void setListMouseListener(MouseAdapter a) {
        suggestionList.addMouseListener(a);
    }

    public void hiddenSuggestionPanel() {
        suggestionPanel.setVisible(false);
    }
}

// This class will display the result after search, can be used in menu aswell
// with consideration, contact me for more infomation

class SearchResult extends JScrollPane {
    private JPanel searchResult = new JPanel(new BorderLayout());
    private JPanel searchResult_Center;
    private JButton seeMoreButton = new JButton("See more!");

    public SearchResult() {
        setPreferredSize(new Dimension(1440, 2000));
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        setVisible(true);

        Font font30 = new Font("Arial", Font.PLAIN, 30);

        seeMoreButton.setFont(font30);

        searchResult.setPreferredSize(new Dimension(1280, 1500));
        searchResult.setLayout(new BorderLayout());
        searchResult_Center = new JPanel();
        searchResult_Center.setLayout(new GridLayout(6, 2, 175, 0));
        searchResult_Center.setPreferredSize(new Dimension(1280, 1500));

        searchResult.add(searchResult_Center, BorderLayout.NORTH);
        searchResult.add(seeMoreButton, BorderLayout.SOUTH);

        // add(searchResult);
        setViewportView(searchResult);
    }

    public void seeMoreActionListeners(ActionListener e) {
        seeMoreButton.addActionListener(e);
    }

    public void addArticle(JPanel p) {
        searchResult_Center.add(p);
    }

    public void setLayoutAndSize(int n) {
        searchResult.setPreferredSize(new Dimension(1280, 1500 + 1200 * n));
        searchResult_Center.setLayout(new GridLayout(6 + 3 * n, 2, 175, 0));
        searchResult_Center.setPreferredSize(new Dimension(1280, 1500 + 1200 * n));
    }
}


// class Test {
//     public static void main(String[] args) {
//         SearchUI test = new SearchUI();
//         test.setVisible(true);
//     }
// }