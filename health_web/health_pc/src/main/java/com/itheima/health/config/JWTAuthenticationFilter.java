package com.itheima.health.config;

import com.alibaba.fastjson.JSONObject;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.User;
import com.itheima.health.utils.jwt.JwtUtils;
import com.itheima.health.utils.jwt.UserInfo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.Collection;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法 ,
 * attemptAuthentication：接收并解析用户凭证。
 * successfulAuthentication：用户成功登录后，这个方法会被调用，我们在这个方法里生成token并返回。
 * 
 * @author wangweili 
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JwtProperties properties;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtProperties properties) {
        this.properties = properties;
        setAuthenticationManager(authenticationManager);
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/login");
        System.out.println("filter===" + authenticationManager + "---");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        // 从输入流中获取到登录的信息
        try {
            JSONObject jsonObject = new JSONObject();  //   前端 发送 json 对象  username  password
            User loginUser = JSONObject.parseObject(request.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword())
            );
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Result info = new Result();
                info.setStatus(500);
                info.setMessage("账号或密码认证失败");
                response.setContentType("text/json;charset=utf-8");
                String s = JSONObject.toJSONString(info);
                response.getWriter().print(s);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    public void successfulAuthentication(HttpServletRequest request,
                                         HttpServletResponse response,
                                         FilterChain chain,
                                         Authentication authResult) throws IOException, ServletException {
        //  认证成功 我们需要将用户信息 生成token   发送给客户端
        org.springframework.security.core.userdetails.User jwtUser = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        Collection<GrantedAuthority> authorities = jwtUser.getAuthorities();
        GrantedAuthority[] objects = authorities.toArray(new GrantedAuthority[]{});
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < authorities.size(); i++) {
            String authority = objects[i].getAuthority();
            if (i == authorities.size() - 1) {
                sb.append(authority);
            } else {
                sb.append(authority + "-");
            }
        }
        //  开始对用户进行 加密  生成token
        UserInfo userInfo = new UserInfo();
        userInfo.setRoles(sb.toString());
        userInfo.setUsername(jwtUser.getUsername());

        PrivateKey privateKey = properties.getPrivateKey();

        String token = null;
        response.setContentType("application/json; charset=utf-8");
        Result errorInfoDTO = new Result();
        try {
            //  使用私钥  加密 生成 token  返回给浏览器 以header形式发送
            token = JwtUtils.generateToken(userInfo, privateKey, 60 * 24);
            String tokenStr = JwtUtils.TOKEN_PREFIX + token;
            response.setHeader(JwtUtils.TOKEN_NAME, tokenStr); //  header形式 发送给客户端浏览器
            errorInfoDTO.setFlag(true);
            errorInfoDTO.setStatus(200);
            errorInfoDTO.setMessage(tokenStr);
        } catch (Exception e) {
            e.printStackTrace();
            errorInfoDTO.setFlag(false);
            errorInfoDTO.setStatus(500);
            errorInfoDTO.setMessage("token生成失败");
        }
        String s = JSONObject.toJSONString(errorInfoDTO);
        response.getWriter().print(s);
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(403);
        response.setContentType("application/json; charset=utf-8");
        if (failed instanceof BadCredentialsException) {
            response.getWriter().write("authentication failed, reason: 密码错误");
        } else {
            response.getWriter().write("authentication failed, reason: " + failed.getMessage());
        }
    }
}

