package com.able;

public class VolatileExample {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        for(;;) {
            if (myThread.isFlag()) {
                System.out.println("主线程访问到 flag 变量");
            }
        }
    }
}

class MyThread extends Thread {
    // volatile 实现可见性 (如果没有该关键字，主线程和子线程会从内存中复制副本执行，因此主进程无法进入println)
    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag = " + flag);
        super.run();
    }

    public boolean isFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
