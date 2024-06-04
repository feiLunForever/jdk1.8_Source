package quickstart.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocal {

    public static void main(String[] args) {
        SimpleDateFormatErrorTest test = new SimpleDateFormatErrorTest();
        test.test();
    }


    public static class SimpleDateFormatErrorTest {
        private final java.util.concurrent.ThreadPoolExecutor EXECUTOR = new java.util.concurrent.ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), Executors.defaultThreadFactory(), new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());
        private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public void test() {
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                EXECUTOR.execute(() -> {
                    System.out.println(simpleDateFormat.format(new Date(finalI * 1000)));
                });
            }
        }
    }

}
