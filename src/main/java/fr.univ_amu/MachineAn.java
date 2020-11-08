package fr.univ_amu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MachineAn {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<Integer, ProgramEdge<Integer>> node1Edge = new HashMap<>();
        ProgramNode<Integer> initial = new ProgramNode<>(false, node1Edge);

        ProgramNode<Integer> end = new ProgramNode<>(true, Collections.emptyMap());

        node1Edge.put((int) 'a', new ProgramEdge<>(TuringMachine.Action.Right, initial, (int) 'a'));
        node1Edge.put(TuringMachine.blank(), new ProgramEdge<>(TuringMachine.Action.Right, end, TuringMachine.blank()));

        String line;
        while ((line = reader.readLine()) != null)
            System.out.println(new TuringMachine<>(0, line.chars().boxed().collect(Collectors.toList()), initial).isAccept());
    }
}
