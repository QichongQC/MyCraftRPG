package qc.MyCraft.Service;

import Common.Search.BaseSerach;
import Common.Search.EquimentSearchModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.servlet.ModelAndView;
import qc.MyCraft.Models.BaseModels.Suit;

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

    /**
     * 删除那些被删除数据的静态资源
     *
     * @param
     * @return
     */
    boolean release_deleted_resource(qc.MyCraft.Models.BaseModels.Equiment equiment);

    void handelIndexSearchModel(EquimentSearchModel equimentSearchModel);

    void handelEquiment_detail(ModelAndView view, int id);

    void handelSuit_Page_GET(ModelAndView view, Page<Suit> page,Suit searchModel);

    public void handelSuit_Detail_GET(ModelAndView view,Integer id);

}
