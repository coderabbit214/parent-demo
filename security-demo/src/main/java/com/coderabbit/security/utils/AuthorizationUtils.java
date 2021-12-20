package com.coderabbit.security.utils;


import com.coderabbit.security.constant.LoginConstant;
import com.coderabbit.security.dao.AuthRepository;
import com.coderabbit.security.dao.RoleAuthRepository;
import com.coderabbit.security.dao.RoleRepository;
import com.coderabbit.security.model.AuthInfo;
import com.coderabbit.security.model.RoleAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AuthorizationUtils {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleAuthRepository roleAuthRepository;

    @Autowired
    private AuthRepository authRepository;
    /**
     * 获取拥有url的角色集合
     * @param url url
     * @return
     */
    public List<String> listRoles(String url){
        //auth->id
        AuthInfo auth = authRepository.findByUrlAndStatus(url, LoginConstant.AUTH_USED);
        //role_auth->roleId
        if (Objects.nonNull(auth)){
            List<RoleAuth> roleAuths = roleAuthRepository.findByAuthIdAndStatus(auth.getId(), LoginConstant.ROLE_AUTH_USED);
            if (!CollectionUtils.isEmpty(roleAuths)){
                List<String> roles=new ArrayList<>();
                //role->roles
                roleAuths.forEach(param-> roleRepository.findById(param.getRoleId()).ifPresent(o->roles.add(o.getName())));
                return roles;
            }
        }
        return null;
    }
}
