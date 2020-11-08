package fr.univ_amu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MachineAnBn {
    public static void main(String[] args) throws IOException {
        Map<Integer, ProgramEdge<Integer>> node1Edge = new HashMap<>();
        Map<Integer, ProgramEdge<Integer>> rAEdge = new HashMap<>();
        Map<Integer, ProgramEdge<Integer>> rBEdge = new HashMap<>();
        Map<Integer, ProgramEdge<Integer>> comeBackEdge = new HashMap<>();
        ProgramNode<Integer> initial = new ProgramNode<>(false, node1Edge);
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

        comeBackEdge.put((int) 'a', new ProgramEdge<>(TuringMachine.Action.Left, error, (int) 'a'));
        comeBackEdge.put((int) 'b', new ProgramEdge<>(TuringMachine.Action.Left, rB, TuringMachine.blank()));

        rBEdge.put((int) 'b', new ProgramEdge<>(TuringMachine.Action.Left, rB, (int) 'b'));
        rBEdge.put(TuringMachine.blank(), new ProgramEdge<>(TuringMachine.Action.Right, initial, TuringMachine.blank()));
        rBEdge.put((int) 'a', new ProgramEdge<>(TuringMachine.Action.Left, rB, (int) 'a'));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null)
            System.out.println(new TuringMachine<>(0, line.chars().boxed().collect(Collectors.toList()), initial).isAccept());
    }
}
