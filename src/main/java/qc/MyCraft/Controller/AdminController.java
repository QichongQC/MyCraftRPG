package qc.MyCraft.Controller;

import Common.PathUtils.PathUtils;
import Common.SaveFileUtils.SaveFileUtils;
import Common.Search.EquimentSearchModel;
import Common.Template;
import Common.ValidateUtils.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import qc.MyCraft.Aspects.AspectsAnnotation.CheckUserStatusAnno;
import qc.MyCraft.Models.BaseModels.Equiment;
import qc.MyCraft.Models.BaseModels.Suit;
import qc.MyCraft.Models.ControllerDTO.AdminControllerEquimentDTO;
import qc.MyCraft.Models.ControllerDTO.AdminControllerSuitDTO;
import qc.MyCraft.PathManager.StaticFilePath;
import qc.MyCraft.Service.AdminHomeServices;
import qc.MyCraft.Service.BaseImpls.SuitServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Admin")
public class AdminController {


    @Autowired
    AdminHomeServices adminHomeServices;

    @Autowired
    qc.MyCraft.Service.ETypeImpl eTypeService;

    @Autowired
    qc.MyCraft.Service.EquimentImp equimentService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    qc.MyCraft.Service.Suit suitService;
    @CheckUserStatusAnno
    @RequestMapping("/Home")
    public ModelAndView equiment(EquimentSearchModel equimentSearchModel,HttpServletRequest request){
        //设置子页面
        ModelAndView view = Template.getTemplate("index", "AdminEquiment", "装备");

        HashMap<String, String> adminModules = adminHomeServices.getAdminModules();
        view.addObject("etype_list",eTypeService.getAllEtype());
        view.addObject("admin_modules",adminModules);
        view.addObject("update_url",PathUtils.getServerUriAndPort(request)+"/Admin/Equiment/Update/");
        if (equimentSearchModel == null){
            equimentSearchModel=new EquimentSearchModel();
        }

        //设置页码条需要的数据
        equimentSearchModel.setRecordCount(equimentService.getRecordCount(equimentSearchModel));
        equimentSearchModel.setBarStart_And_BarEnd();

        //填充数据到viewModel
        view.addObject("equiment_list",equimentService.getEquimentListBySearch(equimentSearchModel));
        view.addObject("searchModel",equimentSearchModel);
        return view;
    }

    @CheckUserStatusAnno
    @GetMapping("/Equiment/Put")
    public ModelAndView equiment_addPage(){
        ModelAndView modelAndView=Template.getTemplate("index","AdminEquiment_ADD","添加装备");

        //处理view
        adminHomeServices.handelEquiment_ADD(modelAndView);
        return modelAndView;
    }

    @Autowired
    qc.MyCraft.Service.Mybatis_plus_serviceDemo.ServicesEquimentImpl mp_equimentService;

    @CheckUserStatusAnno
    @PostMapping("/Equiment/Put")
    public ModelAndView add_equiment(HttpServletRequest request,  @Valid Equiment equiment,BindingResult bindingResult,
                                     MultipartFile pictrue_img, String deleteImgs) throws IOException {
        //获取数据
        ModelAndView view=Template.getTemplate("index","AdminEquiment_ADD","添加装备");
        view.addObject("etype_list",eTypeService.getAllEtype());
        //删除提交上来的多余图片
        if (deleteImgs!=null && !deleteImgs.trim().isEmpty()){
            //如果不为空就删除
            deleteImgs(deleteImgs,request);
        }
        String realPath=PathUtils.getStaticDir(request)+StaticFilePath.Admin_Equipment_Add_PictureImgs;
        //验证数据 错误列表
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String, String> field_error_map = ValidateUtils.FieldErrorList_To_Map(fieldErrors);

        //保存图片 如果图片没保存成功添加一条错误到页面
        String saveResult = SaveFileUtils.SaveOneFile( realPath,
                pictrue_img, new String[]{".png", ".gif", ".jpg", ".jpeg", ".bmp"});
        if (saveResult.length()==1){
           field_error_map.put("picture","图片上传失败！");
        }

        //如果添加的字段有错误
        if (field_error_map.size()>0){
            view.addObject("field_errors",field_error_map);
            return view;
        }

        //如果数据都通过了校验
        equiment.setPicture(StaticFilePath.Admin_Equipment_Add_PictureImgs+saveResult);

        //int i = equimentService.addEquiment(equiment);

        if (equiment.getSuitId().trim().equals("")){
            equiment.setSuitId(null);
        }

        boolean save = mp_equimentService.save(equiment);

        if (!save){
            //添加失败
            Logger logger = LoggerFactory.getILoggerFactory().getLogger("AdminController.class");
            logger.error("添加Equiment失败：/Admin/Equiment/Put");

            return view;
        }

        //添加成功
        view.addObject("addone",true);
        return view;
    }

    /**
     * 删除对应的文件
     * 返回删除失败的数目
     * @return
     */
    private int deleteImgs(String imgsStr, HttpServletRequest request){
        //获得删除的真实路径文件夹
        String realPath = request.getRealPath("/");
        //删除图片
        String[] split = imgsStr.split("`_`");
        int i=0;
        for (String img:
                split) {
            if (img.trim().equals("")){
                continue;
            }

            boolean b = SaveFileUtils.deleteFile(realPath+img);
            if (!b)i++;
        }
        return i;
    }

