import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * this class represents a lock.
 * implements lock.
 */
public class MyReentrantLock implements Lock{
    AtomicBoolean lock = new AtomicBoolean(false);
    Thread hasLock; //saves the current holder of the lock
    Thread releasingThread;

    /**
     * tries to acquire the lock. if not successful then sends thread to sleep to avoid busy waiting.
     */
    public void acquire(){
        Thread current = Thread.currentThread();
        boolean success = false;
        while(!success) {
            // Critical
            if(this.hasLock == current){return;} //already has the lock
            success = lock.compareAndSet(false, true); // if lock is free - lock it.
            if(!success){ // didn't get lock
                try {
                    Thread.sleep(18);
                }
                catch (InterruptedException e){ // Thread has been interrupted. Can keep running
                }
            }
            else{ //got lock
                this.hasLock = current;
                return;
            }
        }
    }

    /**
     * tries to acquire the lock.
     * @return if successful - true. if not - false
     */
    public boolean tryAcquire(){
        boolean res = lock.compareAndSet(false,true);
        if(res == true){
            this.hasLock = Thread.currentThread();
        }
        return res;
    }


    /**
     *tries to release the lock- only if the current thread has the lock.
     *
     *@throws IllegalReleaseAttempt - if the current thread doesn't have the lock.
     */
    public void release() {
        if (Thread.currentThread() == this.hasLock) {
            this.releasingThread = this.hasLock;
            this.hasLock = null;
            this.lock.setRelease(false);
        }
        else {
            throw new IllegalReleaseAttempt();
        }
    }

    /**
     *
     * making sure the lock was released by the current thread. if lock has been locked since then do nothing.
     * @throws IllegalReleaseAttempt - if close was reached when no one ever locked the lock.
     */
    @Override
    public void close(){
        if (this.releasingThread == null){ // no one released this lock.
            throw new IllegalReleaseAttempt();
        }
        if(this.hasLock == Thread.currentThread()) { // This thread has the lock. never released
            this.release();
            return;
        }
        return; //else to all conditions is that other Thread already locked the lock. all good.
    }
}
