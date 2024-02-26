import java.util.Stack;
/*
    1- YAZAN AL SHAEBI      ID: 2142647
    2- MOHANAD AL DAKHEEL   ID: 2135847
    3- ABDULHAMID SAATI     ID: 2135877
 */
public class Kruskal {
    private int[] parent;
    private int[] rank;
    public static int calcTotalWeight(Stack<Edge> stack){
        int totalWeight=0;
        Stack <Edge> s = (Stack) stack.clone();
        while (!s.empty())
            totalWeight += s.pop().weight;
        return totalWeight;
    }
    public Kruskal(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    public void unionSet(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else {
            parent[yRoot] = xRoot;
            if (rank[xRoot] == rank[yRoot]) {
                rank[xRoot]++;
            }
        }
    }
}