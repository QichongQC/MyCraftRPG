package qc.MyCraft.Service;

import Common.HtmlUtils.HtmlUtils;
import Common.PathUtils.PathUtils;
import Common.Search.BaseSerach;
import Common.Search.EquimentSearchModel;
import Common.ThreadLocalUtils;
import Common.UserManager;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import qc.MyCraft.Models.BaseModels.Suit;

import java.util.*;

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

    @Override
    /**
     * 删除那些被删除的数据占用的资源
     * 注意：只会返回true，写错了懒得改了。
     */
    public boolean release_deleted_resource(qc.MyCraft.Models.BaseModels.Equiment equiment){
        String picture = equiment.getPicture();
        String richTxt = equiment.getRichTxt();
        //提取全部的HTML链接
        Set<String> imgUrl = Common.HtmlUtils.HtmlUtils.getImgStr(richTxt);
        List<String> videoUrl = HtmlUtils.listVideoSrc(richTxt);

        //封面图片保存路径
        String picture_imgRealDir= PathUtils.getStaticDir(Common.ThreadLocalUtils.getRequest());
        //富文本图片保存路径
        String richTxt_imgRealDir= PathUtils.getStaticDir(Common.ThreadLocalUtils.getRequest());
        //富文本视频保存路径
        String richTxt_videoDir= PathUtils.getStaticDir(Common.ThreadLocalUtils.getRequest());
        //删除封面
        Common.SaveFileUtils.SaveFileUtils.deleteFile(picture_imgRealDir+picture);
        //删除富文本图片
        for (String str: imgUrl) {
            Common.SaveFileUtils.SaveFileUtils.deleteFile(richTxt_imgRealDir + str);
        }
        for (String str : videoUrl) {
            Common.SaveFileUtils.SaveFileUtils.deleteFile(richTxt_videoDir + str);
        }

        return true;
    }


    //---------------------这下面的代码使用 mybatis_plus提供的Mapper------------------------------

    @Autowired
    qc.MyCraft.dao.TestMybatis_EquimentMapper MP_equimentMapper;

    @Autowired
    qc.MyCraft.dao.EType eTypeServices;

    @Autowired
    qc.MyCraft.dao.SuitMapper suitMapper;

    @Override
    public void handelIndexSearchModel(EquimentSearchModel equimentSearchModel) {
        //设置页码条
        equimentSearchModel.setRecordCount(getRecordCount(equimentSearchModel));
        equimentSearchModel.setBarStart_And_BarEnd();
        //重构searchModel数据，以便查询
        //如果准备套装名称不为空 查询出对应名称的套装ID
        if (equimentSearchModel.getSuitName() != null && !equimentSearchModel.getSuitName().isEmpty()) {
            Map<String, Object> searchCondition = new HashMap<>();
            searchCondition.put("name", "%" + equimentSearchModel.getSuitName() + "%");
            QueryWrapper<Suit> queryWrapper = new QueryWrapper();
            queryWrapper.like("name", equimentSearchModel.getSuitName());
            List<Suit> suits = suitMapper.selectList(queryWrapper);
            if (suits.size() > 0) {
                List<String> idList = new ArrayList<>();
                suits.stream().forEach(suit -> {
                    idList.add(suit.getId());
                });
                equimentSearchModel.setSuitIds(idList);
            }
        }

    }

    @Override
    public void handelEquiment_detail(ModelAndView view, int id) {
        qc.MyCraft.Models.BaseModels.Equiment equiment = MP_equimentMapper.selectById(id);
        view.addObject("equiment", equiment);
        view.addObject("etype", eTypeServices.getById(equiment.getEtype()));
        //获得对应的套装id 与同套装武器
        if (equiment.getSuitId() != null && equiment.getSuitId().trim() != "") {
            Suit suit = suitMapper.selectById(equiment.getSuitId());
            QueryWrapper<qc.MyCraft.Models.BaseModels.Equiment> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("suitId",suit.getId());
            List<qc.MyCraft.Models.BaseModels.Equiment> equiments = MP_equimentMapper.selectList(queryWrapper);
            //套装
            view.addObject("suit", suit);
            //同套装武器
            view.addObject("suit_equiment_list",equiments);
        }

    }

    @Autowired
    AdminHomeServices adminHomeServices;
    public void handelSuit_Page_GET(ModelAndView view, Page<Suit> page,Suit searchModel) {
        //如果用户有登录权限
        Object userStatus = UserManager.getUserStatus(ThreadLocalUtils.getSession());
        //判断前端是否显示admin菜单
        view.addObject("userStatus",userStatus);
        //todo 后门2
        //if (userStatus!=null){
            HashMap<String, String> adminModules = adminHomeServices.getAdminModules();
            view.addObject("admin_modules",adminModules);
        //}
        //分页数据
        QueryWrapper<Suit> queryWrapper=new QueryWrapper<>();
        //查询名字
        if ( searchModel.getName()!=null&&!searchModel.getName().trim().equals("")){
            queryWrapper.like("name",searchModel.getName());
        }
        Page<Suit> suitPage = suitMapper.selectPage(page, queryWrapper);
        List<Suit> records = suitPage.getRecords();
        view.addObject("suit_list",records);
        view.addObject("paging",page);
        view.addObject("search_model",searchModel);
    }



    public void handelSuit_Detail_GET(ModelAndView view,Integer id) {
        //查询套装内容
        Suit suit = suitMapper.selectById(id);
        QueryWrapper<qc.MyCraft.Models.BaseModels.Equiment> equimentQueryWrapper=new QueryWrapper<>();
        equimentQueryWrapper.eq("suitId",id);
        List<qc.MyCraft.Models.BaseModels.Equiment> equiments = MP_equimentMapper.selectList(equimentQueryWrapper);
        view.addObject("suit",suit);
        view.addObject("suit_equiment_list",equiments);
    }


}
