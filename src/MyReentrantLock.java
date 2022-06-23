import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock{
    AtomicBoolean lock = new AtomicBoolean(false);
    ArrayDeque<Thread> waitingQueue = new ArrayDeque<>();

    //wait until free and acquire
    public void acquire(){
        Thread current = Thread.currentThread();
        boolean success = false;
        while(!success) {
            // Critical
            success = lock.compareAndSet(false, true); // if lock is free - lock it.
            if(!success){
                try {
                    this.waitingQueue.add(current);
                    current.wait();
                }
                catch (InterruptedException e){ // Thread has been interrupted. Can keep running
                }
            }
        }
    }
    //if success lock and true. else false
    public boolean tryAcquire(){
        return lock.compareAndSet(false,true);
    }
    // might throw exception
    public void release(){
        this.lock.setRelease(false);
        notifyAll();
        if(false){
            throw new IllegalReleaseAttempt();
        }
    }
    public void close(){
        release();
    }
}
