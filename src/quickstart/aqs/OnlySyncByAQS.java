package quickstart.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class OnlySyncByAQS {

    private Sync sync;

    public boolean lock () {
        return sync.tryAcquire(1);
    }

    public boolean unlock () {
        return sync.tryRelease(1);
    }


    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {

            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }
}
