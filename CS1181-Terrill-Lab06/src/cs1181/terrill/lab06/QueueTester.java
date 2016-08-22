/*
 * Test class to test the MyRequestQueue class in order to determine whether or
not the MyRequestQueue works correctly.
 */
package cs1181.terrill.lab06;

/**
 * Tests the MyRequestQueue class.
 * @author Clayton Terrill 
 * CS1181L-06 
 * Instructor: R. Volkers 
 * TA: Sai Polamarasetty
 */
public class QueueTester {

    /**
     * Main class to test the MyRequestQueue class. Precondition -
     * MyRequestQueue class is untested. Postcondition- The MyRequestQueue class
     * has been tested and determined whether or not it functions properly.
     * @param args - default arguments.
     * @throws Exception - throws Exception if error occurs.
     */
    public static void main(String args[]) throws Exception {

        // Create an object of your queue class here and add all the test 
        // cases needed to make sure that your queue is working properly!
        //Queue works properly as tested below.
        MyRequestQueue queue = new MyRequestQueue();//object to be tested.
        queue.enqueue("1");//adds a node containing "1" to the queue.
        queue.enqueue("2");//adds a node containing "2" to the queue.
        queue.enqueue("3");//adds a node containing "3" to the queue.
        queue.enqueue("4");//adds a node containing "4" to the queue.

        //Removes and returns "1" from the queue.
        System.out.println(queue.dequeue());//displays 1. If so then enqueue works.
        //Removes and returns "2" from the queue.
        System.out.println(queue.dequeue());//displays 2. If so then dequeue works.
        //Removes and returns "3" from the queue.
        System.out.println(queue.dequeue());//displays 3.
        //Removes and returns "4" from the queue.
        System.out.println(queue.dequeue());//displays 4.
        System.out.println("\nThe maximum length of the queue after first 4 nodes are deleted: ");
        System.out.println(queue.getMaxLength());//should display 4.

        //enqueue and dequeue have been proven to work.
        //Below adds 5 new nodes to the queue.
        queue.enqueue("0");//adds a node containing "1" to the queue.
        queue.enqueue("1");//adds a node containing "2" to the queue.
        queue.enqueue("2");//adds a node containing "3" to the queue.
        queue.enqueue("3");//adds a node containing "4" to the queue.
        queue.enqueue("4");//adds a node containing "5" to the queue.
        System.out.println("");

        //Should be 5. If 5 is displayed then MaxLength() works.
        System.out.println("The maximum length of the queue after 5 new nodes are added: ");
        System.out.println(queue.getMaxLength());
        //getMaxLength() has been proven to work.
        System.out.println("");
        //System.out.println(queue.toString());
    }
}
