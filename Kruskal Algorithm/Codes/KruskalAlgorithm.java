import java.util.Comparator;
import java.util.List;

public class KruskalAlgorithm {
    public Edge[] kruskal(int v, List<Edge> edges) {
        int i = 0;
        int numberOfEdges = 0;
        Subset[] subsets = new Subset[v];
        Edge[] result = new Edge[v];
        for (int j = 0; j < v; j++)
            subsets[j] = new Subset(j, 0);

        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        while (numberOfEdges < v-1) {
            Edge nextEdge = edges.get(i);
            int x = findRoot(subsets, nextEdge.source);
            int y = findRoot(subsets, nextEdge.destination);
            if (x != y) {
                result[numberOfEdges++] = nextEdge;
                union(subsets, x, y);
            }
            i++;
        }
        return result;
    }

    private int findRoot(Subset[] subsets, int i) {
        if (subsets[i].parent == i)
            return subsets[i].parent;
        subsets[i].parent = findRoot(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    private void union(Subset[] subsets, int x, int y) {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);
        if (subsets[rootX].rank > subsets[rootY].rank)
            subsets[rootY].parent = rootX;
        else if (subsets[rootX].rank < subsets[rootY].rank)
            subsets[rootX].parent = rootY;
        else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }
}
