package qc.MyCraft.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import qc.MyCraft.Models.BaseModels.Suit;
import qc.MyCraft.Models.ControllerDTO.AdminControllerEquimentDTO;
import qc.MyCraft.Models.FunctionModels.ModulesModels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminHomeServices {

    @Autowired
    qc.MyCraft.Service.Suit SuitServices;
    @Autowired
    qc.MyCraft.Service.EType eTypeSercice;
    @Autowired
    qc.MyCraft.Service.Equiment equimentService;


    public HashMap<String, String> getAdminModules() {
        return ModulesModels.module_urls;
    }

    public void handelEquiment_ADD(ModelAndView modelAndView) {
        List<Suit> suits = SuitServices.list();
        modelAndView.addObject("etype_list", eTypeSercice.getAllEtype());
        modelAndView.addObject("suit_list", suits);
    }

    public void handelUpdateEquiment_GET(ModelAndView modelAndView, Integer id) {

        Map<String, String> page_error_map = new HashMap<>();
        if (id == null || id == 0) {
            page_error_map.put("id", "id不符合规范！");
        }
        modelAndView.addObject("etype_list", eTypeSercice.getAllEtype());
        modelAndView.addObject("equipment", equimentService.getEquimentById(id));
        //查询全部的suit套装信息
        List<Suit> suits = SuitServices.list();
        modelAndView.addObject("suit_list",suits);
    }


    public AdminControllerEquimentDTO handelSuit_DELETE(Integer id) {
        AdminControllerEquimentDTO result = new AdminControllerEquimentDTO();
        Suit byId = SuitServices.getById(id.toString());
        if (byId == null){
            result.setState(0);
            return result;
        }

        boolean b = SuitServices.removeById(id);
        if (b){
            //释放图片资源
            SuitServices.release_deleted_resource(byId);
            //equimentService.release_deleted_resource(equimentById);
        }
        result.setState(1);
        return result;
    }


}
