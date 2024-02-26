/*
    1- YAZAN AL SHAEBI      ID: 2142647
    2- MOHANAD AL DAKHEEL   ID: 2135847
    3- ABDULHAMID SAATI     ID: 2135877
 */
public class minPriorityQueueUnorderedArray extends MinPriorityQueue {

    Edge edges[];
    int maxSize;
    int counter;
    public minPriorityQueueUnorderedArray(int size) {
        edges=new Edge[size];
        counter=0;
        maxSize = size;
    }
    public boolean isEmpty(){
        return 0 == counter;
}
    public boolean isFull(){
        return counter == maxSize;
}
    public void insert(Edge edge) {
        if (isFull())
            System.out.println("Error. Array of edges is full");
        else
        edges[counter++]=edge;
    }
    public Edge extractMin(){
        if (!isEmpty()) {
            int minEdgeWeight = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < counter; i++) {
                if (edges[i].weight < minEdgeWeight) {
                    minEdgeWeight = edges[i].weight;
                    index = i;
                }
            }
            Edge smallestEdge = edges[index];
            edges[index] = edges[--counter];// to remove the chosen edge and rearrange the queue
            return smallestEdge;
        }
        else{
            System.out.println("Error. Array is empty, null returned");
            return null;}
    }

}