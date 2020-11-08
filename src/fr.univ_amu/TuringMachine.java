package fr.univ_amu;

import java.util.List;

public class TuringMachine<T extends Number> {
    private final TuringMachineMemory<T> band;
    private final int initialPosition;
    private int position;
    private final ProgramNode<T> root;

    public TuringMachine(int position, List<T> band, ProgramNode<T> root) {
        this.initialPosition = position;
        this.band = new TuringMachineMemory<>(band);
        this.root = root;
    }

    public boolean isAccept() {
        position = initialPosition;
        ProgramNode<T> state = root;
        T symbol = band.get(0);
        while (!state.isFinal || !state.edges.isEmpty()) {
            ProgramEdge<T> edge = state.edges.get(symbol);
            if (edge == null)
                break;
            symbol = writeAndRead(edge.write, edge.action);
            state = edge.next;
        }
        return state.isFinal;
    }


    private T writeAndRead(T value, Action a) {
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
