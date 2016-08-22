/*
 * This queue class is implemented using my own 
 * methods for linking nodes together in a linked data structure.
 * A Node class is defined within this class .
 * Member variables are present  to maintain
 * the queue and all the methods of the
 * RequestQueue interface are implemented.
 * This creates a LinkedList Queue.
 */
package cs1181.terrill.lab06;

/**
 * Creates a Queue to use.
 * @author Clayton Terrill 
 * CS1181L-06 
 * Instructor: R. Volkers 
 * TA: Sai Polamarasetty
 */
public class MyRequestQueue implements RequestQueue {

    /**
     * Creates a node containing a string to be used.
     * Precondition - node not present.
     * Postcondition - node is now present.
     */
    private class Node {
        public String payload;//the string to be added to node
        public Node next;//goes to next connected node

        public Node(String payload) {
            this.payload = payload;
        }
    }

    private int nodes;//number of nodes
    private Node front;//creates Node to point to front
    private Node back;//creates Node to point to back
    private int maximumSize = 0;//makes the initial max zero 

    /**
     * Constructor to create a Queue and initialize nodes within. 
     * Precondition - A MyRequestQueue object may not be made. 
     * Postcondition - A MyRequestQueue object may now be made.
     */
    public MyRequestQueue() {
        front = null;
        back = null;
    }

    /**
     * Adds a node containing the input string to the Queue. 
     * Precondition - no nodes have been added. 
     * Postcondition - Any amount of nodes containing string
     * may now be added to the cue.
     * @param input - the string for the node to hold within it.
     */
    @Override
    public void enqueue(String input) {
        if (nodes == 0) {
            front = new MyRequestQueue.Node(input);
            back = front;
        } else {
            Node newNode = new Node(input);
            back.next = newNode;
            back = back.next;
        }
        nodes++;
        maximumSize = getMaxLength();
    }

    /**
     * Removes a node containing the input string from the front of the
     * Queue and returns that input string. 
     * Precondition - no nodes have been removed and no string returned. 
     * Postcondition - The first node of the Queue is removed and the string
     * within it is returned.
     * @return - returnString is the string within the node to return.
     * @throws Exception - throws an Exception if Queue is empty.
     */
    @Override
    public String dequeue() throws Exception {
        String returnString;
        if (nodes == 0) {
            throw new Exception("Queue Empty");
        }
        returnString = front.payload;
        front = front.next;
        nodes--;
        if (nodes == 0) {
            back = null;
        }
        return returnString;
    }

    /**
     * Determines the max length that the Queue has reached and returns an 
     * integer value that represents this length.
     * Precondition - max length that the queue has achieved is undetermined.
     * Postcondition - max length that the queue has achieved is determined and
     * returned as an integer.
     * @return maximumSize - the maximum length that the queue has reached.
     */
    @Override
    public int getMaxLength() {
        if (maximumSize < nodes) {
            maximumSize = nodes;
        }
        return maximumSize;
    }

    /**
     * A toString method that overrides the default toString to display the
     * Queue values on separate lines, FOR TESTING PURPOSES. 
     * Precondition: the values within the list are not displayed. 
     * Postcondition: allows the list to be displayed in an ordered fashion.
     * @return - the string containing the lists contents.
     */
    @Override
    public String toString() {
        //initiates the String list
        String list = "";
        Node current = front; //the default first node to be used
        //while loop to loop all the way through the nodes
        while (current != null) {
            list = list + current.payload + "\n";//adds the current value to list
            current = current.next;
        }
        return "List: " + "\n" + list;
    }
}
