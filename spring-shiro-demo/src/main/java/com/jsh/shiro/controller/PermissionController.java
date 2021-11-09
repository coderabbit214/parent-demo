package com.jsh.shiro.controller;

import com.github.pagehelper.PageInfo;
import com.jsh.shiro.entity.Permission;
import com.jsh.shiro.result.JSonResult;
import com.jsh.shiro.service.IPermissionService;
import com.jsh.shiro.util.QueryObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model){
        PageInfo<Permission> pageInfo =  permissionService.list(qo);
        model.addAttribute("result", pageInfo);
        return "permission/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Permission permission){
        if(permission.getId() == null){
            permissionService.insert(permission);
        }else{
            permissionService.updateByPrimaryKey(permission);
        }
        return "redirect:/permission/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSonResult delete(Long id){
        permissionService.deleteByPrimaryKey(id);
        return JSonResult.success(null);
    }


    @RequestMapping("/reload")
    @ResponseBody
    public JSonResult reload(){
        //获取数据库中所有的permission
        List<Permission> permissions = permissionService.selectAll();
        Map<String, String> permap = new HashMap<>();
        for (Permission permission: permissions) {
            permap.put(permission.getName(), permission.getExpression());
        }

        Map<String, Object> map = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> controllers = map.values();
        for (Object controller : controllers) {
            Class<?> clzz = controller.getClass().getSuperclass();
            Method[] methods = clzz.getDeclaredMethods();
            for (Method method : methods) {
                RequiresPermissions annotation = method.getAnnotation(RequiresPermissions.class);
                if(annotation!=null){
                    String name = annotation.value()[1];
                    String expression = annotation.value()[0];
                    //数据库中没有这个权限
                    if(permap.get(name)==null){
                        permissionService.insert(new Permission(null, name, expression));
                    }
                }
            }
        }
        return JSonResult.success(null);
    }
}
