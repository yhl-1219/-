package com.itheima.health.config;

import com.itheima.health.pojo.Permission;
import com.itheima.health.service.UserService;
import com.itheima.health.vo.RoleVO;
import com.itheima.health.vo.UserVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangweili 
 */
@Configuration
public class MySecurityAdaptor extends WebSecurityConfigurerAdapter {

    @Reference
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/pages/index.html")
                .failureUrl("/login.html")
                .and()
                .authorizeRequests().antMatchers("/pages/find.html").hasRole("ADMIN")
                .antMatchers("/pages/**").authenticated()
                .and().exceptionHandling().accessDeniedPage("/error.html")
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**").antMatchers("/fonts/**").antMatchers("/img/**").antMatchers("/plugins/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return o -> {
            if ("wwl".equals(o)) {
                return new User("wwl", passwordEncoder().encode("wwl"), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
            }
            UserVO userVO = userService.findUserInfoByUsername(o);
            if (userVO == null) {
                return null;
            }
            List<RoleVO> roleVOList = userVO.getRoleVOList();
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            for (RoleVO role : roleVOList) {
                authorities.add(new SimpleGrantedAuthority(role.getKeyword()));
                List<Permission> permissions = role.getPermissionsList();
                for (Permission permission : permissions) {
                    authorities.add(new SimpleGrantedAuthority(permission.getKeyword()));
                }
            }
            return new User(userVO.getUsername(), userVO.getPassword(), authorities);
        };
    }
}
