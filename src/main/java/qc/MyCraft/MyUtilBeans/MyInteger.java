package qc.MyCraft.MyUtilBeans;

import org.springframework.stereotype.Component;

@Component("myInteger")
/**
 * 用于在前端页面修改String为int
 */
public class MyInteger {

    public static int parseInt(String val){
        if (val==null){
            return -1;
        }
        return Integer.parseInt(val);
    }
}
