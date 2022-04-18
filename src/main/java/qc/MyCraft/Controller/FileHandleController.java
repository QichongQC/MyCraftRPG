package qc.MyCraft.Controller;


import Common.SaveFileUtils.SaveFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import qc.MyCraft.Aspects.AspectsAnnotation.CheckUserStatusAnno;
import qc.MyCraft.Models.ControllerDTO.Equiment_img_upload_DTO;
import qc.MyCraft.PathManager.StaticFilePath;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/Files")
public class FileHandleController {

    @Autowired
    ServletContext servletContext;
    /**
     * 用于接收Equipment 装备富文本的图片;
     * @param request
     */
    @PostMapping("/Equipment_img_FileUpload")
    @ResponseBody
    @CheckUserStatusAnno
    public Object FileUpload(HttpServletRequest request, MultipartFile richTxtImg) throws IOException {
        LocalDate date=LocalDate.now();
        //返回给客户端的路径
        String resultPath= StaticFilePath.Admin_Equipment_Add_RichTxtImgs+date.getYear()+"/"+date.getMonth()+"/";
        //真实的文件存储路径
        String save_realPath = servletContext.getRealPath(resultPath);
        String[] supportExtension={".jpg",".png",".bmp",".gif"};

        Equiment_img_upload_DTO dto=new Equiment_img_upload_DTO(0);

        Equiment_img_upload_DTO.this_data this_data = dto.new this_data("url", "alt", "href");

        String saveResult = null;
        saveResult = SaveFileUtils.SaveOneFile(save_realPath, richTxtImg, supportExtension);

        //如果错误，则length只会等于1
        if (saveResult.length()==1){
            int i = Integer.parseInt(saveResult);
            dto.setErrno(i);
            return dto;
        }

        String fullPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
                + resultPath;

        this_data.setUrl(resultPath+saveResult);
        this_data.setHref(resultPath+saveResult);
        this_data.setAlt(resultPath+saveResult);
        dto.setData(this_data);
        return dto;
    }


    @PostMapping("/Equipment_video_FileUpload")
    @ResponseBody
    @CheckUserStatusAnno
    public Object VideoFileUpload(HttpServletRequest request, MultipartFile equipment_video){
        LocalDate date=LocalDate.now();
        //返回给客户端的路径
        String resultPath= StaticFilePath.Admin_Equipment_Add_RichTxtVideos+date.getYear()+"/"+date.getMonth()+"/";
        //真实的文件存储路径
        String save_realPath = servletContext.getRealPath(resultPath);

        String[] supportExtension={".wmv", ".avi", ".dat",".asf",".mp4",".rm",".m4v",".3gp"};
        Equiment_img_upload_DTO dto=new Equiment_img_upload_DTO(0);
        Equiment_img_upload_DTO.this_data this_data = dto.new this_data("url", "alt", "href");
        String saveResult = null;
        saveResult = SaveFileUtils.SaveOneFile(save_realPath, equipment_video, supportExtension);

        //如果错误，则length只会等于1
        if (saveResult.length()==1){
            int i = Integer.parseInt(saveResult);
            dto.setErrno(i);
            return dto;
        }

        this_data.setUrl(resultPath+saveResult);
        this_data.setHref(resultPath+saveResult);
        this_data.setAlt(resultPath+saveResult);
        dto.setData(this_data);
        return dto;
    }
}
