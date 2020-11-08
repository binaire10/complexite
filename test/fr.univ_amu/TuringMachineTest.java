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
    void test_an() {
        Map<Integer, ProgramEdge<Integer>> node1Edge = new HashMap<>();
        ProgramNode<Integer> node1 = new ProgramNode<>(false, node1Edge);
        ProgramNode<Integer> node2 = new ProgramNode<>(true, Collections.emptyMap());
        node1Edge.put((int) 'a', new ProgramEdge<>(TuringMachine.Action.Right, node1, (int) 'a'));
        node1Edge.put(TuringMachine.blank(), new ProgramEdge<>(TuringMachine.Action.Right, node2, TuringMachine.blank()));
        assertTrue(new TuringMachine<>(0, "aa".chars().boxed().collect(Collectors.toList()), node1).isAccept());
        assertTrue(new TuringMachine<>(0, "a".chars().boxed().collect(Collectors.toList()), node1).isAccept());
        assertFalse(new TuringMachine<>(0, "aab".chars().boxed().collect(Collectors.toList()), node1).isAccept());
        assertFalse(new TuringMachine<>(0, "aaba".chars().boxed().collect(Collectors.toList()), node1).isAccept());
    }

    @Test
    void test_anbn() {
        Map<Integer, ProgramEdge<Integer>> node1Edge = new HashMap<>();
        Map<Integer, ProgramEdge<Integer>> rAEdge = new HashMap<>();
        Map<Integer, ProgramEdge<Integer>> rBEdge = new HashMap<>();
        Map<Integer, ProgramEdge<Integer>> comeBackEdge = new HashMap<>();
        ProgramNode<Integer> begin = new ProgramNode<>(false, node1Edge);
        ProgramNode<Integer> rA = new ProgramNode<>(false, rAEdge);
        ProgramNode<Integer> rB = new ProgramNode<>(false, rBEdge);
        ProgramNode<Integer> comeBack = new ProgramNode<>(false, comeBackEdge);
        ProgramNode<Integer> end = new ProgramNode<>(true, Collections.emptyMap());
        ProgramNode<Integer> error = new ProgramNode<>(false, Collections.emptyMap());

        node1Edge.put((int) 'a', new ProgramEdge<>(TuringMachine.Action.Right, rA, TuringMachine.blank()));
        node1Edge.put(TuringMachine.blank(), new ProgramEdge<>(TuringMachine.Action.Right, end, TuringMachine.blank()));
        node1Edge.put((int) 'b', new ProgramEdge<>(TuringMachine.Action.Right, error, (int) 'b'));

        rAEdge.put((int) 'a', new ProgramEdge<>(TuringMachine.Action.Right, rA, (int) 'a'));
        rAEdge.put(TuringMachine.blank(), new ProgramEdge<>(TuringMachine.Action.Left, comeBack, TuringMachine.blank()));
        rAEdge.put((int) 'b', new ProgramEdge<>(TuringMachine.Action.Right, rA, (int) 'b'));

        comeBackEdge.put((int) 'b', new ProgramEdge<>(TuringMachine.Action.Left, rB, null));

        rBEdge.put((int) 'b', new ProgramEdge<>(TuringMachine.Action.Left, rB, (int) 'b'));
        rBEdge.put(TuringMachine.blank(), new ProgramEdge<>(TuringMachine.Action.Right, begin, TuringMachine.blank()));
        rBEdge.put((int) 'a', new ProgramEdge<>(TuringMachine.Action.Left, rB, (int) 'a'));

        assertTrue(new TuringMachine<>(0, "".chars().boxed().collect(Collectors.toList()), begin).isAccept());
        assertTrue(new TuringMachine<>(0, "ab".chars().boxed().collect(Collectors.toList()), begin).isAccept());
        assertTrue(new TuringMachine<>(0, "aabb".chars().boxed().collect(Collectors.toList()), begin).isAccept());
        assertTrue(new TuringMachine<>(0, "aaabbb".chars().boxed().collect(Collectors.toList()), begin).isAccept());

        assertFalse(new TuringMachine<>(0, "b".chars().boxed().collect(Collectors.toList()), begin).isAccept());
        assertFalse(new TuringMachine<>(0, "abb".chars().boxed().collect(Collectors.toList()), begin).isAccept());
        assertFalse(new TuringMachine<>(0, "aabbb".chars().boxed().collect(Collectors.toList()), begin).isAccept());
        assertFalse(new TuringMachine<>(0, "aaabbbb".chars().boxed().collect(Collectors.toList()), begin).isAccept());

        assertFalse(new TuringMachine<>(0, "ba".chars().boxed().collect(Collectors.toList()), begin).isAccept());
        assertFalse(new TuringMachine<>(0, "ababb".chars().boxed().collect(Collectors.toList()), begin).isAccept());
        assertFalse(new TuringMachine<>(0, "abaabbb".chars().boxed().collect(Collectors.toList()), begin).isAccept());
    }
}