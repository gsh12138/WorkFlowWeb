package interceptors;

import entitys.UserEntity;
import helpers.ActiveNav;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import repositorys.mySqlRepositorys.UserRespository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tech on 2018/1/2.
 */
@Component
public class MessageInterceptor extends HandlerInterceptorAdapter {

    private UserRespository userRespository;



    public MessageInterceptor(){

    }

    @Autowired
    public void setUserRespository(UserRespository userRespository) {
        this.userRespository = userRespository;
    }



    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserEntity entity = userRespository.findByUserid("TS0808");
        modelAndView.addObject("bumen",entity.getDepartment());
        HandlerMethod method = (HandlerMethod)handler;
        ActiveNav activeNav = new ActiveNav(method);
        modelAndView.addObject("active",activeNav);


    }
}
