
public class SyncQueue {
	
	private static int DEFAULT_COND = 10;	
    private static int DEFAULT_TID = 0;
	
	// maintains an array of QueueNode objects, each representing 
	// a different condition and enqueuing all threads that wait for
	// this condition.
    private QueueNode[] queue;


    // is a constructor that creates a queue and allows threads to wait
	// for a default condition number (=10).
    public SyncQueue() {
		
        queue = new QueueNode[DEFAULT_COND];
        for(int i = 0; i < DEFAULT_COND; i++) {
            queue[i] = new QueueNode();

        }
    }

    // is a constructor that creates a queue and allows threads to wait
	// for condMax number of condition/event types. 
    public SyncQueue(int condMax) {
		
        queue = new QueueNode[condMax];
		
        for(int i = 0; i < condMax; i++) {
            queue[i] = new QueueNode();
        }
    }


	// allows a calling thread to sleep until a given
	// condition is satisfied.
    public int enqueueAndSleep(int condition) {
		
        if(condition < queue.length) {
			
            if(condition >= 0) {
                return queue[condition].sleep();
				
            }
        }
		
		
        return -1;
    }


	// dequeues and wakes up a thread waiting for a given condition.
	// If there are two or more threads waiting for the same condition,
	// only one thread is dequeued and resumed.
	// The FCFS (first-come-first-service) order does not matter.
    public void dequeueAndWakeup(int condition) {
		
        if(condition >= 0) {
			
            if(condition < queue.length) {
                queue[condition].wake(DEFAULT_TID);
            }
        }
    }


	// dequeues and wakes up a thread waiting for a given condition.
	// If there are two or more threads waiting for the same condition,
	// only one thread is dequeued and resumed.
	// The FCFS (first-come-first-service) order does not matter.
	// This function can receive the calling thread's ID,
	// (tid) as the 2nd argument. This tid will be passed to the thread
	// that has been woken up from enqueueAndSleep.
	// If no 2nd argument is given, you may regard tid as 0.
    public void dequeueAndWakeup(int condition, int tid) {
		
        if(condition >= 0) {
			
            if(condition < queue.length) {
                queue[condition].wake(tid);
            }
        }
    }
}
