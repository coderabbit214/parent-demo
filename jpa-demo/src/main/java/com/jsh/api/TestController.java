package com.jsh.api;

import com.jsh.entity.Person;
import com.jsh.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController()
public class TestController {

    @Resource
    private PersonService personService;

    @GetMapping("/test")
    public Page<Person> test(){
        return personService.getPageSort(1,5);
    }

    @GetMapping("/findByName")
    public Person findByName(@RequestParam("name") String name){
        return personService.findByName(name);
    }
}
