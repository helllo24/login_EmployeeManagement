
package login.service;

import login.entity.LoginEntity;
import login.repository.LoginRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginRepository repo;

    public CustomUserDetailsService(LoginRepository repo) {
        this.repo = repo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginEntity user = repo.findByusername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(user.getRoles()));


        return new User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

}

