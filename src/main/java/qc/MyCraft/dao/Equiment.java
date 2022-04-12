package qc.MyCraft.dao;

import Common.Search.BaseSerach;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Equiment {

    //获取装备
    public List<qc.MyCraft.Models.BaseModels.Equiment> getEquimentListBySearch(BaseSerach serach);

    @Select("select * from Equiment where id = #{id}")
    public qc.MyCraft.Models.BaseModels.Equiment getEquimentById(int id);

    public int addEquiment(qc.MyCraft.Models.BaseModels.Equiment equiment);

    public int editEquiment(qc.MyCraft.Models.BaseModels.Equiment equiment);

    public int rmEquiment(int id);
}