import java.util.concurrent.atomic.AtomicBoolean;

/**
 * this class represents a lock.
 * implements lock.
 */
public class MyReentrantLock implements Lock{
    AtomicBoolean lock = new AtomicBoolean(false);
    Thread hasLock; //saves the current holder of the lock
    int counter = 0;

    /**
     * tries to acquire the lock. if not successful then sends thread to sleep to avoid busy waiting.
     */
    public void acquire(){
        Thread current = Thread.currentThread();
        boolean success = false;
        while(!success) {
            // Critical
            if(this.hasLock == current){//already has the lock
                counter++;
                return;
            }
            success = lock.compareAndSet(false, true); // if lock is free - lock it.
            if(!success){ // didn't get lock
                try {
                    Thread.sleep(18);
                }
                catch (InterruptedException e){ // Thread has been interrupted. Can keep running
                }
            }
            else{ //got lock
                this.counter++;
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
        Thread current = Thread.currentThread();
        if(hasLock == current){ // Thread reentering.
            counter++;
            return true;
        }
        boolean res = lock.compareAndSet(false,true);
        if(res == true){
            counter++;
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
            counter--;
            if (counter == 0) { // final release of the lock.
                this.hasLock = null;
                this.lock.setRelease(false);
            }
        }
        else {
            throw new IllegalReleaseAttempt();
        }
    }

    /**
     * release the lock using the func release()
     * @throws IllegalReleaseAttempt - in case of illegal attempt to release the lock.
     */
    @Override
    public void close() {
        this.release();
    }
}
