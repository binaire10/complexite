package fr.univ_amu;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;

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
