package quickstart.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class AbstractQueuedSynchronizer {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("我抢到锁了 哈哈我是 ：" + Thread.currentThread().getName());
            }
        };
        Thread threadA = new Thread(runnable, "Thread A");
        Thread threadB = new Thread(runnable, "Thread B");

        threadA.start();
        Thread.sleep(5);
        threadB.start();
        System.out.println("线程A状态: " + threadA.getState());
        System.out.println("线程B状态: " + threadB.getState() + "，线程A不释放 没办法 我只能死等了 ");
    }
}
