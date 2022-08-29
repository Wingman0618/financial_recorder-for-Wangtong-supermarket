package LOGIC;

import GUI.main_UI;

import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

import GUI.main_UI;

public class Login{

    private String username;
    private String password;
    private Console console = System.console();

    public void login(){
        main_UI mainPage = new main_UI();
        mainPage.visualise();
    }

    public Boolean checkAccount(String u, String p){
        try{
            FileReader f = new FileReader("../Database/Account/account.txt");
            BufferedReader bf = new BufferedReader(f);
            String account = bf.readLine();
            while(account != null){
                if(account.split(",")[0].equals(u)&&account.split(",")[1].equals(p)){
                    return true;
                } 
                account = bf.readLine();
            }
            f.close();
        }catch(Exception e){
            System.out.println("error");
            e.printStackTrace();
        }
        return false;
    }

    public void setUsername(String s){
        this.username = s;
    }

    public void setPassword(String s){
        this.password = s;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

}