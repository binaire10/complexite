package fr.univ_amu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MathTest {

    void addTestDatasInGraph(Graph graph) {
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,3);
        graph.addEdge(2,6);
        graph.addEdge(3,4);
        graph.addEdge(5,6);
    }

    @Test
    /*
      If you want to run the test with the assert line,
      comment the line "Collections.shuffle(verticesClique);" of "getOneMaximalCliqueFromGraph()" function in Math.java
     */
    void testGetOneMaximalCliqueFromGraph() {
        Graph graph = new Graph(7);
        addTestDatasInGraph(graph);
        Set<Integer> result = Math.getOneMaximalCliqueFromGraph(graph);
        Object[] tableResult = result.toArray();
        //assertEquals(Arrays.toString(tableResult), "[0, 3, 5]");
        System.out.println(Arrays.toString(tableResult));
    }

    @Test
    void testIsEmptyAreaGraph() {
        Graph graph = new Graph(9);
        addTestDatasInGraph(graph);
        ArrayList<Integer> emptyArea = new ArrayList<>();
        emptyArea.add(7);
        emptyArea.add(8);
        assertTrue(Math.isEmptyAreaGraph(graph, emptyArea));
        graph.addEdge(7,8);
        assertFalse(Math.isEmptyAreaGraph(graph, emptyArea));
    }

}
