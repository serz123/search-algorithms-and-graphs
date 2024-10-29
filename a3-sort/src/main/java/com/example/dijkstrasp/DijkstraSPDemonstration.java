package com.example.dijkstrasp;

import com.example.graph.DirectedGraph;
import com.example.graph.Edge;

public class DijkstraSPDemonstration {
    public void demonstrate(){
        DirectedGraph graph = new DirectedGraph(5);

        graph.addEdge(0, 1, 4.0);
        graph.addEdge(0, 2, 1.0);
        graph.addEdge(2, 1, 2.0);
        graph.addEdge(1, 3, 1.0);
        graph.addEdge(2, 3, 5.0);
        graph.addEdge(3, 4, 3.0);

        DijkstraSP dijkstra = new DijkstraSP(graph, 0);

        graph.printGraph();

        System.out.println("Shortest distances from node 0:");
        for (int v = 0; v < graph.getNumberOfVertex(); v++) {
            System.out.printf("Node %d: %.2f\n", v, dijkstra.distTo(v));
        }

        System.out.println("\nShortest paths from node 0:");
        for (int v = 0; v < graph.getNumberOfVertex(); v++) {
            System.out.printf("Path to node %d: ", v);
            if (dijkstra.hasPathTo(v)) {
                for (Edge e : dijkstra.pathTo(v)) {
                    System.out.print(e + " ");
                }
                System.out.println();
            } else {
                System.out.println("No path");
            }
        }
    } 
}
