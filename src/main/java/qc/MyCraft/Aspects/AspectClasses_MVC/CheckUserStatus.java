package qc.MyCraft.Aspects.AspectClasses_MVC;


import Common.Template;
import Common.ThreadLocalUtils;
import Common.UserManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import qc.MyCraft.Aspects.AspectsAnnotation.CheckUserStatusAnno;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
/**
 * 用在Controller上
 * 用于判断用户是否登录，如果未登录跳转到登录页面
 */
public class CheckUserStatus {


    @Around("execution(* qc.MyCraft.Controller.*.*(..))")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        //TODO 后门
        return  joinPoint.proceed();


//        Signature signature = joinPoint.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        Method method = methodSignature.getMethod();
//        CheckUserStatusAnno annotation = method.getAnnotation(CheckUserStatusAnno.class);
//        HttpServletRequest request = ThreadLocalUtils.getRequest();
//
//        //没有注解就pass  或者有用户状态
//        if (annotation == null || UserManager.getUserStatus(request.getSession())!=null){
//            return (ModelAndView) joinPoint.proceed();
//        }
//
//        ModelAndView view=Template.getTemplate("index","LoginAdmin","进入后台");
//        view.addObject("error_msg","没登录你访问你🐎呢？");
//        return view;
    }

}
