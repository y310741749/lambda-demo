package com;

public class TestVolatile implements Runnable {
    public volatile boolean single = true;

    @Override
    public void run() {
        while (single) {

        }
        System.out.println("执行完成...");
    }

    public static void main(String[] args) throws InterruptedException {
        TestVolatile t1 = new TestVolatile();
        new Thread(t1).start();
        Thread.sleep(1000);
        t1.single = false;
    }
}
