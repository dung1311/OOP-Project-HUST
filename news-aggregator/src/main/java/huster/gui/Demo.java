package huster.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Demo extends JFrame {
    private JTextField searchField;
    private JList<String> suggestionList;
    private DefaultListModel<String> suggestionListModel;
    private JScrollPane scrollPane;
    
    public Demo() {
        JFrame frame = new JFrame("Screen 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        System.setProperty("mautimthuychung", "BC60E7");
        // Color mautimColor = Color.getColor("mautimthuychung");

        //jButton menu
        JButton jButton_menu = new JButton();  
        jButton_menu.setBounds(11,19,32,32);
        URL icone_menu= Demo.class.getResource("/news-aggregator/resource/assets/MENU.png");
        ImageIcon icon_menu = new ImageIcon(icone_menu);
              
        jButton_menu.setPreferredSize( new Dimension(32,32));
        jButton_menu.setIcon(icon_menu);
        jButton_menu.setBorderPainted(false);
        jButton_menu.setFocusPainted(false);
        jButton_menu.setContentAreaFilled(false);
        jButton_menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            JFrame CFrame = new JFrame();
            CFrame.setTitle("CATEGORY");
            CFrame.setSize(320,450);
            CFrame.setLocationRelativeTo(null);
            CFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new GridLayout(10,1));
            contentPanel.setBackground(Color.YELLOW);
            
            JLabel jLabel_cat1 = new JLabel("BÉ VỊT");
            JLabel jLabel_cat2 = new JLabel("BÉ ẾCH");
            JLabel jLabel_cat3 = new JLabel("BÉ XUÂN MAI");
            jLabel_cat1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                
                    JOptionPane.showMessageDialog(null, "Be Vit chao ca nha iu");
                }
            });
            
    
            contentPanel.add(jLabel_cat1);
            contentPanel.add(jLabel_cat2);
            contentPanel.add(jLabel_cat3);
    
            JScrollPane scrollPane = new JScrollPane(contentPanel);
            CFrame.setBackground(Color.YELLOW);
            CFrame.add(scrollPane);   
            // CFrame.add(jLabel_cat1);
            // CFrame.add(jLabel_cat2);
            // CFrame.add(jLabel_cat3);
          

            CFrame.setVisible(true);
            }
        });
        
        //jButton settings
        JButton jButton_setting = new JButton();  
        jButton_setting.setBounds(308,19,32,32);
        URL icone_setting= Demo.class.getResource("/news-aggregator/resource/assets/SETTING.png");
        ImageIcon icon_setting = new ImageIcon(icone_setting);
              
        jButton_setting.setPreferredSize( new Dimension(32,32));
        jButton_setting.setIcon(icon_setting);
        jButton_setting.setBorderPainted(false);
        jButton_setting.setFocusPainted(false);
        jButton_setting.setContentAreaFilled(false);
        

        //jButton_search
        JButton jButton_search = new JButton();  
        jButton_search.setBounds(272,20,32,32);
        URL icone_search= Demo.class.getResource("/news-aggregator/resource/assets/SEARCH.png");
        ImageIcon icon_search = new ImageIcon(icone_search);
              
        jButton_search.setPreferredSize( new Dimension(32,32));
        jButton_search.setIcon(icon_search);
        jButton_search.setBorderPainted(false);
        jButton_search.setFocusPainted(false);
        jButton_search.setContentAreaFilled(false);
        

        //jButton_favorite
        JButton jButton_favorite = new JButton();  
        jButton_favorite.setBounds(7,81,21,21);
        URL icone_favorite= Demo.class.getResource("/news-aggregator/resource/assets/FAVORITES.png");
        ImageIcon icon_favorite = new ImageIcon(icone_favorite);
              
        jButton_favorite.setPreferredSize( new Dimension(20,20));
        jButton_favorite.setIcon(icon_favorite);
        jButton_favorite.setBorderPainted(false);
        jButton_favorite.setFocusPainted(false);
        jButton_favorite.setContentAreaFilled(false);
        

        //jButton_restart
        JButton jButton_restart = new JButton();  
        jButton_restart.setBounds(0,565,32,32);
        URL icone_restart= Demo.class.getResource("/news-aggregator/resource/assets/RESTART.png");
        ImageIcon icon_restart = new ImageIcon(icone_restart);
              
        jButton_restart.setPreferredSize( new Dimension(32,32));
        jButton_restart.setIcon(icon_restart);
        jButton_restart.setBorderPainted(false);
        jButton_restart.setFocusPainted(false);
        jButton_restart.setContentAreaFilled(false);
        
        
        //jButton_home
        JButton jButton_home = new JButton();  
        jButton_home.setBounds(160,565,32,32);
        URL icone_home= Demo.class.getResource("/news-aggregator/resource/assets/HOME.png");
        ImageIcon icon_home = new ImageIcon(icone_home);
              
        jButton_home.setPreferredSize( new Dimension(32,32));
        jButton_home.setIcon(icon_home);
        jButton_home.setBorderPainted(false);
        jButton_home.setFocusPainted(false);
        jButton_home.setContentAreaFilled(false);
        

        //jButton_signout
        JButton jButton_signout = new JButton();  
        jButton_signout.setBounds(310,565,32,32);
        URL icone_signout= Demo.class.getResource("/news-aggregator/resource/assets/SIGN OUT.png");
        ImageIcon icon_signout = new ImageIcon(icone_signout);
              
        jButton_signout.setPreferredSize( new Dimension(32,32));
        jButton_signout.setIcon(icon_signout);
        jButton_signout.setBorderPainted(false);
        jButton_signout.setFocusPainted(false);
        jButton_signout.setContentAreaFilled(false);
        

        //jLabel_favorite
        JLabel jLabel_favorite = new JLabel("POPULAR TODAY");
        jLabel_favorite.setBounds(32,30,125,125);
        Font font = new Font("Times New Roman",Font.BOLD,14);
        jLabel_favorite.setForeground(Color.RED);
        jLabel_favorite.setFont(font);
        
        //jButton_BXM
        JButton jButton_BXM = new JButton();  
        // jButton_BXM.setBounds(37,139,292,65);
        URL icone_BXM= Demo.class.getResource("/news-aggregator/resource/assets.png");
        ImageIcon icon_BXM = new ImageIcon(icone_BXM);
              
        jButton_BXM.setIcon(icon_BXM);
        jButton_BXM.setBorderPainted(false);
        jButton_BXM.setFocusPainted(false);
        jButton_BXM.setContentAreaFilled(false);
        jButton_BXM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Be Xuan Mike chao ca nha iu");
            }
        });

        //jLabel_BXM
        JLabel jLabel_BXM = new JLabel("Bé Xuân Mai Đi Học Lớp 1");
        
