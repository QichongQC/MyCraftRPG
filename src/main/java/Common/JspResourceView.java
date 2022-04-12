package Common;

import org.springframework.web.servlet.view.InternalResourceView;

import java.io.File;
import java.util.Locale;

public class JspResourceView extends InternalResourceView {

    @Override
    public boolean checkResource(Locale locale) throws Exception {
        String s = this.getServletContext().getRealPath("/") + getUrl();
        File file = new File(s);
        return file.exists();// 判断该页面是否存在
    }
}
