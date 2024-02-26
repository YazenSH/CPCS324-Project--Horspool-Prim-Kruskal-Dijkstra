/*
    1- YAZAN AL SHAEBI      ID: 2142647
    2- MOHANAD AL DAKHEEL   ID: 2135847
    3- ABDULHAMID SAATI     ID: 2135877
 */
public class Graph //Class to implement graph
    {
        private Vertex vertexList[]; // Array of vertices as objects of class Vertex
        private int edges[][];

        public int numEdgesEntered;
// ---------------------------------------------------------------------------------------------------------------------
        public Graph(int numberOfVerts) // constructor
        {
            this.vertexList = new Vertex[numberOfVerts];
            vertexListInitialize();
            edges=new int[numberOfVerts][numberOfVerts];
        }
// ---------------------------------------------------------------------------------------------------------------------
        private void vertexListInitialize(){
            for (int i = 0; i <vertexList.length ; i++) {
                this.vertexList[i]=new Vertex(i);
            }
        }
// ---------------------------------------------------------------------------------------------------------------------
        public int[][] getEdgesArray() {
            return edges;
        }
// ---------------------------------------------------------------------------------------------------------------------
        public void setEdgePrims(int var1, int var2,int weight) { //undirected
            this.edges[var1][var2] = weight;
            this.edges[var2][var1] = weight;
        }
// ---------------------------------------------------------------------------------------------------------------------
        public void setEdgeDijikstra(int var1, int var2,int weight) { //directed
            this.edges[var1][var2] = weight;

}
        // ---------------------------------------------------------------------------------------------------------------------
        public int getNumberOfVerts() {
            return vertexList.length;
        }
// ---------------------------------------------------------------------------------------------------------------------
        public int getNumberOfEdges() {
            return  this.vertexList.length * (this.vertexList.length - 1) ; //directed graph calculation for num of edges
        }
// ---------------------------------------------------------------------------------------------------------------------
        public Vertex[] getVertexList() {
            return vertexList;
        }
// ---------------------------------------------------------------------------------------------------------------------
}


