package com.jsh.dao;

import com.jsh.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mac
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    /**
     * 根据名称查询
     * @param name 查询名称
     * @return Person 信息
     */
    Person findByName(String name);
}