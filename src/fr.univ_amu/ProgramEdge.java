package fr.univ_amu;

public class ProgramEdge<T> {
    public final TuringMachine.Action action;
    public final ProgramNode<T> next;
    public final T write;

    public ProgramEdge(TuringMachine.Action action, ProgramNode<T> next, T write) {
        this.action = action;
        this.next = next;
        this.write = write;
    }
}
