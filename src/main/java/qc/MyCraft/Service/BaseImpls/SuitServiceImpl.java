package qc.MyCraft.Service.BaseImpls;

import Common.HtmlUtils.HtmlUtils;
import Common.PathUtils.PathUtils;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import qc.MyCraft.Models.BaseModels.Suit;
import qc.MyCraft.dao.SuitMapper;

import java.util.List;
import java.util.Set;


@Service
public class SuitServiceImpl extends ServiceImpl<SuitMapper, Suit> implements qc.MyCraft.Service.Suit {


    @Override
    public void release_deleted_resource(Suit entity) {
        String picture = entity.getPicture();
        String richTxt = entity.getRichTxt();
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

    }
}
