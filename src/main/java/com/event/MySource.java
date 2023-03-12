package com.event;

public class MySource {
    private Listener listener;

    public void registerListener(Listener listener) {
        this.listener = listener;
    }

    public void notiy() {
        System.out.println("mySource发生改变");
        listener.handle(new MyEvent(this, "mySource"));
    }

    public static void main(String[] args) throws InterruptedException {
        MySource mySource = new MySource();
        mySource.registerListener(new MyListener());
        Thread.sleep(5000);
        mySource.notiy();
    }
}
