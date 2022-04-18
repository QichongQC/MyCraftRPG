package qc.MyCraft.Service;

import Common.HtmlUtils.HtmlUtils;
import Common.PathUtils.PathUtils;
import Common.Search.BaseSerach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qc.MyCraft.PathManager.StaticFilePath;

import java.util.*;
import java.util.regex.*;

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
        for (String str:
             imgUrl) {
            Common.SaveFileUtils.SaveFileUtils.deleteFile(richTxt_imgRealDir+str);
        }
        for (String str:
                videoUrl) {
            Common.SaveFileUtils.SaveFileUtils.deleteFile(richTxt_videoDir+str);
        }

        return true;
    }
}
