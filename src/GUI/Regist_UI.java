package GUI;
import LOGIC.Login;
import LOGIC.Regist;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class Regist_UI{

    private JLabel user_label;
    private JLabel password_label;
    private JLabel confirmPassword_label;

    private JTextField userName;
    private JPasswordField passWord;
    private JPasswordField confirmPassword;

    private JButton regist;

    public void visualise(){
        JFrame jf = new JFrame("login");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel p_username = new JPanel();
        JPanel p_password = new JPanel();
        JPanel p_confirm = new JPanel();
        JPanel p_buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        user_label=new JLabel("username");
        password_label = new JLabel("password");
        confirmPassword_label = new JLabel("confirm password");
        userName = new JTextField(10);
        passWord = new JPasswordField(10);
        confirmPassword = new JPasswordField(10);
        regist = new JButton("regist");

        regist.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                Regist regist = new Regist();
                if(regist.saveAccount(userName.getText(), passWord.getText(),confirmPassword.getText())){
                    regist.regist();
                    jf.dispose();
                }else{
                    System.out.println("failed");
                }
            }
        });

        p_username.add(user_label);
        p_username.add(userName);

        p_password.add(password_label);
        p_password.add(passWord);

        p_confirm.add(confirmPassword_label);
        p_confirm.add(confirmPassword);

        p_buttons.add(regist);

        Box vBox = Box.createVerticalBox();
        vBox.add(p_username);
        vBox.add(p_password);
        vBox.add(p_confirm);
        vBox.add(p_buttons);

        jf.setContentPane(vBox);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
    
}