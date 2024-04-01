package huster.gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class SearchUI extends JFrame implements ActionListener, ItemListener {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 0;
    public static final int ORIGIN_Y = 0;
    
    private JTextField searchBar = new JTextField(10);
    private JList<String> suggestionList;
    private DefaultListModel<String> listModel;

    public SearchUI(){
        Container contentPane = getContentPane();
        setSize(X, Y);
        setResizable(true);
        setLocation(ORIGIN_X, ORIGIN_Y);
        setTitle("UI_TIM_KIEM");
        contentPane.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //set up cho gợi í của sưarchBar ***CÓ THỂ PHỈA THÊM PHƯƠNG THỨC NHẢY DỮ LIỆU TỪ FILE JSON?***
        suggestionList = new JList<>();
        listModel = new DefaultListModel<>();
        suggestionList.setModel(listModel);
        
        //Thiết lập cho panel menu
        JPanel menu = new JPanel();
        System.setProperty("BLACK_menu", "0x222222");
        Color BLACK_menu = Color.getColor("BLACK_menu");
        menu.setLayout(new BorderLayout());
        menu.setSize(1440,101);
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
    
        
        Font font = new Font("Arial", Font.PLAIN, 30);
        searchBar.setFont(font);
        searchBar.setPreferredSize(new Dimension(1440, 60));
        
        
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
        ImageIcon menuIcon = new ImageIcon("news-aggregator\\resource\\assets\\menuIcon.png");
        JButton menuButton = new JButton(menuIcon);
        menuButton.setPreferredSize(new Dimension(65,65));
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(false);
        menuLeft.add(menuButton);

        ImageIcon homeIcon = new ImageIcon("news-aggregator\\resource\\assets\\homeIcon.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.setPreferredSize(new Dimension(65,65));
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        menuLeft.add(homeButton);

        // Thêm các nút vào menuRight
        ImageIcon searchIcon = new ImageIcon("news-aggregator\\resource\\assets\\searchIcon.png");
        JButton searchButton = new JButton(searchIcon);
        searchButton.setPreferredSize(new Dimension(65,65));
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        menuRight.add(searchButton);

        ImageIcon userIcon = new ImageIcon("news-aggregator\\resource\\assets\\userIcon.png");
        JButton userButton = new JButton(userIcon);
        userButton.setPreferredSize(new Dimension(65,65));
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
        JPanel suggestionPanel = new JPanel();
        suggestionPanel.setPreferredSize(new Dimension(1440,40));
        suggestionPanel.setLayout(new BoxLayout(suggestionPanel, BoxLayout.Y_AXIS));
        suggestionPanel.add(new JScrollPane(suggestionList), BorderLayout.CENTER);
        menuAndSearchPanel.add(suggestionPanel);

        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ẩn Panel hiện tại (menuAndSearchPanel)
                menuAndSearchPanel.setVisible(false);
                
                // Hiển thị Panel khác chứa kết quả tìm kiếm (ví dụ: searchResultPanel)
                SearchUIResult searchResultPanel = new  SearchUIResult();
                contentPane.add(searchResultPanel, BorderLayout.CENTER);
                searchResultPanel.setVisible(true);
                
                // Yêu cầu Frame cập nhật lại layout để hiển thị Panel mới
                revalidate();
            }
        });

        
        contentPane.add(menuAndSearchPanel, BorderLayout.NORTH);
        setVisible(true);
    }    

    // Phương thức tìm kiếm gợi ý tìm kiếm dựa trên từ khóa nhập vào
    private String[] searchSuggestions(String searchText) {
        // Thực hiện tìm kiếm trong dữ liệu và trả về các gợi ý tìm kiếm phù hợp
        // Ở đây, chỉ là một ví dụ đơn giản ( Nhảy trên chat GPT)
        return new String[]{"Bitcoin", "Ethereum", "Blockchain", "Cryptocurrency"};
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
}
