import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock{
    AtomicBoolean lock = new AtomicBoolean(false);
    Thread hasLock; //saves the current holder of the lock


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
     *
     */
    public void release() {
        if (Thread.currentThread() == this.hasLock) {
            this.hasLock = null;
            this.lock.setRelease(false);
        }
        else {
            throw new IllegalReleaseAttempt();
        }
    }

    @Override
    public void close(){
        if(this.hasLock == Thread.currentThread()) {
            if (this.lock.compareAndSet(true, false)) {
            }
        }
        return;
    }
}
