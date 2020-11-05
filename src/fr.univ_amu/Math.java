package fr.univ_amu;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Math {
    public static long[][] identityMatrix() {
        return new long[][]{{1, 0}, {0, 1}};
    }

    public static long[][] multipleMatrix(long[][] matrix1, long[][] matrix2, int n) {
        long[][] result = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static long[][] powerMatrix(long[][] matrix, int n) {
        int length = matrix[0].length;
        if (n == 0) {
            return identityMatrix();
        }

        long[][] result = powerMatrix(matrix, n / 2);
        if (n % 2 == 0) return multipleMatrix(result, result, length);
        else {
            result = multipleMatrix(result, result, length);
            result = multipleMatrix(result, matrix, length);
            return result;
        }
    }

    public static Collection<Integer> getMaxEmptyAreaNode(Graph graph) {
        return IntStream.range(0, graph.vertexCount())
                .filter(i -> graph.getEdges(i).isEmpty())
                .boxed()
                .collect(Collectors.toList());
    }

    public static boolean isEmptyAreaGraph(Graph graph, Collection<Integer> empty) {
        for (Integer integer : empty) {
            Set<Integer> neighbors = graph.getEdges(integer);
            Set<Integer> copySet = new HashSet<>(neighbors);
            copySet.removeAll(empty);
            if(copySet.size() != neighbors.size())
                return false;
        }
        return true;
    }

    public Collection<Integer> findVerticesWithOneOrLessEdge (Graph graph) {
        return IntStream.range(0, graph.vertexCount())
                .filter(i -> graph.getEdges(i).size() <= 1)
                .boxed()
                .collect(Collectors.toList());
    }

    public static Set<Integer> getOneMaximalEmptyZoneFromGraph(Graph graph) {
        ArrayList<Integer> verticesEmptyZone = new ArrayList<>();
        ArrayList<Integer> finalList = new ArrayList<>();
        for (int i = 0; i < graph.vertexCount(); i++) {
            verticesEmptyZone.add(i);
            finalList.add(i);
        }
        Collections.shuffle(verticesEmptyZone);
        for (Integer vertice : verticesEmptyZone) {
            if (!finalList.contains(vertice)) continue;
            Set<Integer> neighbors = graph.getEdges(vertice);
            finalList.removeAll(neighbors);
        }
        return new TreeSet<>(finalList);
    }

    /**
     * Exo 4
     *
     * @param graph
     * @return
     */
    public static Collection<Integer> getOneMaximumEmptyZoneFromGraph(Graph graph) {
        DeepFirstSearchIterator iterator = new DeepFirstSearchIterator(graph);
        Collection<Integer> zoneVide = new LinkedList<>();
        while (iterator.hasNext()) {
            int node = iterator.next();
            if (graph.getEdges(node).stream().anyMatch(zoneVide::contains)) // O(G.V + G.N)
                continue;
            zoneVide.add(node);
        }
        return zoneVide;
    }
}
