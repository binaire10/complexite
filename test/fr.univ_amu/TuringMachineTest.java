package fr.univ_amu;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TuringMachineTest {
    @Test
    void test_aa() {
        Map<Integer, ProgramEdge<Integer>> node1Edge = new HashMap<>();
        ProgramNode<Integer> node1 = new ProgramNode<>(false, node1Edge);
        ProgramNode<Integer> node2 = new ProgramNode<>(true, Collections.emptyMap());
        node1Edge.put((int) 'a', new ProgramEdge<>(TuringMachine.Action.Right, node1, (int) 'a'));
        node1Edge.put(null, new ProgramEdge<>(TuringMachine.Action.Right, node2, null));
        TuringMachine<Integer> test = new TuringMachine<>(0, "aa".chars().boxed().collect(Collectors.toList()), node1);
        assertTrue(test.isAccept());
    }

    @Test
    void test_aab() {
        Map<Integer, ProgramEdge<Integer>> node1Edge = new HashMap<>();
        ProgramNode<Integer> node1 = new ProgramNode<>(false, node1Edge);
        ProgramNode<Integer> node2 = new ProgramNode<>(true, Collections.emptyMap());
        node1Edge.put((int) 'a', new ProgramEdge<>(TuringMachine.Action.Right, node1, (int) 'a'));
        node1Edge.put(null, new ProgramEdge<>(TuringMachine.Action.Right, node2, null));
        TuringMachine<Integer> test = new TuringMachine<>(0, "aab".chars().boxed().collect(Collectors.toList()), node1);
        assertFalse(test.isAccept());
    }
}