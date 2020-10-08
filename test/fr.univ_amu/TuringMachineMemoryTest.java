package fr.univ_amu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TuringMachineMemoryTest {

    @Test
    void get() {
        TuringMachineMemory<Integer> memory = new TuringMachineMemory<>(new ArrayList<>());
        memory.set(-10, 5);
        memory.set(10, 2);
        assertEquals(5, memory.get(-10));
        assertEquals(2, memory.get(10));
    }
}