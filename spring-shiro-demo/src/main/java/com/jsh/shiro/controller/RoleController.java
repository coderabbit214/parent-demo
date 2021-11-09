package com.jsh.shiro.controller;

import com.github.pagehelper.PageInfo;
import com.jsh.shiro.entity.Permission;
import com.jsh.shiro.entity.Role;
import com.jsh.shiro.entity.RolePermission;
import com.jsh.shiro.result.JSonResult;
import com.jsh.shiro.service.IPermissionService;
import com.jsh.shiro.service.IRolePermissionService;
import com.jsh.shiro.service.IRoleService;
import com.jsh.shiro.util.QueryObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @Resource
    private IPermissionService permissionService;

    @Resource
    private IRolePermissionService rolePermissionService;


    @RequestMapping("/list")
    public String RolePageInfo(@ModelAttribute("queryObject") QueryObject queryObject, Model model){
        PageInfo<Role> pageInfo = roleService.list(queryObject);
        model.addAttribute("result",pageInfo);

        return "role/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role Role, Long[] ids){
        Long roleId = Role.getId();
        if (Role.getId() == null) {
            roleService.insert(Role);

            if (ids !=null) {
                for (Long id : ids) {
                    rolePermissionService.insert(new RolePermission(roleId,id));
                }
            }
        } else {
            roleService.updateByPrimaryKey(Role);

            //删除原来的
            rolePermissionService.deleteByRoleId(roleId);

            //加入现在的
            if (ids !=null) {
                for (Long id : ids) {
                    rolePermissionService.insert(new RolePermission(roleId,id));
                }
            }
        }

        return "redirect:/role/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSonResult delete(Long id){
        roleService.deleteByPrimaryKey(id);
        return JSonResult.success(null);
    }

    @RequestMapping("/input")
    public String input(Long id,Model model){
        List<Permission> permissions = permissionService.selectAll();
        model.addAttribute("permissions",permissions);
        if (id == null) {
            //新增
        } else {
            Role role = roleService.selectByPrimaryKey(id);
            model.addAttribute("role",role);

            List<Permission> permissions1 = permissionService.selectByRoleId(id);

            model.addAttribute("permissions1",permissions1);
        }
        return "role/input";
    }
}
