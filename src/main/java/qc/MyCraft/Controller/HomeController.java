package qc.MyCraft.Controller;

import Common.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class HomeController {

    @RequestMapping("/")
    public ModelAndView Home(){
        ModelAndView view = Template.getTemplate("index", "equipmen", "武器大全");

        return view;
    }
}
