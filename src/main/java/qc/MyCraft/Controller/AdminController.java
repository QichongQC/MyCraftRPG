package qc.MyCraft.Controller;

import Common.Search.EquimentSearchModel;
import Common.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import qc.MyCraft.Aspects.AspectsAnnotation.CheckUserStatusAnno;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    qc.MyCraft.Service.ETypeImpl eTypeService;

    @Autowired
    qc.MyCraft.Service.EquimentImp equimentService;

    @CheckUserStatusAnno
    @RequestMapping("/Home")
    public ModelAndView Home(EquimentSearchModel equimentSearchModel){
//设置子页面
        ModelAndView view = Template.getTemplate("index", "AdminHome", "装备图鉴");
        view.addObject("etype_list",eTypeService.getAllEtype());
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
        //设置子页面
    }
}
