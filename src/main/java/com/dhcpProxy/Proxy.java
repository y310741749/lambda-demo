package com.dhcpProxy;

import com.staticProxy.Movie;
import com.staticProxy.Person;
import com.staticProxy.Woman;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Getter
@Setter
@AllArgsConstructor
public class Proxy implements InvocationHandler {
    private Object person;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            System.out.println("进入代理方法");
            if(person instanceof Person){
                Person persons = (Person) person;
            System.out.println("personName:" + persons.getName());
            }else {
                Woman persons = (Woman) person;
                System.out.println("womenName:" + persons.getName());
            }
//
            return method.invoke(person, args);
        } finally {
            System.out.println("代理方法执行结束");
        }
    }

    public static void main(String[] args) {
        Person person = new Person("李四");
        Proxy proxy = new Proxy(person);
        Movie p = (Movie) java.lang.reflect.Proxy.newProxyInstance(Person.class.getClassLoader(), Person.class.getInterfaces(), proxy);
        p.sleep("王五");

        System.out.println("================");

        Woman woman = new Woman("赵六");
        Proxy proxy1 = new Proxy(woman);
        Movie p1 = (Movie) java.lang.reflect.Proxy.newProxyInstance(Person.class.getClassLoader(), Person.class.getInterfaces(), proxy1);
        p1.play();
        p1.sleep("赵六");
    }
}
