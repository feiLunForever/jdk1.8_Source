package quickstart.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutor {
    public static void main(String[] args) {
        java.util.concurrent.ThreadPoolExecutor threadPoolExecutor = new java.util.concurrent.ThreadPoolExecutor(
                8,
                8,
                200,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10000),
                new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy()
        );
        threadPoolExecutor.execute(() -> {
            System.out.println("sssssss");
        });
        System.out.println("Hello World");
    }
}
