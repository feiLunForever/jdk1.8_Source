package quickstart.aqs;

public class ReentrantReadWriteLock {

    private final java.util.concurrent.locks.ReentrantReadWriteLock rwl = new java.util.concurrent.locks.ReentrantReadWriteLock();

    private final java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock readLock = rwl.readLock();

    private final java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock writeLock = rwl.writeLock();

    private volatile boolean update;

    // update变量使用volatile修饰
    public void processData() {
        readLock.lock();
        if (!update) {
            // 必须先释放读锁
            readLock.unlock();
            // 锁降级从写锁获取到开始
            writeLock.lock();
            try {
                if (!update) {
                    // 准备数据的流程(略)
                    update = true;
                }
                readLock.lock();
            } finally {
                writeLock.unlock();
            }
            // 锁降级完成，写锁降级为读锁
        }
        try {
            // 使用数据的流程(略)
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLock wr = new ReentrantReadWriteLock();
        wr.processData();
    }
}
