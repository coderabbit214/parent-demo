package com.jsh.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author mac
 */
@Entity
@Getter
@Setter
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = true, length = 20)
    private String name;

    @Column(name = "age", nullable = true, length = 4)
    private int age;
}