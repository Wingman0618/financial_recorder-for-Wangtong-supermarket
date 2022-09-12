package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main_UI{

    private JButton btn_add;



    public void visualise(){
        JFrame jf = new JFrame("WELCOME");
        jf.setSize(400, 300);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLayout(null);
                
        JPanel jp_1 = new JPanel();
        LayoutManager layout = new BoxLayout(jp_1, BoxLayout.Y_AXIS);  
        jp_1.setLayout(layout);
        jp_1.setBackground(Color.LIGHT_GRAY);

        btn_add = new JButton("新增存档");
        btn_add.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        btn_add.setOpaque(true);
        btn_add.setBorderPainted(false);
        btn_add.setBackground(Color.GRAY);
        btn_add.setForeground(Color.WHITE);
        btn_add.setBounds(275, 450, 200, 50);
        btn_add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Page_UI page_UI = new Page_UI();
                page_UI.visualise();
                jf.dispose();
            }

        });
        
        JLabel welcome;
        // ImageIcon bg_pic = new ImageIcon("../Database/background.jpg");
        welcome = new JLabel("欢迎使用", JLabel.CENTER);
        welcome.setFont(new Font("Verdana", Font.PLAIN, 40));
        welcome.setVerticalAlignment(JLabel.CENTER);
        welcome.setBounds(200,100,300,150);
        welcome.setEnabled(false);
        
        jp_1.add(Box.createVerticalGlue());
        jp_1.add(welcome);
        jp_1.add(Box.createRigidArea(new Dimension(125, 100)));
        jp_1.add(btn_add);
        jp_1.add(Box.createVerticalGlue());
        

        jf.setContentPane(jp_1);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

}