//         int x = jButton_BXM.getX()+52;
// int y = jButton_BXM.getY() + jButton_BXM.getHeight() + 10; // Cách 10 pixel so với jButton_BXM
// int width = jButton_BXM.getWidth();
// int height = 20; // Độ cao của jLabel_BXM
// jLabel_BXM.setBounds(x, y, width, height);
        jLabel_BXM.setFont(font);
        jLabel_BXM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
                JOptionPane.showMessageDialog(null, "Be Xuan Mike chao ca nha iu");
            }
        });
        //jLabel_BeVit
        JLabel jLabel_BeVit = new JLabel("Bé Vịt Đi Đám Cưới Người Yêu Cũ");
        // jLabel_BeVit.setBounds(70,338,292,20);
        jLabel_BeVit.setFont(font);
        jLabel_BeVit.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
       
        JOptionPane.showMessageDialog(null, "Be Vit chao ca nha iu");
    }
});
        //jLabel_BeEch
        JLabel jLabel_BeEch = new JLabel("Ếch ngồi đáy giếng");
        // jLabel_BeEch.setBounds(113,459,292,20);
        jLabel_BeEch.setFont(font);
        jLabel_BeEch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                JOptionPane.showMessageDialog(null, "Chu ech con da ia len dau ban");
            }
        });
        //jButton_CEC
        JButton jButton_CEC = new JButton();  
        // jButton_CEC.setBounds(27,387,292,65);
        URL icone= Demo.class.getResource("/news-aggregator/resource/assets/CEC.jpg");
        ImageIcon icon = new ImageIcon(icone);
              
        jButton_CEC.setIcon(icon);
        jButton_CEC.setBorderPainted(false);
        jButton_CEC.setFocusPainted(false);
        jButton_CEC.setContentAreaFilled(false);
      
      
        jButton_CEC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Chu ech con da ia len dau ban");
            }
        });
         //jButton_BeVit
         JButton jButton_BeVit = new JButton();  
        //  jButton_BeVit.setBounds(38,263,292,65);
         URL icone_BeVit= Demo.class.getResource("/news-aggregator/resource/assets/MCV.png");
         ImageIcon icon_BeVit = new ImageIcon(icone_BeVit);
               
         jButton_BeVit.setIcon(icon_BeVit);
         jButton_BeVit.setBorderPainted(false);
         jButton_BeVit.setFocusPainted(false);
         jButton_BeVit.setContentAreaFilled(false);
       
       
         jButton_BeVit.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 JOptionPane.showMessageDialog(null, "Be Vit chao ca nha iu");
             }
         });
         //jButton trung`
         JButton jButton_CEC1 = new JButton();  
         // jButton_CEC.setBounds(27,387,292,65);
         URL icone_CEC1= Demo.class.getResource("/news-aggregator/resource/assets/CEC.jpg");
         ImageIcon icon_CEC1 = new ImageIcon(icone_CEC1);
               
         jButton_CEC1.setIcon(icon_CEC1);
         jButton_CEC1.setBorderPainted(false);
         jButton_CEC1.setFocusPainted(false);
         jButton_CEC1.setContentAreaFilled(false);
       
       
         jButton_CEC1.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 JOptionPane.showMessageDialog(null, "Chu ech con da ia len dau ban");
             }
         });
         JButton jButton_CEC2 = new JButton();  
         // jButton_CEC2.setBounds(27,387,292,65);
         URL icone_CEC2= Demo.class.getResource("/news-aggregator/resource/assets/CEC.jpg");
         ImageIcon icon_CEC2 = new ImageIcon(icone_CEC2);
               
         jButton_CEC2.setIcon(icon_CEC2);
         jButton_CEC2.setBorderPainted(false);
         jButton_CEC2.setFocusPainted(false);
         jButton_CEC2.setContentAreaFilled(false);
       
       
         jButton_CEC2.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 JOptionPane.showMessageDialog(null, "Chu ech con da ia len dau ban");
             }
         });
         JPanel jPanel = new JPanel();
         jPanel.setBackground(Color.GRAY);
         jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));

         JPanel jPanel_BXM1= new JPanel();
         jPanel_BXM1.setPreferredSize(new Dimension(292,65));
         jPanel_BXM1.add(jButton_BXM);
         

         JPanel jPanel_BXM2= new JPanel();
         jPanel_BXM2.setPreferredSize(new Dimension(jLabel_BXM.getWidth(),80));
         jPanel_BXM2.add(jLabel_BXM); 

         JPanel jPanel_MCV1= new JPanel();
         jPanel_MCV1.setPreferredSize(new Dimension(292,65));
         jPanel_MCV1.add(jButton_BeVit);


         JPanel jPanel_MCV2= new JPanel();
         jPanel_MCV2.setPreferredSize(new Dimension(jLabel_BeVit.getWidth(),80));
         jPanel_MCV2.add(jLabel_BeVit);

         JPanel jPanel_CEC1= new JPanel();
         jPanel_CEC1.setPreferredSize(new Dimension(292,65));
         jPanel_CEC1.add(jButton_CEC);


         JPanel jPanel_CEC2= new JPanel();
         jPanel_CEC2.setPreferredSize(new Dimension(jLabel_BeEch.getWidth(),80));
         jPanel_CEC2.add(jLabel_BeEch);

         JPanel jPanel_CECT1= new JPanel();
         jPanel_CECT1.setPreferredSize(new Dimension(292,150));
         jPanel_CECT1.add(jButton_CEC1);

         JPanel jPanel_CECT2= new JPanel();
         jPanel_CECT2.setPreferredSize(new Dimension(292,150));
         jPanel_CECT2.add(jButton_CEC2);



         jPanel.add(jPanel_BXM1);   
         jPanel.add(jPanel_BXM2);
         jPanel.add(jPanel_CEC1);   
         jPanel.add(jPanel_CEC2);
         jPanel.add(jPanel_MCV1);
         jPanel.add(jPanel_MCV2);
         jPanel.add(jPanel_CECT1);
         jPanel.add(jPanel_CECT2);



         JScrollPane scrollPane_suggested = new JScrollPane(jPanel);
         scrollPane_suggested.setPreferredSize(new Dimension(320, 450));

         scrollPane_suggested.setBounds(18,105,320,450);


        // add searchField + searchSuggestList 
        searchField = new JTextField();
        searchField.setBounds(50, 20, 220, 32);

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

        
        suggestionListModel = new DefaultListModel<>();
        suggestionList = new JList<>(suggestionListModel);

       
        suggestionList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedSuggestion = suggestionList.getSelectedValue();
                if (selectedSuggestion != null) {
                    searchField.setText(selectedSuggestion);
                }
            }
        });

        scrollPane = new JScrollPane(suggestionList);
        scrollPane.setBounds(50, 50, 220, 32);
        
        frame.setLayout(null);
        frame.setBackground(Color.ORANGE);
        frame.setForeground(Color.ORANGE);
        frame.getContentPane().add(jButton_menu);
        frame.getContentPane().add(jButton_setting);
        frame.getContentPane().add(jButton_search);
        frame.getContentPane().add(jButton_favorite);
        frame.getContentPane().add(jLabel_favorite);
       
        // frame.getContentPane().add(jButton_BXM);
        // frame.getContentPane().add(jLabel_BXM);
        // frame.getContentPane().add(jButton_BeVit);
        // frame.getContentPane().add(jLabel_BeVit);
        // frame.getContentPane().add(jButton_CEC);
        // frame.getContentPane().add(jLabel_BeEch);
        
        frame.getContentPane().add(jButton_restart);
        frame.getContentPane().add(jButton_home);
        frame.getContentPane().add(jButton_signout);

        frame.add(searchField);
        frame.add(scrollPane);
        frame.add(scrollPane_suggested);
        
        
        scrollPane.setVisible(false);
       
        frame.setVisible(true);
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
        List<String> suggestions = new ArrayList<>();
        suggestions.add("Bé Xuân Mai Đi Học Lớp 1");
        suggestions.add("Bé Vịt Đi Đám Cưới Người Yêu Cũ");
        suggestions.add("Ếch ngồi đáy giếng");

        List<String> filteredSuggestions = new ArrayList<>();
        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().contains(searchText.toLowerCase())) {
                filteredSuggestions.add(suggestion);
            }
        }

        return filteredSuggestions;
    }
    public static void main(String[] args) {
        new Demo();
    }
}
