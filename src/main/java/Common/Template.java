package Common;

import org.springframework.web.servlet.ModelAndView;

public class Template {

    public static ModelAndView getTemplate(String fatherName,String sonName,String title){
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", title);
        mav.addObject("mainPage", sonName);
        mav.setViewName(fatherName);
        return mav;
    }
}
