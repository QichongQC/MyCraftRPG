package Common;

import javax.servlet.http.HttpSession;

public class UserManager {

    static String SESSION_USER_KEY="SESSION_USER_STATUS";

    public static void setUserManger(HttpSession session){
        session.setAttribute(SESSION_USER_KEY,true);
    }

    public static Object getUserStatus(HttpSession session){
        return session.getAttribute(SESSION_USER_KEY);
    }
    public static void loginOut(HttpSession session){
        session.removeAttribute(SESSION_USER_KEY);
    }
}
