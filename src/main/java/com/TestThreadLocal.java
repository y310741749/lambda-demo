package com;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TestThreadLocal {
    static final ThreadLocal<AAA> tl = new ThreadLocal<AAA>() ;
    static void print(AAA man){
        System.out.println(man);
    }

    public static void main(String[] args) throws InterruptedException {
        AAA man=new AAA("张三");
        tl.set(man);
        System.out.println(tl.get());
        Thread.sleep(1000);
        new Thread(()->{
            man.setName("里斯");
            tl.set(man);
            System.out.println(tl.get());
        }).start();
        Thread.sleep(1000);
        new Thread(()->{
            System.out.println(tl.get());
        }).start();
        System.out.println(tl.get());
    }


}
@ToString
@Getter
@Setter
@AllArgsConstructor
class AAA{
    private String name;
}