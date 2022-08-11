/**
 * ====================================================================
 * Created by nima.yoezer on 23-Sep-18.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
package com.ngn.spring.project.security;


import com.ngn.spring.project.auth.LoginDTO;
import com.ngn.spring.project.auth.LoginService;
import com.ngn.spring.project.auth.UserRoleDTO;
import com.ngn.spring.project.global.enu.LoginError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    //region private variable
    private PasswordEncoder passwordEncoder;
    String rolecheck="";
    @Autowired
    private LoginService loginService;
    //endregion

    //region setter
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    //endregion

    //region public method

    /**
     * It processes authentication information
     *
     * @param authentication authentication
     * @return Authentication
     * @throws org.springframework.security.core.AuthenticationException
     */

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

        String username = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());

        LoginDTO userLogin = loginService.login(username);
        if (userLogin == null) {
            throw new UsernameNotFoundException("Wrong username or password.");
        }
        String replace = userLogin.getPassword().replaceAll("2y", "2a");

        if (userLogin.getStatus() == 0) {
            throw new LockedException(LoginError.LOCKED.getCode());
        }
      /*  if(userLogin.getPassword().length() > 40 && BCrypt.checkpw(password, replace))
        {
            Set authorities = getAccessRight(userLogin.getUserId());
            return new UsernamePasswordAuthenticationToken(userLogin, password, authorities);
        } else
        {
            throw new LockedException("Incorrect Password!");
        }*/
        Set authorities = getAccessRight(userLogin.getUserId());
        return new UsernamePasswordAuthenticationToken(userLogin, password, authorities);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UsernamePasswordAuthenticationToken.class);
    }

    /**
     * It supplies authorization information according to user group
     *
     * @param userId --    String
     * @return Set
     */
    private Set<GrantedAuthority> getAccessRight(String userId) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        List<UserRoleDTO> userRoleDTOs = loginService.getUserRoleList(userId);
        if(userRoleDTOs.size()>0){
            for(int i=0;i<userRoleDTOs.size();i++){
                if(userRoleDTOs.get(i).getRoleId().equals(14)){
                    rolecheck="admin";
                    authorities.add(new SimpleGrantedAuthority("DAMC"));
                }/*else if(userRoleDTOs.get(i).getRoleName().contains("Contractor")){
                    rolecheck="public";
                    authorities.add(new SimpleGrantedAuthority("ROLE_CONTRACTOR"));
                }*/
            }
        } else{
            rolecheck="public";
            authorities.add(new SimpleGrantedAuthority("ROLE_PUBLIC"));
        }
        return authorities;
    }
    //endregion
}