    @CheckUserStatusAnno
    @PostMapping("/Equiment/Delete/{id}")
    @ResponseBody
    public AdminControllerEquimentDTO del_equiment(@PathVariable int id){
        Equiment equimentById = equimentService.getEquimentById(id);
        AdminControllerEquimentDTO result = new AdminControllerEquimentDTO();

        if (equimentById == null){
            result.setState(0);
            return result;
        }

        int i = equimentService.rmEquiment(id);
        if (i==1){
            equimentService.release_deleted_resource(equimentById);
        }
        result.setState(1);
        //0失败 1 成功
        return result;
    }

    @CheckUserStatusAnno
    @GetMapping("/Equiment/Update/{id}")
    public ModelAndView update_equiment(@PathVariable Integer id){
        ModelAndView modelAndView=Template.getTemplate("index","AdminUpdate","修改装备");

        adminHomeServices.handelUpdateEquiment_GET(modelAndView,id);

        return modelAndView;
    }
    @CheckUserStatusAnno
    @PostMapping("/Equiment/Update/{id}")
    @ResponseBody
    public ModelAndView update_equiment(@PathVariable int id,HttpServletRequest request,
                                        @Valid Equiment equiment,
                                        BindingResult bindingResult,
                                        MultipartFile pictrue_img, String deleteImgs){
        //获取数据
        ModelAndView view= update_equiment(id);

        //删除提交上来的多余图片
        if (deleteImgs!=null && !deleteImgs.trim().isEmpty()){
            //如果不为空就删除
            deleteImgs(deleteImgs,request);
        }
        String realPath=PathUtils.getStaticDir(request)+StaticFilePath.Admin_Equipment_Add_PictureImgs;

        //验证数据 错误列表
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String, String> field_error_map = ValidateUtils.FieldErrorList_To_Map(fieldErrors);

        String saveResult ="0";
        //如果上传的图片不为空，就修改
        if (pictrue_img!=null && !pictrue_img.isEmpty()) {
            //保存图片 如果图片没保存成功添加一条错误到页面
            saveResult = SaveFileUtils.SaveOneFile(realPath,
                    pictrue_img, new String[]{".png", ".gif", ".jpg", ".jpeg", ".bmp"});
            if (saveResult.length()==1){
                field_error_map.put("picture","图片上传失败！");
            }
        }
        //如果添加的字段有错误
        if (field_error_map.size()>0){
            view.addObject("field_errors",field_error_map);
            return view;
        }
        //如果数据都通过了校验
        if (pictrue_img==null || pictrue_img.isEmpty()){
            //如果图片为空
            //则不修改图片列
            equiment.setPicture(null);
        }else {
            //如果图片不为空
            //则修改
            equiment.setPicture(StaticFilePath.Admin_Equipment_Add_PictureImgs + saveResult);
        }

        //int i = equimentService.editEquiment(equiment);
        if (equiment.getSuitId().trim().equals("")){
            equiment.setSuitId(null);
        }
        boolean b = mp_equimentService.updateById(equiment);
        if (!b){
            //添加失败
            Logger logger = LoggerFactory.getILoggerFactory().getLogger("AdminController.class");
            logger.error("添加Equiment失败：/Admin/Equiment/Update");

            return view;
        }

        view.setViewName("redirect:/Admin/Equiment/Update/"+id);
        //设置重定向
        return view;
    }

    @CheckUserStatusAnno
    @PostMapping("/Suit/Delete/{id}")
    @ResponseBody
    public AdminControllerEquimentDTO del_suit(@PathVariable int id){
        return adminHomeServices.handelSuit_DELETE(id);
    }

    @CheckUserStatusAnno
    @PostMapping("/Suit/Put")
    public @ResponseBody AdminControllerSuitDTO put_suit(HttpServletRequest request,
                                                         MultipartFile pictrue_img,
                                                         @Valid Suit suit,
                                                         BindingResult bindingResult,
                                                         String deleteImgs){

        AdminControllerSuitDTO result=new AdminControllerSuitDTO();
        //1.先进行删除
        if (deleteImgs!=null && !deleteImgs.trim().isEmpty()){
            //如果不为空就删除
            deleteImgs(deleteImgs,request);
        }

        Map<String, String> errorMaps=null;
        //如果数据类型有错误
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            errorMaps = ValidateUtils.FieldErrorList_To_Map(fieldErrors);
        }
        if (pictrue_img==null || pictrue_img.isEmpty()){
            errorMaps.put("picture","上传图片失败");
        }
        if (errorMaps.size()>0){
            AdminControllerSuitDTO.Error error = result.new Error();
            error.setErrorResult(errorMaps);
            result.setError(error);
            result.setStatus(0);
            return result;
        }

        //添加数据
        boolean save = suitService.save(suit);
        if (save){
            result.setStatus(1);
            result.setMessage("删除数据成功");
            return result;
        }else {
            result.setStatus(0);
            AdminControllerSuitDTO.Error error = result.new Error();
            error.setMessage("删除数据失败！");
            return result;
        }
    }

}
