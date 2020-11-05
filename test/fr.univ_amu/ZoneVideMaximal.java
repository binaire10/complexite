package fr.univ_amu;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZoneVideMaximal {
    @Test
    void test() {
        Graph graph = new Graph(6);
        List<Integer> node = IntStream.range(0, graph.vertexCount()).boxed().collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(node);
        int a = node.get(0);
        int b = node.get(1);
        int c = node.get(2);
        int d = node.get(3);
        int e = node.get(4);
        int f = node.get(5);
        graph.addEdge(b, c);
        graph.addEdge(b, f);
        graph.addEdge(b, e);
        graph.addEdge(e, f);
        graph.addEdge(c, f);
        graph.addEdge(c, d);

        Collection<Integer> zoneVide = Math.getOneMaximumEmptyZoneFromGraph(graph);
        System.out.println(zoneVide);
        assertTrue(Math.isEmptyAreaGraph(graph, zoneVide));
    }

    @Test
    void test2() {
        Graph graph = new Graph(10);
        List<Integer> node = IntStream.range(0, graph.vertexCount()).boxed().collect(Collectors.toCollection(ArrayList::new));
//        Collections.shuffle(node);
        String symbole = "abcdefghijklmnopqrstuvwxyz";
        int a = node.get(0);
        int b = node.get(1);
        int c = node.get(2);
        int d = node.get(3);
        int e = node.get(4);
        int f = node.get(5);
        int g = node.get(6);
        int h = node.get(7);
        int i = node.get(8);
        int j = node.get(9);
        graph.addEdge(a, b);
        graph.addEdge(c, d);
        graph.addEdge(d, e);
        graph.addEdge(e, i);
        graph.addEdge(c, f);
        graph.addEdge(g, f);
        graph.addEdge(g, h);
        graph.addEdge(d, h);
        graph.addEdge(j, h);


        Collection<Integer> zoneVide = Math.getOneMaximumEmptyZoneFromGraph(graph);

        System.out.println(Arrays.toString(zoneVide.stream().map(n -> symbole.charAt(node.indexOf(n))).toArray(Character[]::new)));
        assertTrue(Math.isEmptyAreaGraph(graph, zoneVide));
    }
}
