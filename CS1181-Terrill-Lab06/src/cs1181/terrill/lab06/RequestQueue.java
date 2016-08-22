/*
 * Interface to act as a guideline for the MyRequestQueue class.
 */
package cs1181.terrill.lab06;

/**
 * Interface to act as a guideline for the MyRequestQueue class.
 * @author rvolkers
 */
public interface RequestQueue 
{
    /**
     * This method is used to add a String to the end of the queue
     * @param input the string object to be queued
     */
    void enqueue(String input);  
    /**
     * This method returns the String object from the front of the queue
     * deleting that object from the queue
     * @return the string from the front of the queue
     * @throws Exception if the queue is empty
     */
    String dequeue() throws Exception;
    /**
     * This method reports the maximum length the queue has reached
     * @return the maximum number of items in the queue since it was created
     */
    int getMaxLength();
}
