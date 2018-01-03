package interceptors;

import entitys.UserdetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import repositorys.mySqlRepositorys.UserdetailRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserDetailInterceptor extends HandlerInterceptorAdapter {

    private UserdetailRepository userDetailRepository;

    @Autowired
    public void setUserDetailRepository(UserdetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof UserDetails){
            UserdetailEntity entity = userDetailRepository.findByUserid(((UserDetails) userDetails).getUsername());

            modelAndView.addObject("userdetail",entity);
        }
    }
}
