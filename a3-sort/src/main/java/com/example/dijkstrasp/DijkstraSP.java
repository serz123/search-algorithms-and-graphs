package com.example.dijkstrasp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

import com.example.graph.Edge;
import com.example.graph.MyGraph;

public class DijkstraSP {
    private double[] distTo;
    private Edge[] edgeTo;
    private PriorityQueue<Integer> pq;

    public DijkstraSP(MyGraph G, int s) {
        int V = G.getNumberOfVertex();
        distTo = new double[V];
        edgeTo = new Edge[V];
        pq = new PriorityQueue<>((a, b) -> Double.compare(distTo[a], distTo[b]));

        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[s] = 0.0;
        pq.add(s);


        while (!pq.isEmpty()) {
            int v = pq.poll();

            for (Edge e : G.adjacency(v)) {
                relax(e, v);
            }
        }
    }

    private void relax(Edge e, int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.getWeight()) {
            distTo[w] = distTo[v] + e.getWeight();
            edgeTo[w] = e;
            
            if (pq.contains(w)) {
                pq.remove(w);
            }
            pq.add(w);
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        LinkedList<Edge> path = new LinkedList<>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.either()]) {
            path.addFirst(e);
        }
        return path;
    }
}
