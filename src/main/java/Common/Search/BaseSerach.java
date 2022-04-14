package Common.Search;


import lombok.Data;

import java.util.List;

@Data
/**
 * 基本的分页信息 与 获取页码bar
 */
public class BaseSerach {
    //页码
    int pageIndex=1;
    //页大小
    int pageSize=10;
    int start;

    public int getStart() {
        return (pageIndex-1)*pageSize;
    }

    /**
     * 数据条数
     */
    int recordCount;
    /**
     * 页数
     */
    int pageCount;

    /**
     * 页数=总条数除以页面大小 + 1 | 0 (求余)
     * @return
     */
    public int getPageCount() {
        return recordCount % pageSize ==0 ? recordCount/pageSize : recordCount / pageSize+1;
    }

    int barStart;
    int barEnd;
    /**
     * 设置开始页码与结束页码 !!!!必须先为pageRecord赋值!!!!
     * @return
     */
    public void setBarStart_And_BarEnd(){
//        if (recordCount == 0){
//            throw new RuntimeException("recordCount 不能为0");
//        }
        //设置开始的页码
        barStart= pageIndex -1 >0? pageIndex-1 : 1;
        barEnd = barStart+2<=getPageCount()? barStart+2 : getPageCount();
        barStart=barEnd-2 >0? barEnd-2 : 1;
    }
    public static BaseSerach getInstance(){
        BaseSerach baseSerach = new BaseSerach();
        baseSerach.setPageIndex(1);
        baseSerach.setPageSize(10);
        return baseSerach;
    }
}
