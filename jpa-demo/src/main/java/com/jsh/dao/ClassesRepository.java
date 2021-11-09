package com.jsh.dao;

import com.jsh.entity.Classes;
import com.jsh.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mac
 */
public interface ClassesRepository extends JpaRepository<Classes, Integer> {

}