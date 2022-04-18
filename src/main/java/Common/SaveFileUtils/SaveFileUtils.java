package Common.SaveFileUtils;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import qc.MyCraft.Models.ControllerDTO.Equiment_img_upload_DTO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

public class SaveFileUtils {

    /**
     * * 返回值
     *      1表示文件为空
     *      2表示文件文件类型错误
     *      否则返回保存的文件名
     * @param save_realDir
     * @param file
     * @param supportFileExtension
     * @return
     * @throws IOException
     */
    public static String  SaveOneFile(String save_realDir, MultipartFile file,String[] supportFileExtension)  {

        if (file==null || file.isEmpty()){
            return "1";
        }
        LocalDate date=LocalDate.now();

        //日期路径
        String fileName=file.getOriginalFilename();
        //类型
        String extName = fileName.substring(fileName.lastIndexOf('.'), fileName.length()).toLowerCase(Locale.ROOT);

        boolean isMatch = Arrays.stream(supportFileExtension).anyMatch((str) -> {
            return str.toLowerCase(Locale.ROOT).equals(extName.toLowerCase(Locale.ROOT));
        });
        //如果一个都不匹配
        if (!isMatch){
            return "2";
        }
        //随机创建文件
        String uuid_file_name = UUID.randomUUID().toString()+
                extName;
        String savePath=save_realDir+"/"+uuid_file_name;
        try {
            saveFile(savePath,file);
        } catch (IOException e) {
            LoggerFactory.getILoggerFactory().getLogger(SaveFileUtils.class.toString()).error("保存文件失败。");
            return "3";
        }

        return uuid_file_name;
    }

    private static void saveFile(String savePath, MultipartFile file) throws IOException {
        String saveDir=savePath.substring(0,savePath.lastIndexOf("/")).replace('\\','/');
        File dirFile=new File(saveDir);
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
        File saveFile=new File(savePath);
        file.transferTo(saveFile);
    }

    public static boolean deleteFile(String realDeleteFile){
        File file=new File(realDeleteFile);
        boolean delete = file.delete();
        return delete;
    }
}
