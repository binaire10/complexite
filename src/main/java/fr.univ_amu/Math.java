package fr.univ_amu;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Math {
    public static BigInteger[][] identityMatrix() {
        return new BigInteger[][]{{BigInteger.ONE, BigInteger.ZERO}, {BigInteger.ZERO, BigInteger.ONE}};
    }

    public static BigInteger[][] multipleMatrix(BigInteger[][] matrix1, BigInteger[][] matrix2, int n) {
        BigInteger[][] result = new BigInteger[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = BigInteger.ZERO;
                for (int k = 0; k < n; k++) {
                    result[i][j] = result[i][j].add(matrix1[i][k].multiply(matrix2[k][j]));
                }
            }
        }
        return result;
    }

    public static BigInteger[][] powerMatrix(BigInteger[][] matrix, int n) {
        int length = matrix[0].length;
        BigInteger[][] result = identityMatrix();
        while (n != 0) {
            if ((n & 1) == 1) result = multipleMatrix(result, matrix, length);
            n /= 2;
            matrix = multipleMatrix(matrix, matrix, length);
        }
        return result;
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
            if (copySet.size() != neighbors.size())
                return false;
        }
        return true;
    }

    public Collection<Integer> findVerticesWithOneOrLessEdge(Graph graph) {
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

    /**
     * Algorithm 1 Greedy
     * ref: http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.452.5619&rep=rep1&type=pdf
     * Require: a graph G = (V, E)
     * ==============================
     * W ← V
     * S ← ∅
     * while W != ∅ do
     *  Find a vertex v ∈ W with minimum degree in G[W]
     *  W ← W \ NG[v]
     *  S ← S ∪ {v}
     * end while
     * return S
     * ==============================
     * NG[v] = neighbourhood of vertex v and v;
     * Complexity: O(n/a * n * d(v)) = O(n) with: a: max degree in graph
     * Question 3 with a complexity of 2 ^ n will of course have greater complexity
     * when using the greedy algorithm.
     * Note: Our main result is that Greedy is much better than previously claimed. We obtain a
     * tight performance ratio of (Δ + 2)/3 in terms of maximum degree, and an asymptotically
     * optimal bound of (d + 2)/2 in terms of average degree
     * @param graph
     * @return zoneVide
     */

    public static Collection<Integer> getOneMaximumEmptyZoneFromGraphGreedy(Graph graph) {
        DeepFirstSearchIterator iterator = new DeepFirstSearchIterator(graph);
        int vertexCount = graph.vertexCount();
        int[] setVertex = new int[vertexCount]; //W set
        Arrays.fill(setVertex, 1);
        Collection<Integer> zoneVide = new LinkedList<>(); //S set
        int sum = IntStream.of(setVertex).sum();
        while (sum!=0) {
            //Find a vertex v ∈ W with minimum degree in G[W]
            int min = Integer.MAX_VALUE;
            int index = 0;
            for(int i = 0; i < vertexCount; i++){ // V times
                if(setVertex[i]==1) {
                    Set<Integer> edges = graph.getEdges(i);
                    int degree = 0;
                    for (int vertex: edges) { // < max: d(V) times
                        if(setVertex[vertex] == 1) degree++;
                    }
                    if (degree < min) {
                        min = degree;
                        index = i;
                    }
                }
            }
            // W ← W \ NG[v]
            setVertex[index] = 0;
            for (int a : graph.getEdges(index)) {
                setVertex[a] = 0;
            }
            // S ← S ∪ {v}
            zoneVide.add(index);
            sum = IntStream.of(setVertex).sum();
        }
        return zoneVide;
    }
}
