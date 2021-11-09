package com.jsh.shiro.config;

import com.jsh.shiro.entity.User;
import com.jsh.shiro.service.IPermissionService;
import com.jsh.shiro.service.IRoleService;
import com.jsh.shiro.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

public class MyRealm extends AuthorizingRealm{

    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IPermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前的主体对象
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(user.getAdmin()){
            info.addRole("admin");
            info.addStringPermission("*:*");
            return info;
        }
        //获取当前用户的角色
        List<String> rolesnlist = roleService.getRoleSnByUserId(user.getId());
        //获取当前用户的权限
        List<String> permissionList = permissionService.getPermissionExpressionByUserId(user.getId());
        //给当前用户添加角色和权限（授权）
        info.addRoles(rolesnlist);
        info.addStringPermissions(permissionList);
        return info;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        User user =  userService.selectByUsername(usernamePasswordToken.getUsername());
        if(user == null){
            //用户不存在
            return null;
        }else{
            //第二个参数的密码，是数据库查询出结果的密码
            //第一个参数传递user，当认证成功后，走到授权方法时，授权方法将得到user对象做参数
            AuthenticationInfo info = new SimpleAuthenticationInfo(user ,user.getPassword(),ByteSource.Util.bytes(user.getName()) ,this.getName());
            return info;
        }

    }
}
