package com.jsh.service;

import com.jsh.entity.Person;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonService {
    //根据信息搜索用户
    List<Person> getAllByOther(Example example);

    // 普通分页
    Page<Person> getPage(Integer pageNum, Integer pageLimit);

    // 排序分页
    Page<Person> getPageSort(Integer pageNum, Integer pageLimit);

    //保存用户
    void savaPerson(Person person);

    Person findByName(String name);
}
