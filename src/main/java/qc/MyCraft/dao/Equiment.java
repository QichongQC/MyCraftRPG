package qc.MyCraft.dao;

import Common.Search.BaseSerach;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Equiment {

    //获取装备
    public List<qc.MyCraft.Models.BaseModels.Equiment> getEquimentListBySearch(BaseSerach serach);

    /**
     * 获取对应搜索条件的数据数量
     * @param serach
     * @return
     */
    public int getRecordCount(BaseSerach serach);

    @Select("select * from Equiment where id = #{id}")
    public qc.MyCraft.Models.BaseModels.Equiment getEquimentById(int id);

    @Insert("insert into Equiment (Etype,picture,`describe`,achieving) values (#{Etype},#{picture},#{describe},#{achieving})")
    public int addEquiment(qc.MyCraft.Models.BaseModels.Equiment equiment);
    @Update("update Equiment set etype=#{etype},picture=#{picture},`describe`=#{describe},achieving=#{achieving},richTxt=#{richTxt} where id=${id}")
    public int editEquiment(qc.MyCraft.Models.BaseModels.Equiment equiment);

    @Delete("delete from equiment where id =#{id}")
    public int rmEquiment(int id);
}