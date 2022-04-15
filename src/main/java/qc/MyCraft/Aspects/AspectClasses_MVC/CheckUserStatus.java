package qc.MyCraft.Aspects.AspectClasses_MVC;


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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        CheckUserStatusAnno annotation = method.getAnnotation(CheckUserStatusAnno.class);
        //没有注解就pass
        if (annotation == null){
            return joinPoint.proceed();
        }

        //从线程工具中拿到request
        HttpServletResponse response = ThreadLocalUtils.getResponse();
        Object userStatus = UserManager.getUserStatus(ThreadLocalUtils.getSession());
        if (userStatus!=null){
            try {
                response.sendRedirect("/loginAdmin");
                response.flushBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
