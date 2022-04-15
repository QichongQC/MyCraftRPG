package qc.MyCraft.Controller;

import Common.MD5;
import Common.Search.EquimentSearchModel;
import Common.Template;
import Common.UserManager;
import org.apache.ibatis.io.Resources;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import qc.MyCraft.Models.BaseModels.Equiment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Controller("/")
public class HomeController {

    @Autowired
    qc.MyCraft.Service.ETypeImpl eTypeService;

    @Autowired
    qc.MyCraft.Service.EquimentImp equimentService;

    @RequestMapping("/")
    public ModelAndView Home(EquimentSearchModel equimentSearchModel){
        //设置子页面
        ModelAndView view = Template.getTemplate("index", "equipmen", "装备");
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
    }

    @RequestMapping("/equiment_detail/{id}")
    public ModelAndView Home(@PathVariable Integer id){
        System.out.println(id);
        ModelAndView view = Template.getTemplate("index", "equiment_detail", "装备详情");
        Equiment equiment = equimentService.getEquimentById(id);
        view.addObject("equiment",equiment);
        view.addObject("etype",eTypeService.getById(equiment.getEtype()));

        return view;
    }

    @GetMapping("/loginAdmin")
    public ModelAndView Home(HttpServletRequest request){
        ModelAndView mav=Template.getTemplate("index","LoginAdmin","进入后台♂");
        String errorMsg_key="page_error";
        if(request.getSession().getAttribute(errorMsg_key)!=null){
            String page_error = request.getSession().getAttribute(errorMsg_key).toString();
            request.getSession().removeAttribute(errorMsg_key);
            mav.addObject("error_msg",page_error);
        }
        //设置子页面
        return mav;
    }

    @PostMapping("/loginAdmin")
    public void Home(String password, HttpServletRequest request, HttpServletResponse res) throws IOException, ServletException {

        Properties properties=new Properties();
        File resourceAsFile = Resources.getResourceAsFile("Configs.properties");
        properties.load(new FileInputStream(resourceAsFile));
        Object admin = properties.get("admin")==null?"":properties.get("admin").toString();

        if (MD5.encryptToMD5(password).equals(admin)){
            request.getSession().removeAttribute("page_error");

            //登录成功
            UserManager.setUserManger(request.getSession());
            res.sendRedirect("/Admin/Home");
        }else{
            request.getSession().setAttribute("page_error","密码错误");
            res.sendRedirect("/loginAdmin");
        }
    }
}
