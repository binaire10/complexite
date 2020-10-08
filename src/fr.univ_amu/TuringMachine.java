package fr.univ_amu;

import java.util.List;

public class TuringMachine<T extends Number> {
    private final TuringMachineMemory<T> band;
    private int position;

    public TuringMachine(int position, List<T> band) {
        this.position = position;
        this.band = new TuringMachineMemory<>(band);
    }

    public T writeAndRead(T value, Action a) {
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
