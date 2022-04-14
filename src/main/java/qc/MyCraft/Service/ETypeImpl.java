package qc.MyCraft.Service;

import Common.Search.BaseSerach;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qc.MyCraft.Models.BaseModels.Equiment;

import java.util.List;

@Service("ETypeService")
@Data
public class ETypeImpl implements EType{

    @Autowired
    qc.MyCraft.dao.EType eType;

    @Override
    public List<qc.MyCraft.Models.BaseModels.EType> getEtypeListBySearch(BaseSerach serach) {
        return null;
    }

    @Override
    public List<qc.MyCraft.Models.BaseModels.EType> getAllEtype() {
        return eType.getAllEtype();
    }

    @Override
    public int addEtype(EType eType) {
        return eType.addEtype(eType);
    }

    @Override
    public int editEType(EType eType) {
        return eType.editEType(eType);
    }

    @Override
    public int rmEType(int id) {
        return eType.rmEType(id);
    }
}
