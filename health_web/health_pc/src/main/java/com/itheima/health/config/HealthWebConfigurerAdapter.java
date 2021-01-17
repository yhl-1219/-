package com.itheima.health.config;

import com.itheima.health.pojo.Permission;
import com.itheima.health.service.UserService;
import com.itheima.health.vo.RoleVO;
import com.itheima.health.vo.UserVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangweili 
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(JwtProperties.class)
public class HealthWebConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtProperties properties;

    @Reference
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(createPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return o -> {
            UserVO user = userService.findUserInfoByUsername(o);
            ArrayList<GrantedAuthority> userList = new ArrayList<>();
            List<RoleVO> roles = user.getRoleVOList();
            for (RoleVO role : roles) {
                userList.add(new SimpleGrantedAuthority(role.getKeyword()));
                List<Permission> permissions = role.getPermissionsList();
                for (Permission permission : permissions) {
                    userList.add(new SimpleGrantedAuthority(permission.getKeyword()));
                }

            };
            return new User(user.getUsername(), user.getPassword(), userList);
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                formLogin().
                loginProcessingUrl("/login").
                and().
                csrf().
                disable().
                addFilter(new JwtAuthenticationFilter(super.authenticationManager(), properties))
                .addFilter(new JwtAuthorizationFilter(super.authenticationManager(), properties))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
