/*
 * This class puts together all the classes together into one process in order
 to run the simulation. 
 */
package cs1181.terrill.lab06;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class to run the simulation.
 * @author Clayton Terrill 
 * CS1181L-06 
 * Instructor: R. Volkers 
 * TA: Sai Polamarasetty
 */
public class CS1181TerrillLab06 {

    /**
     * Processes the output within the output queue by evaluating the string
     * contained in each dequeued value.
     * Precondition - The output queue is untouched and unused.
     * Postcondition - The output queue is processed and the number of 
     * exceptions in a row is found.
     * @param output - the output queue to be read from.
     */
    public static void processOutput(RequestQueue output) {
        final int MAXEXCEPTIONS = 10;

        Delay delayObject = new Delay(57369);
        String data;
        int numExceptionsInARow = 0;

        // We will count the number of exceptions in a row and
        // when we get to MAXEXCEPTIONS, we will assume there is
        // no more work to be done and exit the loop.
        while (numExceptionsInARow < MAXEXCEPTIONS) {
            try {
                // Try to get a string from the output queue
                data = output.dequeue();
                // If we get one, we will output it and set the
                // number of exceptions in a row back to 0
                System.out.println(data);
                numExceptionsInARow = 0;
            } catch (Exception e) {
                // Nothing in the queue, so count the exception
                numExceptionsInARow++;
                // Wait for a while so when we check the queue 
                // there is a better chance there will be some 
                // work to do.
                delayObject.specificDelay(1000);
            }
        }
        System.out.println("Output processing is done");
    }

    /**
     * The main class that runs the server simulation by putting several classes 
     * and methods together.
     * Precondition - The classes are mainly independent and unused.
     * Postcondition - The classes have been put together to create a simulation 
     * of a number of user defined requests going to a user defined number of
     * servers.
     * @param args - basic arguments.
     */
    public static void main(String[] args) {
        RequestQueue input = new MyRequestQueue();
        RequestQueue output = new MyRequestQueue();
        int requests = 0, servers = 0;

        /*
         * You need to create the RequestQueue objects specified above.
         * You also need to get user input to initialize the number of 
         * requests to be generated and the number of servers to process the requests
         *
         * NOTE: when the constructors have been added for the RequestServer and  
         * RequestGenerator classes, remove the //** from the beginning of the lines below.
         * Those 5 lines are commented out for now because they will generate compiler errors
         * until the class constructors have been added.       
         */
        
        //get input for number of requests and number of severs
        //Scanner to read user input
        Scanner keyboard = new Scanner(System.in);
        
        //loop to recieve and validate user input for Server Requests
        do {
            System.out.println("Please Enter The Number Of Server Requests: ");
            while (!keyboard.hasNextInt()) {
                System.out.println("Error: Please Enter A Positive Integer: ");
                keyboard.next(); // this is important!
            }
            requests = keyboard.nextInt();
        } while (requests <= 0);
        System.out.println("Number Of Servers: " + requests + "\n");
        
        //loop to recieve and validate user input for Servers
        do {
            System.out.println("Please Enter The Number Of Servers: ");
            while (!keyboard.hasNextInt()) {
                System.out.println("Error: Please Enter A Positive Integer: ");
                keyboard.next(); // this is important!
            }
            servers = keyboard.nextInt();
        } while (requests <= 0);
        System.out.println("Number Of Servers: " + servers + "\n");

        // The following code creates a pool of worker threads, one for 
        // each request server and one for the request generator
        ExecutorService executor = Executors.newFixedThreadPool(servers + 1);
        for (int i = 1;
                i <= servers;
                i++) {
            Runnable reqsrvr = new RequestServer(input, output, i);
            executor.execute(reqsrvr);
        }
        executor.execute(new RequestGenerator(input, requests));

        // Now that the system is up and running, we need to start processing
        // the output from the output queue. Call a function to handle that.
        processOutput(output);
        // Report on queue statistics - output the max lengths of the input and output queues
        System.out.printf("Queue Stats: Input - MaxLength = %d, Output - MaxLength = %d\n", input.getMaxLength(), output.getMaxLength());
        // Wait on all the threads to complete their work
        executor.shutdown();

        while (!executor.isTerminated()) {
        }
        System.out.println(
                "Finished all threads");
    }
}
