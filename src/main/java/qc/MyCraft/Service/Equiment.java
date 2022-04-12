package qc.MyCraft.Service;

import Common.Search.BaseSerach;

import java.util.List;

public interface Equiment {

    //获取装备
    public List<qc.MyCraft.Models.BaseModels.Equiment> getEquimentListBySearch(BaseSerach serach);

    public qc.MyCraft.Models.BaseModels.Equiment getEquimentById(int id);

    public int addEquiment(qc.MyCraft.Models.BaseModels.Equiment equiment);

    public int editEquiment(qc.MyCraft.Models.BaseModels.Equiment equiment);

    public int rmEquiment(int id);
}
