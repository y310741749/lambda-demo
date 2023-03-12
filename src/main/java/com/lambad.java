package com;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static com.lambad.Status.BUSY;

public class lambad {
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> integers = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        new TreeSet<>(Integer::compare);
    }

    @Test
    public void test2() {
        Collections.sort(Arrays.asList("1", "2"));
    }

    @Test
    public void test3() {
        String s = "abcde";
        Fun1<String> fun1 = x -> {
            x = x.toUpperCase();
            return x.substring(1, 2);
        };
        System.out.println(getValue(s, fun1));
    }

    private String getValue(String s, Fun1<String> fun1) {
        return fun1.getT(s);
    }

    @Test
    public void test4() {
        System.out.println(getValue2(1, 2, (x, y) -> (long) x * y));
    }

    private long getValue2(int i, int i1, Fun2<Integer, Long> fun2) {
        return fun2.jisuan(i, i1);
    }

    @Test
    public void test5() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("123");
    }

    @Test
    public void test6() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        integers.stream()
                .filter(e -> {
                    System.out.println("hahaha");
                    return e > 3;
                })
                .skip(4)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test7() {
        List<String> list = Arrays.asList("abc", "def");
        list.stream().map(p -> p.substring(0, 1)).forEach(System.out::println);
//        List<List<String>> collect = list.stream().map(p -> {
//                    List<String> strs = new ArrayList<>();
//                    for (int i = 0; i < p.length(); i++) {
//                        strs.add(p.charAt(i) + "");
//                    }
//                    return strs;
//                }
//        ).collect(Collectors.toList());
//        collect.forEach(p -> p.forEach(System.out::println));
        String s = "123";
        char[] chars = s.toCharArray();
        Stream.of(chars).forEach(System.out::println);
        list.stream().flatMap(p -> {
            List<String> strs = new ArrayList<>();
            for (int i = 0; i < p.length(); i++) {
                strs.add(p.charAt(i) + "");
            }
            return strs.stream();
        }).forEach(System.out::println);
    }

    @Test
    public void Test8() {
//        System.out.println(Status.BUSY.equals("BUSY"));
        String s = "123";
        Status value = Status.getValue(s);
        switch (value) {
            case BUSY:
                break;
        }
    }

    public enum Status {
        FREE("a"),
        VICTOR("b"),
        BUSY("c");

        private String value;

        Status(String value) {
            this.value = value;
        }

        public static Status getValue(String value) {
            return Stream.of(Status.values()).filter(p -> p.value.equals("a")).findAny().orElse(null);
        }
        //        private Integer code;
//
//        Status(Integer code) {
//            this.code = code;
//        }
    }

    @Test
    public void test9() {
//        List<People> list = Arrays.asList(new People("a", 18), new People("b", 19), new People("c", 20), new People("a", 20));
//        boolean b = list.stream().noneMatch(p -> p.getAge() > 20);
//        System.out.println(a);
    }


    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public class People {
        private String name;
        private Integer age;
        private double salary;

        public People(String name) {
            this.name = name;
        }
    }

    @Test
    public void test10() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(integers.stream().reduce(0, (x, y) -> x * y));

        System.out.println(integers.stream().collect(Collectors.averagingInt(Integer::intValue)));

        System.out.println((Integer) integers.stream().mapToInt(Integer::intValue).sum());

        integers.stream().max(Integer::compareTo);
    }

    @Test
    public void test11() {
        List<People> people = Arrays.asList(new People("aaa", 18, 5555), new People("bbb", 18, 6666), new People("ccc", 19, 5555), new People("ddd", 20, 7777), new People("eee", 20, 8888));
        Map<Integer, Map<Double, List<People>>> collect = people.stream().collect(Collectors.groupingBy(People::getAge, Collectors.groupingBy(People::getSalary)));
        System.out.println(collect);
    }

    @Test
    public void test12() {
        List<People> people = Arrays.asList(new People("aaa", 18, 5555), new People("bbb", 18, 6666), new People("ccc", 19, 5555), new People("ddd", 20, 7777), new People("eee", 20, 8888));
        IntSummaryStatistics collect = people.stream().collect(Collectors.summarizingInt(p -> (int) p.getSalary()));
        people.stream().max(Comparator.comparingDouble(People::getSalary));
    }

    @Test
    public void test13() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream()
                .map(x -> Math.pow(x, 2))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    public void test14() {
        List<Object> objects = Arrays.asList(1, new People(), 2, new People(), new People());
        long count = objects.stream()
                .filter(p -> p instanceof People)
                .count();
        System.out.println(count);
    }

    @Test
    public void test15() {
        Instant now = Instant.now();
        LongStream.rangeClosed(0, 100000000000L)
//                  .parallel()
                .sum();
        Instant now1 = Instant.now();
        System.out.println(Duration.between(now, now1).toMillis());
    }

    @Test
    public void Test16() {
        Optional<People> people = Optional.ofNullable(null);
        people.orElseGet(People::new);
    }

    @Test
    public void Test17() {
        Optional<List<People>> people = Optional.of(Arrays.asList(new People("张三"), new People("李四"), new People("王五")));
    }

    @Test
    public void Test18() {
        Man man = new Man();
        getName(man);
    }

    private void getName(Man man) {
        Optional.of(man)
                .ifPresent(m -> Optional.of(m.getWoman())
                        .ifPresent(w -> Optional.of(w).map(Woman::getName)));

        Optional<Woman> woman = Optional.of(man).map(p -> p.getWoman());

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    class Man {
        private Woman woman;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    class Woman {
        private String name;
    }

    @Test
    public void test19() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    sdf.parse("20220301");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    @Test
    public void test20() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(dtf.parse("20220301"))).start();
        }
    }

    @Test
    public void test21() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusDays(1);
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd HHmmss")));
    }

    @Test
    public void test22() throws InterruptedException {
        Instant instant = Instant.now();
        Thread.sleep(1000);
        Instant instant1 = Instant.now();
        Duration duration = Duration.between(instant, instant1);
        System.out.println(duration.toMillis());
    }

    @Test
    public void test23(){
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.of(2022, 12, 21));
        Period between = Period.between(LocalDate.now(), LocalDate.of(2022, 12, 21));
        System.out.println(between.getDays());
    }

    @Test
    public void test24(){
        LocalDateTime ldt=LocalDateTime.now();
        DayOfWeek dayOfWeek = ldt.getDayOfWeek();
        System.out.println(dayOfWeek.getClass());
    }
}
