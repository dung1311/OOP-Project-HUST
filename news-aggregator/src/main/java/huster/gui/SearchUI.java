package huster.gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SearchUI extends JFrame implements ActionListener, ItemListener {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 0;
    public static final int ORIGIN_Y = 0;

    public String tenDeTimTrongJSON;

    private boolean isSuggestionPanelVisible = false;
    private int seeMoreButtonClickedCount = 0;

    private SearchBar searchBar = new SearchBar(10);
    private JList<String> suggestionList;
    private DefaultListModel<String> listModel;

    private JPanel searchResult_center;
    private ImageIcon articleIcon;

    private AbstractButton articleButton;
    private ArticlePanel panelTin;

    private String titlePanelTin;
    private String postingDatePanelTin;

    private ScreenHistory historyStack;


    public SearchUI() {
        historyStack = ScreenHistory.getInstance();
        // ScreenHistory.getInstance().pushScreen(this);
        Font font40 = new Font("Arial", Font.PLAIN, 40);

        Container contentPane = getContentPane();
        setSize(X, Y);
        setResizable(false);
        setLocationRelativeTo(null);
        // setLocation(ORIGIN_X, ORIGIN_Y);
        setTitle("UI_TIM_KIEM");
        contentPane.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // set up cho gợi í của sưarchBar ***CÓ THỂ PHỈA THÊM PHƯƠNG THỨC NHẢY DỮ LIỆU
        // TỪ FILE JSON?***
        JPanel suggestionPanel = new JPanel();
        suggestionList = new JList<>();
        listModel = new DefaultListModel<>();
        suggestionList.setModel(listModel);

        // Thiết lập cho panel menu
        JPanel menu = new JPanel();
        System.setProperty("BLACK_menu", "0x222222");
        Color BLACK_menu = Color.getColor("BLACK_menu");
        menu.setLayout(new BorderLayout());
        menu.setSize(1440, 101);
        menu.setBackground(BLACK_menu);

        JPanel menuLeft = new JPanel();
        menuLeft.setBackground(BLACK_menu);
        menuLeft.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel menuRight = new JPanel();
        menuRight.setBackground(BLACK_menu);
        menuRight.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel menuCenter = new JPanel();
        menuCenter.setBackground(BLACK_menu);
        menuCenter.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel menuAndSearchPanel = new JPanel();
        menuAndSearchPanel.setLayout(new BoxLayout(menuAndSearchPanel, BoxLayout.Y_AXIS));

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

        // Thêm các nút vào menuLeft
        ImageIcon closeIcon = new ImageIcon("news-aggregator\\resource\\assets\\closeIcon.png");
        JButton closeButton = new JButton(closeIcon);
        closeButton.setPreferredSize(new Dimension(50, 50));
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ScreenHistory.getInstance().isEmpty()) {
                    JFrame previousScreen = ScreenHistory.getInstance().popScreen();
                    previousScreen.setVisible(true);
                    dispose();
                }
            }
        });

        menuLeft.add(closeButton);

        ImageIcon homeIcon = new ImageIcon("news-aggregator\\resource\\assets\\homeIcon.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.setPreferredSize(new Dimension(50, 50));
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new Menu().setVisible(true);;
                dispose();
            }
        });
        menuLeft.add(homeButton);

        // Thêm các nút vào menuRight
        ImageIcon searchIcon = new ImageIcon("news-aggregator\\resource\\assets\\searchIcon.png");
        JButton searchButton = new JButton(searchIcon);
        searchButton.setPreferredSize(new Dimension(50, 50));
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        menuRight.add(searchButton);

        ImageIcon userIcon = new ImageIcon("news-aggregator\\resource\\assets\\userIcon.png");
        JButton userButton = new JButton(userIcon);
        userButton.setPreferredSize(new Dimension(50, 50));
        userButton.setBorderPainted(false);
        userButton.setFocusPainted(false);
        userButton.setContentAreaFilled(false);
        menuRight.add(userButton);

        // Thêm các panel con vào menu
        menu.add(menuLeft, BorderLayout.WEST);
        menu.add(menuRight, BorderLayout.EAST);
        menu.add(menuCenter, BorderLayout.CENTER);
        menuAndSearchPanel.add(menu);
        menuAndSearchPanel.add(searchBar);

        // Thêm JList vào JPanel để hiển thị gợi ý tìm kiếm()
        suggestionPanel.setPreferredSize(new Dimension(1440, 40));
        suggestionPanel.setLayout(new BoxLayout(suggestionPanel, BoxLayout.Y_AXIS));
        suggestionPanel.add(new JScrollPane(suggestionList), BorderLayout.CENTER);
        menuAndSearchPanel.add(suggestionPanel);
        suggestionPanel.setVisible(false);

        // Thêm ListSelectionListener cho suggestionList
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
                }
            }
        });
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
                createSearchResultPanels(6 + 3 * seeMoreButtonClickedCount);
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

        // Phần hiển thị gợi í cho searchBar
        ActionListener searchListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa hết mấy cái Panel hiện tại
                menuAndSearchPanel.setVisible(false);
                contentPane.removeAll();
                
                // Có thể phải code đưa thông tin vào JPanel, chưa thấy cách nào hay
                createSearchResultPanels(6);
                
                tenDeTimTrongJSON = searchBar.getText();
                contentPane.add(menu, BorderLayout.NORTH);
                contentPane.add(scrollResult, BorderLayout.CENTER);
                revalidate();
                repaint();

                // Thêm actionListener cho searchButton sau khi hiển thị kết quả tìm kiếm
                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new SearchUI().setVisible(true);
                        dispose();
                    }
                });
            }
        };        
        searchBar.addActionListener(searchListener);

        // Thêm actionListener cho searchButton khi chưa hiển thị kết quả tìm kiếm
        searchButton.addActionListener(searchListener);

        // Trong constructor của SearchUI, thêm sự kiện cho searchBar
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


        contentPane.add(menuAndSearchPanel, BorderLayout.NORTH);
        ListOfCate catePanel = new ListOfCate();
        contentPane.add(catePanel, BorderLayout.CENTER);
    }

    // Phương thức tìm kiếm gợi ý tìm kiếm dựa trên từ khóa nhập vào
    // Muốn hiển thị gợi í cho người dùng thì Dũng nhét vào suggestion
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

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public void setScreenHistory(JFrame frame) {
        // screenHistory.pushFrame(frame);
    }

    public String getTenDeTimTrongJSON(){
        return tenDeTimTrongJSON;
    }

    public String getTitlePanelTin() {
        return titlePanelTin;
    }

    public void setTitlePanelTin(String s) {
        this.titlePanelTin = s;
    }

    public String getPostingDatePanelTin() {
        return postingDatePanelTin;
    }

    public void setPostingDatePanelTin(String s) {
        this.postingDatePanelTin = s;
    }

    public ImageIcon getArticleIcon() {
        return articleIcon;
    }

    public void setArticleIcon(ImageIcon articleIcon) {
        this.articleIcon = articleIcon;
    }

    public void createSearchResultPanels(int numberOfRows) {
        for (int i = 0; i < 2; i++) {
            for (int j = numberOfRows - 6; j < numberOfRows; j++) {
                articleIcon = new ImageIcon("news-aggregator\\resource\\assets\\articleIcon.png");
                articleButton = new ArticleButton();
                articleButton.setIcon(articleIcon);
                
                

                panelTin = new ArticlePanel(getTitlePanelTin(), getPostingDatePanelTin());
                panelTin.setBounds(100 + 715 * i, 72 + 300 * j, 465, 170);
                panelTin.add(articleButton, BorderLayout.NORTH);
                articleButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        News news = new News();
                        news.setVisible(true);
                        dispose();
                        news.setHeader(articleButton.getText());
                    }
                    
                });
                searchResult_center.add(panelTin);
            }
        }
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

