package com.jsh.shiro.controller;

import com.github.pagehelper.PageInfo;
import com.jsh.shiro.entity.Role;
import com.jsh.shiro.entity.User;
import com.jsh.shiro.entity.UserRole;
import com.jsh.shiro.result.JSonResult;
import com.jsh.shiro.service.IPermissionService;
import com.jsh.shiro.service.IRoleService;
import com.jsh.shiro.service.IUserRoleService;
import com.jsh.shiro.service.IUserService;
import com.jsh.shiro.util.QueryObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

    @Resource
    private IPermissionService permissionService;

    @RequestMapping("/list")
    @RequiresPermissions(value = {"user:list", "用户列表"}, logical = Logical.OR)
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) {

        PageInfo<User> pageInfo = userService.list(qo);
        model.addAttribute("result", pageInfo);
        return "user/list";
    }

    @RequestMapping("/input")
    public String input(Long id, Model model) {

        List<Role> roles = roleService.selectAll();
        model.addAttribute("roleList", roles);
        if (id == null) {
            //新增
        } else {
            //编辑
            User user = userService.selectByPrimaryKey(id);
            model.addAttribute("currentUser", user);
            List<Role> roleList = roleService.selectByUserId(id);
            model.addAttribute("selfRoles", roleList);
        }
        return "user/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(User user, Long[] ids) {
        if (user.getId() == null) {
            userService.insert(user);
            Long userid = user.getId();
            if (ids != null) {
                for (Long id : ids) {
                    userRoleService.insert(new UserRole(userid, id));
                }
            }

        } else {
            userService.updateByPrimaryKey(user);
            userRoleService.deleteByUserId(user.getId());
            if (ids != null) {
                for (Long id : ids) {
                    userRoleService.insert(new UserRole(user.getId(), id));
                }
            }
        }
        return "redirect:/user/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSonResult delete(Long id) {
        userService.deleteByPrimaryKey(id);
        userRoleService.deleteByUserId(id);
        return JSonResult.success(null);
    }

    @RequestMapping("/batchdelete")
    @ResponseBody
    public JSonResult batchdelete(Long[] ids) {
        userService.batchdelete(ids);
        userRoleService.batchDeleteByUserIds(ids);
        return JSonResult.success(null);
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public Map checkName(String username) {
        Map<String, Boolean> map = new HashMap<>();
        User user = userService.selectByUsername(username);
        if (user == null) {
            map.put("valid", true);
        } else {
            map.put("valid", false);
        }
        return map;
    }

}
