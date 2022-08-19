package GUI;
import LOGIC.Login;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class Login_UI{

    private JLabel user_label;
    private JLabel password_label;

    private JTextField userName;
    private JPasswordField passWord;

    private JButton login;
    private JButton regist;

    public void visualise(){
        JFrame jf = new JFrame("login");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel p_username = new JPanel();
        JPanel p_password = new JPanel();
        JPanel p_buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        user_label=new JLabel("username");
        password_label = new JLabel("password");
        userName = new JTextField(10);
        passWord = new JPasswordField(10);
        login = new JButton("login");
        regist = new JButton("regist");

        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Login login = new Login();
                login.setUsername(userName.getText()) ;
                login.setPassword(passWord.getText());
                if(login.checkAccount(login.getUsername(),login.getPassword())){
                    System.out.println("success");
                    login.login();
                    jf.dispose();
                }else{
                    System.out.println("failed");
                }
            }
        });

        regist.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Regist_UI regist_ui = new Regist_UI();
                regist_ui.visualise();
            }
        });

        p_username.add(user_label);
        p_username.add(userName);

        p_password.add(password_label);
        p_password.add(passWord);

        p_buttons.add(login);
        p_buttons.add(regist);

        Box vBox = Box.createVerticalBox();
        vBox.add(p_username);
        vBox.add(p_password);
        vBox.add(p_buttons);

        jf.setContentPane(vBox);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
    
}