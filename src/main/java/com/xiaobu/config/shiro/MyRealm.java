package com.xiaobu.config.shiro;

import com.xiaobu.base.util.MD5Util;
import com.xiaobu.entity.Resource;
import com.xiaobu.entity.Role;
import com.xiaobu.entity.User;
import com.xiaobu.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaobu
 * @version 1.0
 * @date 2018/11/30 17:06
 * @descprition
 */
@Component
public class MyRealm extends AuthorizingRealm {

    public MyRealm() {
        super(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(UsernamePasswordToken.class);
        //FIXME: 启用Cache
        setCachingEnabled(true);
    }

    @Autowired
    private UserService userService;


    /**
     * @author xiaobu
     * @date 2018/12/18 15:31
     * @param token token
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @descprition   身份认证
     * @version 1.0
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = null;
        try {
            user = userService.findByUserName(username);
        } catch (Exception e) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        String password = new String((char[]) token.getCredentials());
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        if (!MD5Util.getMD5Pwd(username, password).equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        // 账号锁定
        if (user.getLocked() == 1) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        return new SimpleAuthenticationInfo(user, password, getName());
    }


    /**
     * @author xiaobu
     * @date 2018/12/18 15:34
     * @param principals  principals
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @descprition   授权
     * @version 1.0
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User dbUser = userService.findByUserName(user.getUserName());
        Set<String> shiroPermissions = new HashSet<>();
        Set<String> roleSet = new HashSet<String>();
        Set<Role> roles = dbUser.getRoles();
        for (Role role : roles) {
            Set<Resource> resources = role.getResources();
            for (Resource resource : resources) {
                shiroPermissions.add(resource.getSourceKey());

            }
            roleSet.add(role.getRoleKey());
        }
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(shiroPermissions);
        return authorizationInfo;
    }



}
