package fr.univ_amu;

import java.util.*;

public class Graph {
    private final List<Set<Integer>> edge;

    public Graph(int vertexCount) {
        edge = new ArrayList<>(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            edge.add(new TreeSet<>());
        }
    }

    public void addEdge(int a, int b) {
        edge.get(a).add(b);
        edge.get(b).add(a);
    }

    public Set<Integer> getEdges(int a) {
        return Collections.unmodifiableSet(edge.get(a));
    }

    public int vertexCount() {
        return edge.size();
    }

    public Graph complement() {
        Graph graph = new Graph(edge.size());
        return graph;
    }
}
