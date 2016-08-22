/*
 * Class that creates a RequestServer.
 */
package cs1181.terrill.lab06;

/**
 * An object of this class can be run in a separate thread. The purpose of this
 * class is to process requests from the incoming request queue and then send
 * the processed request to the output queue. All the code that a thread
 * executes will be in the run method.
 */
public class RequestServer implements Runnable {

    private RequestQueue input;
    private RequestQueue output;
    private int serverID;

    /**
     * a constructor that takes two RequestQueue objects and an integer and
     * initialize the member variables with the provided parameter values.
     * @param inputQueue
     * @param outputQueue
     * @param identification
     */
    public RequestServer(RequestQueue inputQueue, RequestQueue outputQueue, int identification) {
        this.input = inputQueue;
        this.output = outputQueue;
        this.serverID = identification;
    }

    /**
     * Runs the process to RequestServer.
     * Precondition - the process has not been executed.
     * Postcondition - process is executed.
     */
    @Override
    public void run() {
        final int MAXEXCEPTIONS = 10;
        Delay delayObject = new Delay(294739 * serverID);
        String data;
        String prefix = "";
        // Keep track of how many time in a row we try to dequeue and get an exception
        // After MAXEXCEPTION times, we assume there are no more requests coming so we exit the loop
        int numExceptionsInARow = 0;
        // Keep track of how many requests we get from the input queue
        int numberOfRequestsServed = 0;
        // Create a string prefix to uniquely identify each instance of this class
        // The prefix will contain serverID number of tabs followed by a colon and a blank
        for (int i = 0; i < serverID; i++) {
            prefix += "\t";
        }
        prefix += serverID + ": ";
        // After MAXEXCEPTIONS attempts at dequeueing have failed in a row, we just quit - no more work coming
        while (numExceptionsInARow < MAXEXCEPTIONS) {
            // Wait for a while
            delayObject.randomDelay(250, 1000);
            try {
                // Try to read a string from the input queue
                data = input.dequeue();
                // If we got one, simulate the processing time by waiting a random amount - 100 msec min to 100 + range 500 msec max
                delayObject.randomDelay(100, 500);
                // Processing is done, so send to the output queue - our unique prefix plus the request string we processed
                output.enqueue(prefix + data);
                // Since we got data, set the number of exceptions in a row counter back to 0
                numExceptionsInARow = 0;
                // Keep track of how many requests we have handled
                numberOfRequestsServed++;
            } catch (Exception e) {
                // Our attempt to dequeue failed, so increment numExceptions in a row 
                ++numExceptionsInARow;
            }
        }
        // Report statistics for this thread.
        System.out.printf("RequestServer %d served %d requests - terminating!\n", serverID, numberOfRequestsServed);
    }
}
