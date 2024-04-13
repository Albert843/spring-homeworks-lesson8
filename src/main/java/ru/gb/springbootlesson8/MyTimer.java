package ru.gb.springbootlesson8;

import org.springframework.stereotype.Component;

@Component
@Timer
public class MyTimer {
    public void method() {
        System.out.println("Method is working");
    }
}
