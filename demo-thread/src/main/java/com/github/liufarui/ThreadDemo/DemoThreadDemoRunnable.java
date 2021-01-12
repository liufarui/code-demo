package com.github.liufarui.ThreadDemo;

public class DemoThreadDemoRunnable implements Runnable {
    public void run() {
        System.out.println("我执行了一次！");
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new DemoThreadDemoRunnable());
            thread.start();
            Thread.sleep(3000);
        }
    }
}
