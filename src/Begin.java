import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Begin {

    private String username;
    private String password;
    private Console console = System.console();

    public void start() throws Exception{
        System.out.println("1. Login   2.regist   3.quit");
        String op = console.readLine("PLS input numbers: ");
        this.start(op);
    }
    
    public void start(String x) throws Exception{
        if(x.equals("1") ){
            System.out.println("login");
            if(this.login()==true){
                System.out.println("success");
            }else{
                start();
            }
        }
        else if(x.equals("2")){
            System.out.println("regist");
            if(this.regist()==true){
                System.out.println("success");
            }else{
                start();
            }
        }
        else if(x.equals("3")){
            System.exit(0);
        }
        else{
            System.out.println("input invald");
            start();
        }
    }

    public Boolean login(){
        System.out.println("username: ");
        this.username = console.readLine();
        System.out.println("password: ");
        this.password = console.readLine();
        return checkAccount(username, password);
    }

    public Boolean regist(){
        System.out.println("username: ");
        this.username = console.readLine();
        if(checkUsername(username)==false){
            System.out.println("username exists");
            return false;
        }
        System.out.println("password: ");
        this.password = console.readLine();
        System.out.println("confirm password: ");
        if(this.password.equals(console.readLine())){
            try{
                File f = new File("../Database/Account/account.txt");
                FileWriter fw = new FileWriter(f,true);
                fw.write(this.username+","+this.password+"\n");
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
                if(account.split(",")[0].equals(this.username)){
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

    public Boolean checkAccount(String u, String p){
        try{
            FileReader f = new FileReader("../Database/Account/account.txt");
            BufferedReader bf = new BufferedReader(f);
            String account = bf.readLine();
            while(account != null){
                if(account.split(",")[0].equals(this.username)&&account.split(",")[1].equals(this.password)){
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

}
