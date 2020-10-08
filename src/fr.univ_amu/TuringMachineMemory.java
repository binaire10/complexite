package fr.univ_amu;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
            int count = -(position + offset);
            offset += count;
            memory.addAll(0, IntStream.range(0, count).mapToObj(i -> (T) null).collect(Collectors.toList()));
        }
        if (position + offset >= memory.size()) {
            int count = position + offset + 1 - memory.size();
            memory.addAll(IntStream.range(0, count).mapToObj(i -> (T) null).collect(Collectors.toList()));
        }
    }
}
