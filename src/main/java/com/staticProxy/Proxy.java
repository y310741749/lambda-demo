package com.staticProxy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Proxy implements Movie {
    private Person person;

    @Override
    public void play() {
        System.out.println("电影马上开场");
        person.play();
        System.out.println("电影结束了");
    }

    @Override
    public boolean sleep(String name) {
        return false;
    }

    public static void main(String[] args) {
        Person person = new Person("张三");
        Proxy proxy = new Proxy(person);
        proxy.play();
    }
}
