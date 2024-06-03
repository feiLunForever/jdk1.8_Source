package quickstart.aqs;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Semaphore {
    public static void main(String[] args) {
        java.util.concurrent.ThreadPoolExecutor threadPoolExecutor = new java.util.concurrent.ThreadPoolExecutor(
                4,
                4,
                200,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10000),
                new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy()
        );


        java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(3);
        for (int i = 0; i < 12; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    if (semaphore.availablePermits() == 0) {
                        System.out.println("分配的3个许可证用完了，调用acquire会阻塞等待直到有许可证");
                    }
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "【消费】了一个许可，当前可用许可证数量为：" + semaphore.availablePermits());
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "【释放】了一个许可，当前可用许可证数量为：" + semaphore.availablePermits());
                    System.out.println();
                }
            });
        }
    }
}
