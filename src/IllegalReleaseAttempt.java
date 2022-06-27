/**
 * this class represents an exception given if there is an illegal attempt to release a lock.
 */
public class IllegalReleaseAttempt extends IllegalMonitorStateException{
    public IllegalReleaseAttempt(){
        super();
    }
    public IllegalReleaseAttempt(String message){
        super(message);
    }
}
