package com.event;

public class MyListener implements Listener {
    private Thread thread;
    private volatile boolean perform;

    public MyListener() {
        perform = true;
        doTask();
    }

    public void doTask() {
        thread = new Thread(() -> {
            while (perform) {
                try {
                    Thread.sleep(1000);
                    System.out.println("hahaha");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void handle(MyEvent myEvent) {
        MySource mySource = (MySource) myEvent.getObject();
        System.out.println("监听到" + mySource.getClass() + "发生改变");
        this.perform = false;
    }
}
