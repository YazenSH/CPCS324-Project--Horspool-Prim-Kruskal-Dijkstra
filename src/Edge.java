/*
    1- YAZAN AL SHAEBI      ID: 2142647
    2- MOHANAD AL DAKHEEL   ID: 2135847
    3- ABDULHAMID SAATI     ID: 2135877
 */
public class Edge implements Comparable <Edge> {
   public int ver1, ver2, weight;
    public Edge(int ver1, int ver2, int weight) {
        this.ver1 = ver1;
        this.ver2 = ver2;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.getWeight() - o.getWeight();
    }
}
