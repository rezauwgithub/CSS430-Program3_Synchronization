
import java.util.Vector;

 
// The SyncQueue uses the QueueNode.
// It stores each thread inside of the queue and 
// wakes it up or puts it to sleep.
public class QueueNode {
    
    private Vector<Integer> queue;

	
	// Instantiates the queue (Vector).
    public QueueNode() { 
	
		queue = new Vector<>(); 
	}


	
	// puts the thread to sleep using wait()
    public synchronized int sleep( ) {
		
        if(queue.size() == 0) {
			
            try {
                wait( );

            } catch ( InterruptedException e ) {   
                SysLib.cerr(e.toString() + "\n");
            }
			
            return queue.remove(0);
        }
		
		
        return -1;
    }

	
    // wakes up a thread by enqueuing it into the queue.
    public synchronized void wake(int tid){
		
        queue.add(tid);
		
        notify();
    }
}
