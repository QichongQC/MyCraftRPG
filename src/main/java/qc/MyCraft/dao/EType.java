package qc.MyCraft.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EType {

    //获取全部的装备种类
    @Select("select * from etype")
    public List<qc.MyCraft.Models.BaseModels.EType> getAllEtype();

    //添加
    public int addEtype(qc.MyCraft.Service.EType eType);
    //修改
    public int editEType(qc.MyCraft.Service.EType eType);
    //删除
    public int rmEType(int id);
}