package interceptors;

import entitys.UserEntity;
import entitys.UserdetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import repositorys.mySqlRepositorys.UserRespository;
import repositorys.mySqlRepositorys.UserdetailRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserDetailInterceptor extends HandlerInterceptorAdapter {

    private UserdetailRepository userDetailRepository;
    private UserRespository userRespository;

    @Autowired
    public void setUserDetailRepository(UserdetailRepository userDetailRepository,
                                        UserRespository userRespository) {
        this.userDetailRepository = userDetailRepository;
        this.userRespository=userRespository;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof UserDetails){
            UserdetailEntity entity = userDetailRepository.findByUserid(((UserDetails) userDetails).getUsername());
            if(entity==null){
                UserEntity userEntity = userRespository.findByUserid(((UserDetails) userDetails).getUsername());
                entity = new UserdetailEntity();
                entity.setUserid(userEntity.getUserid());
                entity.setUsername(userEntity.getName());
            }
            modelAndView.addObject("userdetail",entity);
        }
    }
}
