package fr.univ_amu;

import java.util.List;

public class TuringMachine<T extends Number> {
    public static <A> A blank() {
        return null;
    }

    private final List<T> initialValue;
    private final int initialPosition;
    private int position;
    private final ProgramNode<T> root;

    public TuringMachine(int position, List<T> initialValue, ProgramNode<T> root) {
        this.initialPosition = position;
        this.initialValue = initialValue;
        this.root = root;
    }

    public boolean isAccept() {
        position = initialPosition;
        TuringMachineMemory<T> memory = new TuringMachineMemory<>(initialValue);
        ProgramNode<T> state = root;
        T symbol = memory.get(position);
        while (!state.isFinal || !state.edges.isEmpty()) {
            ProgramEdge<T> edge = state.edges.get(symbol);
            if (edge == null)
                break;
            symbol = writeAndRead(memory, edge.write, edge.action);
            state = edge.next;
        }
        return state.isFinal;
    }


    private T writeAndRead(TuringMachineMemory<T> band, T value, Action a) {
        band.set(position, value);
        switch (a) {
            case Left:
                --position;
                break;
            case Right:
                ++position;
                break;
        }
        return band.get(position);
    }

    enum Action {
        Left,
        Right
    }
}
