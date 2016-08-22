/*
 * Class provided by Professor Volkers to help run the simulation.
 */
package cs1181.terrill.lab06;

import java.util.Random;

/**
 * This class is used to create delays of specific
 * or random amounts of time.
 * 
 * @author rvolkers
 */
public class Delay 
{
    // Use an object of the Random class to
    // generate random numbers for the delay periods
    private Random rand;
    
    /**
     * The constructor takes a seed value so each Delay
     * object will create a Random object which generates
     * a different sequence of random values provided
     * the seed values are different.
     * 
     * @param seed should be a large integer value
     */
    public Delay(int seed)
    {
        rand = new Random(seed);
    }
    
    /**
     * Create a random delay based on the input parameters.
     * 
     * @param min specifies the minimum number of milliseconds to delay
     * @param range specifies the range for the random component of the delay
     */
    public void randomDelay(int min, int range)
    {
        // The delay time will be between min and min + range
        goToSleep(min + rand.nextInt(range));
    }
    
    /**
     * Delay the specified number of milliseconds
     * 
     * @param delay the number of milliseconds to delay
     */
    public void specificDelay(int delay)
    {
        goToSleep(delay);
    }
    
    /**
     * This method causes the calling thread to stop execution
     * for the specified number of milliseconds after which the
     * thread wakes up and continues execution
     * 
     * @param msecs the number of milliseconds the thread will sleep
     */
    private void goToSleep(int msecs)
    {
        try
        {
            Thread.sleep(msecs);
        }
        catch(InterruptedException e)
        {
            // Nothing to do here.... we really don't expect this to happen
        }
    }
}
