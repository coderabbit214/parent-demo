package com.jsh.api;

import com.jsh.dao.ClassesRepository;
import com.jsh.dao.UserRepository;
import com.jsh.entity.Classes;
import com.jsh.entity.Person;
import com.jsh.entity.User;
import com.jsh.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController()
public class TestController {

    @Resource
    private PersonService personService;

    @Resource
    private ClassesRepository classesRepository;

    @Resource
    private UserRepository userRepository;

    @GetMapping("/test")
    public Page<Person> test(){
        return personService.getPageSort(1,5);
    }

    @GetMapping("/findByName")
    public Person findByName(@RequestParam("name") String name){
        return personService.findByName(name);
    }

    @GetMapping("/classes")
    public Classes getClasses(@RequestParam("id") Integer id){
        Classes classes = classesRepository.getOne(id);
        return classes;
    }

    @GetMapping("/classes/add")
    public void getClasses(){
        Classes classes = new Classes();
        classes.setClassName("1111");
        classesRepository.save(classes);

        User user = new User();
        user.setUsername("111");
        user.setClassesId(classes.getId());
        user.setPassword("asdf");
        User user1 = new User();
        user1.setUsername("222");
        user1.setClassesId(classes.getId());
        user1.setPassword("asdf");

        user.setClassesId(classes.getId());
        user1.setClassesId(classes.getId());

        userRepository.save(user);
        userRepository.save(user1);
    }
}
