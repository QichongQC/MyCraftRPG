package qc.MyCraft.Service;

import Common.Search.BaseSerach;
import org.springframework.stereotype.Service;

import java.util.List;

public interface Equiment {

    //获取装备
    /**
     * 如果没有数据返回Null
     */
    public List<qc.MyCraft.Models.BaseModels.Equiment> getEquimentListBySearch(BaseSerach serach);

    public qc.MyCraft.Models.BaseModels.Equiment getEquimentById(int id);

    public int addEquiment(qc.MyCraft.Models.BaseModels.Equiment equiment);

    public int editEquiment(qc.MyCraft.Models.BaseModels.Equiment equiment);

    public int rmEquiment(int id);

    /**
     * 获取对应搜索条件的数据数量
     * @param serach
     * @return
     */
    public int getRecordCount(BaseSerach serach);
}
