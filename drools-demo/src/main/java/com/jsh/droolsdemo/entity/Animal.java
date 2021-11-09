package com.jsh.droolsdemo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Animal {
    private List<Cat> cats;
}
