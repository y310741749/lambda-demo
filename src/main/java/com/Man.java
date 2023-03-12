package com;

import lombok.ToString;

@ToString
public class Man {
    private Man man;

    public Man() {
        this.man=new Man();
    }

    public static void main(String[] args) {
        Man man = new Man();
        System.out.println(man);
    }
}
