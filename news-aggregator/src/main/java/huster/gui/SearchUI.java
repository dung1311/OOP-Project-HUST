package huster.gui;

import javax.swing.*;
import javax.swing.event.*;

import huster.crawl.crawlTweet.ServerClient;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SearchUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private SearchAndSuggestionPanel searchPanel = new SearchAndSuggestionPanel();
    private JButton randomSearchButton = new JButton();

    public SearchUI() {
        Container contentPane = getContentPane();
        setSize(1440, 1024);
        setResizable(false);
        setLocationRelativeTo(null);
        // setLocation(ORIGIN_X, ORIGIN_Y);
        setTitle("SearchPanel");
        contentPane.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ServerClient.shutDownServer();
                System.out.println("Close Search window");
                dispose();
            }
        });

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
                    // frame.dispose();
                    SearchUI.this.setVisible(false);
                }
            }
        });

        menuAndSearchPanel.addHomeButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menuAndSearchPanel);
                Menu previousScreen = MenuHistory.getInstance().peekScreen();
                previousScreen.setVisible(true);
                previousScreen.addBackButton();
                ScreenHistory.getInstance().pushScreen(frame);
                // frame.dispose();
                SearchUI.this.setVisible(false);
            }
        });

        menuAndSearchPanel.add(searchPanel);

        // Click searhButton thì hiển thị SearchResultUI và set giá trị của thuộc tính
        // acticalNameJSON
        ActionListener searchListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(searchPanel);

                // Display "please wait" message
                JLabel pleaseWaitLabel = new JLabel("Please wait...");
                pleaseWaitLabel.setHorizontalAlignment(JLabel.CENTER);
                pleaseWaitLabel.setVerticalAlignment(JLabel.CENTER);
                pleaseWaitLabel.setFont(new Font("Arial", Font.BOLD, 24));
                contentPane.add(pleaseWaitLabel, BorderLayout.CENTER);
                contentPane.revalidate();
                contentPane.repaint();

                // Use SwingWorker to create and show the SearchResultUI
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        ScreenHistory.getInstance().pushScreen(frame);
                        return null;
                    }

                    @Override
                    protected void done() {
                        // Remove the "please wait" message
                        contentPane.remove(pleaseWaitLabel);
                        contentPane.revalidate();
                        contentPane.repaint();

                        SearchResultUI.createNews(searchPanel.getSearchBarText(), "news-aggregator\\resource\\data\\totalData.json");
                        if (SearchResultUI.listPanelIsNull()) {
                            JOptionPane.showMessageDialog(null, "We don't have that article!!!");
                        } else {
                            SearchResultUI searchResult = new SearchResultUI();
                            searchResult.addNews(12);
                            searchResult.revalidate();
                            searchResult.setVisible(true);
                            ScreenHistory.getInstance().pushScreen(frame);
                            // dispose();
                            SearchUI.this.setVisible(false);
                        }

                    }
                };
                worker.execute();

            }
        };

        // tìm kiếm
        searchPanel.addSearchBarActionListenner(searchListener);
        randomSearchButton.addActionListener(searchListener);

        contentPane.add(menuAndSearchPanel, BorderLayout.NORTH);
        // contentPane.add(randomSearchButton, BorderLayout.CENTER);
    }
}

class SearchBar extends JTextField {
    public SearchBar(int columns) {
        super(columns);
        setupSearchBar();
    }

    private void setupSearchBar() {
        // This code customize the search bar
        Font font = new Font("Arial", Font.PLAIN, 30);
        setFont(font);
        setSize(1440, 60);
        setPreferredSize(new Dimension(1440, 60));
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
                    suggestionPanel.setVisible(true);
                } else {
                    suggestionPanel.setVisible(false);
                }
            }
        });

        suggestionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedSuggestion = suggestionList.getSelectedValue();
                    if (selectedSuggestion != null) {
                        searchBar.setText(selectedSuggestion);
                        searchBar.requestFocusInWindow();
                        suggestionPanel.setVisible(false);
                    }
                }
            }
        });

        add(searchBar);
        add(suggestionPanel);
    }

    // Dungx cho các tên bài báo vào đây ở dạng String
    private String[] searchSuggestions(String searchText) {
        // Dữ liệu gợi ý
        String[] suggestions = { "Bitcoin", "Ethereum", "Blockchain", "Cryptocurrency", "Crypto", "DAO", "API",
                "CoinDesk", "ETFS", "Dogecoin", "meme", "", "", "", "", "", "", "", };
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
