import java.util.Scanner;
/*
    1- YAZAN AL SHAEBI      ID: 2142647
    2- MOHANAD AL DAKHEEL   ID: 2135847
    3- ABDULHAMID SAATI     ID: 2135877
 */
public class DijkstraAlgo {
    //all infinity but source
    // first ver 0 and explored
    // update est
    // next ver unexplored shortest
    public static int[] Dijkstra(Graph graph, String method,int start){
        int[] distances;
        if(method.equalsIgnoreCase("Heap")){
            MinPriorityQueue queueHeap = new minPriorityQueueHeap(graph.getNumberOfEdges());
            distances = DijkstraImp(graph , queueHeap,start);
        }
        else {
            MinPriorityQueue queueArray = new minPriorityQueueUnorderedArray(graph.getNumberOfEdges());
            distances = DijkstraImp(graph , queueArray,start);
        }

        return distances;
    }

private static int[]  DijkstraImp(Graph graph, MinPriorityQueue queue, int start){
    int starter = start;
    graph.getVertexList()[starter].wasVisited = true;
    int NumberOfVers = graph.getNumberOfVerts() ;
    int counter = 1;
    int [] distances = new int[NumberOfVers];

    for (int i = 0; i < distances.length; i++) {
        distances[i] = Integer.MAX_VALUE;
    }

    distances[starter] = 0;
    graph.getVertexList()[starter].dijPrev += starter;

    while ( counter != NumberOfVers) {
        for (int i = 0; i < graph.getEdgesArray().length; i++) {
            if (graph.getEdgesArray()[starter][i] > 0 && !graph.getVertexList()[i].wasVisited) {
                if((graph.getEdgesArray()[starter][i] + distances[starter])< distances[i]){
                    distances[i] = graph.getEdgesArray()[starter][i] + distances[starter];
                    queue.insert(new Edge(starter, i, + distances[i]));
                }
            }
        }

        boolean flag = true;
        while(flag) {

            Edge edge = queue.extractMin();
            if (graph.getVertexList()[edge.ver2].wasVisited) {
                continue;
            }
            graph.getVertexList()[edge.ver2].dijPrev =graph.getVertexList()[edge.ver1].dijPrev +" "+edge.ver2;
            graph.getVertexList()[edge.ver2].wasVisited = true;
            counter++;
            starter=edge.ver2;
            flag=false;

        }


    }
return distances;}



}
