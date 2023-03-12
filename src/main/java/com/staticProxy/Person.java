package com.staticProxy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person implements Movie{
    private String name;

    @Override
    public void play() {
        System.out.println(name+"正在看电影");
    }

    @Override
    public boolean sleep(String name) {
        System.out.println(name+"正在睡觉");
        return true;
    }


}
