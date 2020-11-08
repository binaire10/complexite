package fr.univ_amu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TuringMachineMemory<T extends Number> {
    private final List<T> memory;
    private int offset;

    public TuringMachineMemory(List<T> memory) {
        offset = 0;
        this.memory = new ArrayList<>(memory);
    }

    public T get(int position) {
        ensurePosition(position + offset);
        return memory.get(position + offset);
    }

    public T set(int position, T value) {
        ensurePosition(position + offset);
        return memory.set(position + offset, value);
    }

    private void ensurePosition(int position) {
        if (position < 0) {
            int count = -position;
            offset += count;
            memory.addAll(0, IntStream.range(0, count).mapToObj(i -> (T) null).collect(Collectors.toList()));
        }
        if (position >= memory.size()) {
            int count = position + 1 - memory.size();
            memory.addAll(IntStream.range(0, count).mapToObj(i -> (T) null).collect(Collectors.toList()));
        }
    }
}
