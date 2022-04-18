package Common.PathUtils;

import javax.servlet.http.HttpServletRequest;

public class PathUtils {

    /**
     * 获得
     * http://localhost:8080/
     * @param request
     * @return
     */
    public  static String  getServerUriAndPort(HttpServletRequest request){
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
    }


    /**
     * 获得 webapp 的真实路径
     * @param request
     * @return
     */
    public static String getStaticDir(HttpServletRequest request){
        return request.getRealPath("/");
    }

}
