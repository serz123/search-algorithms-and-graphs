package com.example.graph;

import java.util.Objects;

public class Edge {

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // Overloaded constructor with default weight
    public Edge(int v, int w) {
        this(v, w, 1.0); // Calls the main constructor with default weight of 1.0
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException("Invalid endpoint");
        }
    }

    public double getWeight() {
        return weight;
    }

    // Optional: Override toString for easy printing
    @Override
    public String toString() {
        return String.format("Edge(%d-%d, weight=%.2f)", v, w, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Edge edge = (Edge) obj;
        return (v == edge.v && w == edge.w && weight == edge.weight)
                || (v == edge.w && w == edge.v && weight == edge.weight); // undirected edge equality
    }

    @Override
    public int hashCode() {
        return Objects.hash(v, w, weight);
    }
}
