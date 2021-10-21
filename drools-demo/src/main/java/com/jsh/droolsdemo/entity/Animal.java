package com.jsh.droolsdemo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Animal {
    private List<Cat> cats;
}
