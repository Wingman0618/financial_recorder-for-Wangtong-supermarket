package LOGIC;

import GUI.Login_UI;

import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;


public class Regist{

    private String username;
    private String password;
    private String password_2;

    public void regist(){
        Login_UI login_ui =new Login_UI();
        login_ui.visualise();
    }

    public boolean saveAccount(String u, String p, String cp){
        if(checkUsername(u)==false){
            System.out.println("username exists");
            return false;
        }
        if(p.equals(cp)){
            try{
                File f = new File("../Database/Account/account.txt");
                FileWriter fw = new FileWriter(f,true);
                fw.write(u+","+p+"\n");
                fw.flush();
                fw.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return true;
        }else{
            System.out.println("password different");
        }
        return false;
    }

    public Boolean checkUsername(String u){
        try{
            FileReader f = new FileReader("../Database/Account/account.txt");
            BufferedReader bf = new BufferedReader(f);
            String account = bf.readLine();
            while(account != null){
                if(account.split(",")[0].equals(u)){
                    return false;
                } 
                account = bf.readLine();
            }
            f.close();
        }catch(Exception e){
            System.out.println("error");
            e.printStackTrace();
        }
        return true;
    }

    public void setUsername(String s){
        this.username = s;
    }

    public void setPassword(String s){
        this.password = s;
    }

    public void setPassword_2(String s){
        this.password_2 = s;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getPassword_2(){
        return this.password_2;
    }
}