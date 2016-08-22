/*
 * Class provided by Professor Volkers and appended by Clayton Terrill
to help run the simulation.
 */
package cs1181.terrill.lab06;

/**
 * The RequestGenerator class runs in a separate thread of execution. The code
 * that the thread executes must be inside the run method. The purpose of this
 * class is to place strings onto a RequestQueue at random times.
 * @author Clayton Terrill and rvolkers
 * CS1181L-06 
 * Instructor: R. Volkers 
 * TA: Sai Polamarasetty
 */
public class RequestGenerator implements Runnable {

    private RequestQueue output;
    private int numRequests;

    /**
     * Creates a constructor that accepts a RequestQueue object
     * and a number of requests as parameters.
     * Precondition - public variables are not initialized and object cannot be
     * created.
     * Postcondition - Object is created with the public variables being 
     * initialized with the values defined with the object.
     * @param queue - The queue to be used with the requests.
     * @param requests - The number of Requests to be implemented.
     */
    public RequestGenerator(RequestQueue queue, int requests) {
        this.output = queue;
        this.numRequests = requests;
    }

    /**
     * Creates a delayObject with a large seed and then makes the random delay
     * time occur, also adding the string to the queue.
     * Precondition - Queue has had no requests.
     * Postcondition - The requests numbers have been added to the Queue.
     */
    @Override
    public void run() {
        /*
         *  Create a Delay object with a large integer seed value (dont use the same seed elsewhere).
         *  You will need a loop that does the following 
         *       Use the delay object to wait for a random delay between 200 and 500 milliseconds - that is min of 200 + range of 300
         *       Place a string onto the request queue. The string should look like "Request # n" 
         *          where n is the request number.... first request is # 1, second request is # 2, etc
         *  The loop should stop when the specified number of requests have been enqueued.
         */
        int seed = 200;
        Delay delayObject = new Delay(999999);

        for (int i = 0; i < numRequests; i++) {
            delayObject.randomDelay(200, 300);
            output.enqueue("Request #" + i);
        }
    }
}
