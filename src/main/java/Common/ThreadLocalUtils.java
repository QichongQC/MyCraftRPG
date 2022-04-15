package Common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//用于保存线程内对象
public class ThreadLocalUtils {

    private static ThreadLocal<HttpServletRequest> requestThreadLocal=new InheritableThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> responseThreadLocal=new InheritableThreadLocal<>();


    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) requestThreadLocal.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestThreadLocal.set(request);
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) responseThreadLocal.get();
    }

    public static void setResponse(HttpServletResponse response) {
        responseThreadLocal.set(response);
    }

    public static HttpSession getSession() {
        return (HttpSession) ((HttpServletRequest) requestThreadLocal.get()).getSession();
    }

}
