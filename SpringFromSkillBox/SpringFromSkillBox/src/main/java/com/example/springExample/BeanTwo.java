package com.example.springExample;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanTwo {

    public void hello () {
        System.out.println("say hello");
    }
}
