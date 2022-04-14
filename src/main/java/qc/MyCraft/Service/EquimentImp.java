package qc.MyCraft.Service;

import Common.Search.BaseSerach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class EquimentImp implements Equiment{

    @Autowired
    qc.MyCraft.dao.Equiment equiment;


    @Override
    /**
     * 如果没有数据返回Null
     */
    public List<qc.MyCraft.Models.BaseModels.Equiment> getEquimentListBySearch(BaseSerach serach) {
        List<qc.MyCraft.Models.BaseModels.Equiment> equimentListBySearch = equiment.getEquimentListBySearch(serach);
        return equimentListBySearch.size()>0? equimentListBySearch:null;
    }

    @Override
    public qc.MyCraft.Models.BaseModels.Equiment getEquimentById(int id) {
        return equiment.getEquimentById(id);
    }

    @Override
    public int addEquiment(qc.MyCraft.Models.BaseModels.Equiment equiment) {
        return this.equiment.addEquiment(equiment);
    }

    @Override
    public int editEquiment(qc.MyCraft.Models.BaseModels.Equiment equiment) {
        return this.equiment.editEquiment(equiment);
    }

    @Override
    public int rmEquiment(int id) {
        return equiment.rmEquiment(id);
    }

    @Override
    public int getRecordCount(BaseSerach serach) {
        return  equiment.getRecordCount(serach);
    }
}
