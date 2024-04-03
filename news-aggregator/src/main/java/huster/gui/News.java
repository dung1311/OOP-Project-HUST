package huster.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import java.awt.*;
import java.awt.event.*;

public class News extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final int X = 1440;
    public static final int Y = 1024;
    public static final int ORIGIN_X = 100;
    public static final int ORIGIN_Y = 100;
    JPanel menu = new JPanel();
    private JLabel jLabel_news_header;
    // private JTextArea jTextArea_news_header;


    public News() {
        // Container contentPane = getContentPane(); // Sử dụng getContentPane() để lấy contentPane của JFrame

        setSize(X, Y);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("The MENU");
        // contentPane.setLayout(new BorderLayout());

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        System.setProperty("BLACK_menu", "0x222222");
        Color BLACK_menu = Color.getColor("BLACK_menu");
        
        menu.setLayout(new FlowLayout(15));
        menu.setSize(1440,101);
        menu.setBackground(BLACK_menu);


        Font font30B = new Font("Arial", Font.BOLD, 30);
        Font font20 = new Font("Arial", Font.PLAIN, 20);
        Font font15I = new Font("Arial", Font.ITALIC, 15);


        ImageIcon menuIcon = new ImageIcon("news-aggregator\\\\resource\\\\assets\\\\menuIcon.png" );
        JButton menuButton = new JButton(menuIcon);
        menuButton.setPreferredSize(new Dimension(65, 65));
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

            }
        });

        menu.add(menuButton);


        // add buttons into menu
        ImageIcon homeIcon = new ImageIcon("news-aggregator\\resource\\assets\\homeIcon.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.setPreferredSize(new Dimension(65, 65));
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
            }
        });

        ImageIcon userIcon = new ImageIcon("news-aggregator\\resource\\assets\\userIcon.png");
        JButton userButton = new JButton(userIcon);
        userButton.setPreferredSize(new Dimension(65, 65));
        userButton.setBorderPainted(false);
        userButton.setFocusPainted(false);
        userButton.setContentAreaFilled(false);
        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
            }
        });

        ImageIcon searchIcon = new ImageIcon("news-aggregator\\resource\\assets\\searchIcon.png");
        JButton searchButton = new JButton(searchIcon);
        searchButton.setPreferredSize(new Dimension(65, 65));
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
            }
        });

        menu.setLayout(new BorderLayout());

        JPanel jPanel_left = new JPanel();
        jPanel_left.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 16));
        jPanel_left.add(menuButton);
        jPanel_left.add(homeButton);
        jPanel_left.setBackground(BLACK_menu);
        menu.add(jPanel_left, BorderLayout.WEST);

        JPanel jPanel_right=new JPanel();
        jPanel_right.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 16));
        jPanel_right.add(searchButton);
        jPanel_right.add(userButton);
        jPanel_right.setBackground(BLACK_menu);

        menu.add(jPanel_right, BorderLayout.EAST);
        menu.setBounds(0, 0, 1440, 101);


        
        // create centre panel
        JPanel jPanel_news = new JPanel();
        jPanel_news.setLayout(new BorderLayout());

        ImageIcon ngotUpBo = new ImageIcon("news-aggregator\\resource\\assets\\ngotUpBo350_180.jpg");
        JLabel jLabel_image = new JLabel(ngotUpBo, JLabel.RIGHT);
        jLabel_image.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        jLabel_news_header = new JLabel("QUÁ VÔ ĐẠO BẤT LƯƠNG", JLabel.CENTER);
        jLabel_news_header.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel_news_header.setFont(font30B);

        // JPanel jPanel_news_header = new JPanel();
        // jPanel_news_header.setLayout(new BorderLayout());

        // jTextArea_news_header = new JTextArea("QUÁ VÔ ĐẠO BẤT LƯƠNG");
        // jTextArea_news_header.setFont(font30B);
        // jTextArea_news_header.setEditable(false);
        // jTextArea_news_header.setLineWrap(true);
        // jTextArea_news_header.setWrapStyleWord(true);

        // jPanel_news_header.add(jTextArea_news_header, BorderLayout.CENTER);

        JTextArea jTextArea_news_author = new JTextArea("Author: Author's name");
        jTextArea_news_author.setBackground(jLabel_image.getBackground());
        jTextArea_news_author.setFont(font15I);
        jTextArea_news_author.setEditable(false);
        jTextArea_news_author.setLineWrap(true);
        jTextArea_news_author.setWrapStyleWord(true);
        JTextArea jTextArea_news_postingDate = new JTextArea("Posting date:");
        jTextArea_news_postingDate.setBackground(jLabel_image.getBackground());
        jTextArea_news_postingDate.setFont(font15I);
        jTextArea_news_postingDate.setEditable(false);
        jTextArea_news_postingDate.setLineWrap(true);
        jTextArea_news_postingDate.setWrapStyleWord(true);
        JTextArea jTextArea_news_link = new JTextArea("Chi tiết: link bài viết");
        jTextArea_news_link.setBackground(jLabel_image.getBackground());
        jTextArea_news_link. setFont(font15I);
        jTextArea_news_link.setEditable(false);
        jTextArea_news_link.setLineWrap(true);
        jTextArea_news_link.setWrapStyleWord(true);


        JPanel jPanel_news_center = new JPanel(new BorderLayout());

        JPanel jPanel_news_source = new JPanel();
        jPanel_news_source.setLayout(new GridLayout(1, 2));
        
        JPanel jPanel_news_source_left = new JPanel(new GridLayout(3, 1));
        jPanel_news_source_left.add(jTextArea_news_author);
        jPanel_news_source_left.add(jTextArea_news_postingDate);
        jPanel_news_source_left.add(jTextArea_news_link);

        jPanel_news_source.add(jPanel_news_source_left);
        jPanel_news_source.add(jLabel_image);


        String text = "Như đã thông báo lúc nào đó, team đã tạm hoãn nấu content láo nửa buổi để có thêm thời gian xem xét và xác định các dữ liệu bất thường phát sinh ở các bài post đã đăng. Từ đó, chúng tôi sẽ đối chiếu với những quy định nội bộ đã ban hành để đưa ra các quyết định nhằm đảm bảo tính minh bạch.\n"
                    + "Dựa trên những kết quả kiểm tra ban đầu, Ngũ đại quyết định công khai tình trạng hiện tại của các thành viên sau đây:\n"
                    + "\n"
                    + "Lều báo: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "Diêm Thống Nhất: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "\n"
                    + "Sau thời gian tạm dừng, chỉ đạo, những thực thể này sẽ vẫn tiếp tục tham gia bình thường và còn lót tích hơn nữa so với trước đây.\n"
                    + "Trong quá trình thực hiện việc kiểm tra tuân thủ, chúng tôi sẽ cho phép các thành viên của Ngũ đại (những cá nhân không kể trên thì không được được phép) bổ sung thông tin hoặc phản hồi quyết định của BTC.\n"
                    + "Những biện pháp này được thực hiện với sự nghiêm túc nhất để bảo đảm tính lót tích lành mạnh.\n"
                    + "Chúng tôi sẽ sớm cập nhật thêm thông tin liên quan đến quá trình bắt đầu lót tích trở lại. Hãy tiếp tục theo dõi các kênh chính thức của Ngũ đại để nhận thông tin mới nhất. Xin chân thành cảm ơn sự đồng hành và ủng hộ của các fan diêm dành cho chúng tôi!\n"
                    + "Dựa trên những kết quả kiểm tra ban đầu, Ngũ đại quyết định công khai tình trạng hiện tại của các thành viên sau đây:\n"
                    + "\n"
                    + "Lều báo: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "Diêm Thống Nhất: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "\n"
                    + "Sau thời gian tạm dừng, chỉ đạo, những thực thể này sẽ vẫn tiếp tục tham gia bình thường và còn lót tích hơn nữa so với trước đây.\n"
                    + "Trong quá trình thực hiện việc kiểm tra tuân thủ, chúng tôi sẽ cho phép các thành viên của Ngũ đại (những cá nhân không kể trên thì không được được phép) bổ sung thông tin hoặc phản hồi quyết định của BTC.\n"
                    + "Những biện pháp này được thực hiện với sự nghiêm túc nhất để bảo đảm tính lót tích lành mạnh.\n"
                    + "Chúng tôi sẽ sớm cập nhật thêm thông tin liên quan đến quá trình bắt đầu lót tích trở lại. Hãy tiếp tục theo dõi các kênh chính thức của Ngũ đại để nhận thông tin mới nhất. Xin chân thành cảm ơn sự đồng hành và ủng hộ của các fan diêm dành cho chúng tôi!\n"
                    + "Dựa trên những kết quả kiểm tra ban đầu, Ngũ đại quyết định công khai tình trạng hiện tại của các thành viên sau đây:\n"
                    + "\n"
                    + "Lều báo: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "Diêm Thống Nhất: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "\n"
                    + "Sau thời gian tạm dừng, chỉ đạo, những thực thể này sẽ vẫn tiếp tục tham gia bình thường và còn lót tích hơn nữa so với trước đây.\n"
                    + "Trong quá trình thực hiện việc kiểm tra tuân thủ, chúng tôi sẽ cho phép các thành viên của Ngũ đại (những cá nhân không kể trên thì không được được phép) bổ sung thông tin hoặc phản hồi quyết định của BTC.\n"
                    + "Những biện pháp này được thực hiện với sự nghiêm túc nhất để bảo đảm tính lót tích lành mạnh.\n"
                    + "Chúng tôi sẽ sớm cập nhật thêm thông tin liên quan đến quá trình bắt đầu lót tích trở lại. Hãy tiếp tục theo dõi các kênh chính thức của Ngũ đại để nhận thông tin mới nhất. Xin chân thành cảm ơn sự đồng hành và ủng hộ của các fan diêm dành cho chúng tôi!\n"
                    + "Dựa trên những kết quả kiểm tra ban đầu, Ngũ đại quyết định công khai tình trạng hiện tại của các thành viên sau đây:\n"
                    + "\n"
                    + "Lều báo: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "Diêm Thống Nhất: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "\n"
                    + "Sau thời gian tạm dừng, chỉ đạo, những thực thể này sẽ vẫn tiếp tục tham gia bình thường và còn lót tích hơn nữa so với trước đây.\n"
                    + "Trong quá trình thực hiện việc kiểm tra tuân thủ, chúng tôi sẽ cho phép các thành viên của Ngũ đại (những cá nhân không kể trên thì không được được phép) bổ sung thông tin hoặc phản hồi quyết định của BTC.\n"
                    + "Những biện pháp này được thực hiện với sự nghiêm túc nhất để bảo đảm tính lót tích lành mạnh.\n"
                    + "Chúng tôi sẽ sớm cập nhật thêm thông tin liên quan đến quá trình bắt đầu lót tích trở lại. Hãy tiếp tục theo dõi các kênh chính thức của Ngũ đại để nhận thông tin mới nhất. Xin chân thành cảm ơn sự đồng hành và ủng hộ của các fan diêm dành cho chúng tôi!\n"
                    + "Dựa trên những kết quả kiểm tra ban đầu, Ngũ đại quyết định công khai tình trạng hiện tại của các thành viên sau đây:\n"
                    + "\n"
                    + "Lều báo: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "Diêm Thống Nhất: Vi phạm tiêu chuẩn cộng đồng\n"
                    + "\n"
                    + "Sau thời gian tạm dừng, chỉ đạo, những thực thể này sẽ vẫn tiếp tục tham gia bình thường và còn lót tích hơn nữa so với trước đây.\n"
                    + "Trong quá trình thực hiện việc kiểm tra tuân thủ, chúng tôi sẽ cho phép các thành viên của Ngũ đại (những cá nhân không kể trên thì không được được phép) bổ sung thông tin hoặc phản hồi quyết định của BTC.\n"
                    + "Những biện pháp này được thực hiện với sự nghiêm túc nhất để bảo đảm tính lót tích lành mạnh.\n"
                    + "Chúng tôi sẽ sớm cập nhật thêm thông tin liên quan đến quá trình bắt đầu lót tích trở lại. Hãy tiếp tục theo dõi các kênh chính thức của Ngũ đại để nhận thông tin mới nhất. Xin chân thành cảm ơn sự đồng hành và ủng hộ của các fan diêm dành cho chúng tôi!\n"
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
        jTextArea_news_center.setFont(font20);
        jTextArea_news_center.setLineWrap(true);
        jTextArea_news_center.setWrapStyleWord(true);
        jTextArea_news_center.setEditable(false);

        jPanel_news_center.add(jPanel_news_source, BorderLayout.NORTH);
        jPanel_news_center.add(jTextArea_news_center, BorderLayout.CENTER);


        jPanel_news.add(jLabel_news_header, BorderLayout.NORTH);
        jPanel_news.add(jPanel_news_center, BorderLayout.CENTER);



        JScrollPane scrollPane_News = new JScrollPane(jPanel_news);
        scrollPane_News.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane_News.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_News.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scrollPane_News.setBounds(0, 104, 1440, 916);


        // contentPane.add(scrollPane_News, BorderLayout.CENTER);



        // add into this
        this.setLayout(null);
        this.add(menu);
        this.add(scrollPane_News);
        this.setVisible(true); 
    }

    public static void main(String[] args) {
        new News();
    }
}


