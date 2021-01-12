package com.github.liufarui.ThreadDemo;

public class DemoThreadDemoThread extends Thread {
    public void run() {
        System.out.println("我执行了一次！");
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread thread = new DemoThreadDemoThread();
            thread.start();
            Thread.sleep(3000);
        }
    }
}
