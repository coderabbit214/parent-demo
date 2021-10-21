package com.jsh.service.impl;

import com.jsh.dao.PersonRepository;
import com.jsh.entity.Person;
import com.jsh.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> getAllByOther(Example example) {
        List<Person> persons = personRepository.findAll(example);
        return persons;
    }

    @Override
    public Page<Person> getPage(Integer pageNum, Integer pageLimit) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit);
        return personRepository.findAll(pageable);
    }

    @Override
    public Page<Person> getPageSort(Integer pageNum, Integer pageLimit) {
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, sort);
        return personRepository.findAll(pageable);
    }

    @Override
    public void savaPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person findByName(String name) {
        return personRepository.findByName(name);
    }
}
