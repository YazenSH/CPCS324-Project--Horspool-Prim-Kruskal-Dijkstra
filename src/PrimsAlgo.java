/*
    1- YAZAN AL SHAEBI      ID: 2142647
    2- MOHANAD AL DAKHEEL   ID: 2135847
    3- ABDULHAMID SAATI     ID: 2135877
 */
public class PrimsAlgo {

    public static Edge[] findMST(Graph graph, String method){
        Edge[] edges;
        if(method.equalsIgnoreCase("Heap")){

            MinPriorityQueue queueHeap = new minPriorityQueueHeap(graph.getNumberOfEdges());
            edges= MinHeap(graph , queueHeap);
        }
        else {
            MinPriorityQueue queueArray = new minPriorityQueueUnorderedArray(graph.getNumberOfEdges());
            edges= MinHeap(graph , queueArray);
        }

    return edges;
    }
    private static Edge[] MinHeap(Graph graph, MinPriorityQueue queue) {
        graph.getVertexList()[0].wasVisited = true;
        int NumberOfVers = graph.getNumberOfVerts() - 1;
        int counter = 0;
        Edge edges[] = new Edge[NumberOfVers];
        for (int i = 0; i < graph.getNumberOfVerts(); i++) {
            if (graph.getEdgesArray()[0][i] != 0)
                queue.insert(new Edge(0, i, graph.getEdgesArray()[0][i]));
        }
        while (counter != NumberOfVers) {
            Edge min = queue.extractMin();
            if (graph.getVertexList()[min.ver2].wasVisited) {
                continue;
            }
            graph.getVertexList()[min.ver2].wasVisited = true;
            edges[counter++] = min;
            for (int i = 0; i < graph.getNumberOfVerts(); i++) {
                    if (graph.getEdgesArray()[min.ver2][i] != 0)
                        queue.insert(new Edge(min.ver2, i, graph.getEdgesArray()[min.ver2][i]));
            }

        }
        return edges;
    }

}
