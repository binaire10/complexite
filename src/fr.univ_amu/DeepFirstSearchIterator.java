package fr.univ_amu;

import java.util.*;

public class DeepFirstSearchIterator implements Iterator<Integer> {
    private final boolean[] visited;
    private final Graph graph;
    private final Queue<Integer> frontier;

    public DeepFirstSearchIterator(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.vertexCount()];
        frontier = Collections.asLifoQueue(new ArrayDeque<>());
        frontier.add(0);
        visited[0] = true;
        explore(0);
    }

    public DeepFirstSearchIterator(Graph graph, Collection<Integer> frontier) {
        this.graph = graph;
        visited = new boolean[graph.vertexCount()];
        this.frontier = Collections.asLifoQueue(new ArrayDeque<>());
        for (int i : this.frontier)
            visited[i] = true;
    }

    @Override
    public boolean hasNext() {
        return !frontier.isEmpty();
    }

    public void explore(int node) {
        for (int neighbour : graph.getEdges(node))
            if (!visited[neighbour]) {
                visited[neighbour] = true;
                frontier.add(neighbour);
                explore(neighbour);
            }
    }

    @Override
    public Integer next() {
        int vertex = frontier.poll();
        for (int i = 0; i < visited.length; i++)
            if (!visited[i]) {
                frontier.add(i);
                visited[i] = true;
                explore(i);
                break;
            }
        return vertex;
    }
}
