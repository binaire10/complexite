package fr.univ_amu;

import java.util.List;

public class TuringMachineMemory<T extends Number> {
    private final List<T> memory;
    private int offset;

    public TuringMachineMemory(List<T> memory) {
        offset = 0;
        this.memory = memory;
    }

    public T get(int position) {
        ensurePosition(position);
        return memory.get(position + offset);
    }

    public T set(int position, T value) {
        ensurePosition(position);
        return memory.set(position + offset, value);
    }

    private void ensurePosition(int position) {
        if (position + offset < 0) {
            ++offset;
            memory.add(0, null);
        }
        if (position + offset > memory.size())
            memory.add(null);
    }
}
