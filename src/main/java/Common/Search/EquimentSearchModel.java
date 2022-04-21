package Common.Search;

import lombok.Data;

import java.util.List;

@Data
public class EquimentSearchModel extends BaseSerach {
    String id;
    String Etype;
    String picture;
    String describe;
    String achieving;
    String name;
    /**
     * 套装名称
     */
    String suitName;

    //0---------------非查询条件的字段---------------0
    /**
     * sql中用于拼接查询
     */
    List<String> suitIds;
}
