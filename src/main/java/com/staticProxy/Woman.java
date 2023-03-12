package com.staticProxy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Woman implements Movie {
    private String name;

    @Override
    public void play() {
        System.out.println(name + "边看电影边玩手机");
    }

    @Override
    public boolean sleep(String name) {
        return false;
    }
}
