package fr.univ_amu;

import java.util.Collections;
import java.util.Map;

public class ProgramNode<T> {
    public final boolean isFinal;
    public final Map<T, ProgramEdge<T>> edges;

    public ProgramNode(boolean isFinal, Map<T, ProgramEdge<T>> edges) {
        this.isFinal = isFinal;
        this.edges = Collections.unmodifiableMap(edges);
    }
}
