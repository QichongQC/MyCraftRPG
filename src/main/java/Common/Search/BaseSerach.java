package Common.Search;


import lombok.Data;

@Data
public class BaseSerach {
    //页码
    int pageIndex;
    //页大小
    int pageSize;


    public static BaseSerach getInstance(){
        BaseSerach baseSerach = new BaseSerach();
        baseSerach.setPageIndex(1);
        baseSerach.setPageSize(10);
        return baseSerach;
    }
}
