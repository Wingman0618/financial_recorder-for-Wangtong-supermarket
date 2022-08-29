package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main_UI{

    private JButton btn_add;



    public void visualise(){
        JFrame jf = new JFrame("WELCOME");
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp_1 = new JPanel();
        jp_1.setLayout(null);

        btn_add = new JButton("Add");
        btn_add.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        btn_add.setBounds(300, 100, 200, 50);
        btn_add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Page_UI page_UI = new Page_UI();
                page_UI.visualise();
                jf.dispose();
            }

        });
        
        jp_1.add(btn_add);

        jf.setContentPane(jp_1);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

}