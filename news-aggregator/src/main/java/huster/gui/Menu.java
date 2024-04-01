package huster.gui;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Menu extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 0;
    public static final int ORIGIN_Y = 0;

    private JTextField searchBar;
    private JList<String> suggestionList;
    private DefaultListModel<String> listModel;

    public Menu() {
        Container contentPane = getContentPane();
        setSize(X, Y);
        setResizable(true);
        setLocation(ORIGIN_X, ORIGIN_Y);
        setTitle("UI_TIM_KIEM");
        contentPane.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        // Tạo JTextField để tìm kiếm và JList để hiển thị gợi ý
        searchBar = new JTextField(10);
        Font font = new Font("Arial", Font.PLAIN, 20);
        searchBar.setFont(font);
        searchBar.setPreferredSize(new Dimension(1440, 30));

        suggestionList = new JList<>();
        listModel = new DefaultListModel<>();
        suggestionList.setModel(listModel);

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
                String searchText = searchBar.getText().toLowerCase();
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

        // Thêm các nút vào menuLeft và menuRight
        // Các nút được đặt ở đây chỉ để minh họa, bạn có thể thay đổi hoặc bổ sung theo nhu cầu
        JButton menuButton = new JButton("Menu");
        JButton homeButton = new JButton("Home");
        JButton searchButton = new JButton("Search");
        JButton userButton = new JButton("User");

        menuLeft.add(menuButton);
        menuLeft.add(homeButton);
        menuRight.add(searchButton);
        menuRight.add(userButton);

        // Thêm các panel con vào menu
        menu.add(menuLeft, BorderLayout.WEST);
        menu.add(menuRight, BorderLayout.EAST);
        menu.add(menuCenter, BorderLayout.CENTER);
        menuAndSearchPanel.add(menu);
        menuAndSearchPanel.add(searchBar);

        // Thêm JList vào JPanel để hiển thị gợi ý tìm kiếm
        JPanel suggestionPanel = new JPanel(new BorderLayout());
        suggestionPanel.add(new JScrollPane(suggestionList), BorderLayout.CENTER);
        menuAndSearchPanel.add(suggestionPanel);

        contentPane.add(menuAndSearchPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    // Phương thức tìm kiếm gợi ý tìm kiếm dựa trên từ khóa nhập vào
    private String[] searchSuggestions(String searchText) {
        // Thực hiện tìm kiếm trong dữ liệu và trả về các gợi ý tìm kiếm phù hợp
        // Ở đây, chỉ là một ví dụ đơn giản
        return new String[]{"Bitcoin", "Ethereum", "Blockchain", "Cryptocurrency"};
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // Xử lý sự kiện khi các nút được nhấn (không cần thiết cho ví dụ này)
    }

    public static void main(String[] args) {
        new SearchUI();
    }
}
