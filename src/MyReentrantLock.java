import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock{
    AtomicBoolean lock = new AtomicBoolean(false);
    Thread hasLock;

    //wait until free and acquire
    public void acquire(){
        Thread current = Thread.currentThread();
        boolean success = false;
        while(!success) {
            // Critical
            if(this.hasLock == current){return;}
            success = lock.compareAndSet(false, true); // if lock is free - lock it.
            if(!success){ // didn't get lock
                try {
                    Thread.sleep(10);
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
    //if success lock and true. else false
    public boolean tryAcquire(){
        boolean res = lock.compareAndSet(false,true);
        if(res == true){
            this.hasLock = Thread.currentThread();
        }
        return res;
    }


    // might throw exception
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
        if(this.lock.get()){release();}
        return;
    }
}
