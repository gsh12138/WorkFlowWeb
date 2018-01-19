package helpers;

import entitys.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import repositorys.mySqlRepositorys.UserRespository;

@Component
public class UserHelper {

    private static UserRespository respository;

    @Autowired
    public void setRespository(UserRespository respository) {
        UserHelper.respository = respository;
    }

    public static UserEntity currentUser(){
        Object userdetil = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userdetil instanceof UserDetails){
            UserEntity userEntity = respository.findByUserid(((UserDetails) userdetil).getUsername());
            return userEntity;
        }
        else {
            return null;
        }
    }
}
