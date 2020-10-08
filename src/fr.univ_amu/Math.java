package fr.univ_amu;

import java.util.Collection;
import java.util.Set;
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
                .filter(i -> graph.getEdge(i).isEmpty())
                .boxed()
                .collect(Collectors.toList());
    }

    public boolean isEmptyAreaGraph(Graph graph, Collection<Integer> empty) {
        return empty.stream()
                .map(graph::getEdge)
                .allMatch(Set::isEmpty);
    }

}
