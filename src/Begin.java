import GUI.*;
import LOGIC.*;

public class Begin {

    public void start() throws Exception{
        Login_UI login_ui = new Login_UI();
        main_UI main_ui = new main_UI();
        Regist_UI regist_ui = new Regist_UI();
        Page_UI page_ui = new Page_UI();
        login_ui.visualise();
    }

}
