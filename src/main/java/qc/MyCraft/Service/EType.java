package qc.MyCraft.Service;

import java.util.List;
public interface EType {

    //获取全部的装备种类
    public List<qc.MyCraft.Models.BaseModels.EType> getAllEtype();

    //添加
    public int addEtype(EType eType);
    //修改
    public int editEType(EType eType);
    //删除
    public int rmEType(int id);
}
