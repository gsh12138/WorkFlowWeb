package configs;

import entitys.UserEntity;
import entitys.UserdetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import repositorys.mySqlRepositorys.UserRespository;
import repositorys.mySqlRepositorys.UserdetailRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private UserRespository userRespository;
    private UserdetailRepository userDetailRepository;

    @Autowired
    public MyUserDetailsService(UserRespository userRespository, UserdetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
        this.userRespository = userRespository;
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final UserEntity userEntity = userRespository.findByUserid(s);

        final UserdetailEntity userdetailEntity = userDetailRepository.findByUserid(s);

        if (userEntity != null && userdetailEntity != null) {
            final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            String[] authoritys = userdetailEntity.getRoles().split(",");
            for (String authority : authoritys
                    ) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+authority));
            }
            UserDetails userDetails =new UserDetails() {
                public Collection<? extends GrantedAuthority> getAuthorities() {

                    return authorities;
                }

                public String getPassword() {

                    return userEntity.getPassword();
                }

                public String getUsername() {
                    return userEntity.getUserid();
                }

                public boolean isAccountNonExpired() {
                    if (userdetailEntity.getAccountexpiredate() == null) {
                        return true;
                    }
                    Date today = new Date();
                    if (userdetailEntity.getAccountexpiredate().before(today)) {
                        return false;
                    }
                    return true;
                }

                public boolean isAccountNonLocked() {

                    return true;
                }

                public boolean isCredentialsNonExpired() {
                    return true;
                }

                public boolean isEnabled() {
                    return userdetailEntity.getEnabled();

                }


            };

            return userDetails;
        }


        throw new UsernameNotFoundException("user:"+userEntity.getUserid()+" not found");
    }
}

