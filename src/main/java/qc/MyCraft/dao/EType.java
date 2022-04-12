package qc.MyCraft.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EType {

    //获取全部的装备种类
    @Select("select * from etype")
    public List<qc.MyCraft.Models.BaseModels.EType> getAllEtype();

    //添加
    @Insert("insert into etype(typeName,introduce) values('${typeName}','${introduce}')")
    public int addEtype(qc.MyCraft.Models.BaseModels.EType eType);

    //修改
    @Update("update etype set typeName= '${typeName}' ,introduce='${introduce}' where id = ${id}")
    public int editEType(qc.MyCraft.Models.BaseModels.EType eType);

    //删除
    @Delete("delete FROM etype where id = #{id} ")
    public int rmEType(int id);
}