package com.example.graph;

import java.util.Iterator;


public class GraphDemonstration {

    public void demonstrate() {

        MyGraph graph = new DirectedGraph(15); // Create a graph with 15 vertices
        // MyGraph graph = new UndirectedGraph(15);

        // Add edges with weights where applicable
        graph.addEdge(2, 0, 5.0); // Adding edge with specified weight
        graph.addEdge(2, 3); // Adding edge with default weight of 1.0
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        graph.addEdge(9, 10);
        graph.addEdge(10, 6);
        graph.addEdge(11, 12);
        graph.addEdge(12, 13);
        graph.addEdge(12, 14);
        graph.addEdge(13, 14);

        // Remove edges
        graph.removeEdge(13, 14); // Specify weight if needed for removal
        graph.removeEdge(1, 3); // Specify weight if needed for removal
    
        System.out.println("Adjacency of the node 14 is " + graph.adjacency(14));
        System.out.println("Adjacency of the node 4 is " + graph.adjacency(4));
        System.out.println("Adjacency of the node 1 is " + graph.adjacency(1));
        System.out.println("Degree of the node 1 is " + graph.degree(1));
        System.out.println("Degree of the node 4 is " + graph.degree(4));
        System.out.println("Degree of the node 14 is " + graph.degree(14));

        // Optional: Output the number of edges and vertices to confirm assertions
        System.out.println("Number of vertices: " + graph.getNumberOfVertex());
        System.out.println("Number of edges: " + graph.getNumberOfEdges());

        System.out.println();
        System.out.println("Edges:");
        Iterator<Integer> vertexIterator = graph.vertexIterator();
        while (vertexIterator.hasNext()) {
            System.out.println("Vertex: " + vertexIterator.next());
        }

        System.out.println();
        System.out.println("Edges:");
        Iterator<Edge> edgeIterator = graph.edgeIterator();
        while (edgeIterator.hasNext()) {
            System.out.println(edgeIterator.next());
        }

        System.out.println("Adjacency of vertex 4:");
        Iterator<Edge> adjacencyIterator = graph.adjacencyIterator(4);
        while (adjacencyIterator.hasNext()) {
            System.out.println(adjacencyIterator.next());
        }

        System.out.println("DFS: There is a path from 13 to 4 = " + graph.dfsHasPathTo(3, 4));
        System.out.println("DFS: There is a path from 3 to 14 = " + graph.dfsHasPathTo(13, 14));
        System.out.println("DFS: There is a path from 12 to 14 = " + graph.dfsHasPathTo(12, 14));
        System.out.println("DFS: There is a path from 3 to 2 = " + graph.dfsHasPathTo(3, 2));
        System.out.println("DFS: There is a path from 12 to 10 = " + graph.dfsHasPathTo(2, 10));
        System.out.println("DFS: There is a path from 3 to 4 = " + graph.dfsHasPathTo(3, 14));
        System.out.println();

        System.out.println("BFS: There is a path from 13 to 4 = " + graph.bfsHasPathTo(3, 4));
        System.out.println("BFS: There is a path from 3 to 14 = " + graph.bfsHasPathTo(13, 14));
        System.out.println("BS: There is a path from 12 to 14 = " + graph.bfsHasPathTo(12, 14));
        System.out.println("BFS: There is a path from 3 to 2 = " + graph.bfsHasPathTo(3, 2));
        System.out.println("BFS: There is a path from 12 to 10 = " + graph.bfsHasPathTo(2, 10));
        System.out.println("BFS: There is a path from 3 to 4 = " + graph.bfsHasPathTo(3, 14));
        System.out.println();

         // Demonstrate DFS
         System.out.println("DFS: There is a path from 3 to 4 = " + graph.dfsHasPathTo(3, 4));
         demonstratePathToDFS(graph, 3, 4);
 
         System.out.println("DFS: There is a path from 13 to 14 = " + graph.dfsHasPathTo(13, 14));
         demonstratePathToDFS(graph, 13, 14);
 
         System.out.println("DFS: There is a path from 2 to 10 = " + graph.dfsHasPathTo(2, 10));
         demonstratePathToDFS(graph, 2, 10);
 
         System.out.println();
 
         // Demonstrate BFS
         System.out.println("BFS: There is a path from 3 to 4 = " + graph.bfsHasPathTo(3, 4));
         demonstratePathToBFS(graph, 3, 4);
 
         System.out.println("BFS: There is a path from 12 to 14 = " + graph.bfsHasPathTo(12, 14));
         demonstratePathToBFS(graph, 12, 14);
 
         System.out.println("BFS: There is a path from 2 to 10 = " + graph.bfsHasPathTo(2, 10));
         demonstratePathToBFS(graph, 2, 10);
 
        
        graph.printGraph();
    }

    private void demonstratePathToDFS(MyGraph graph, int startVertex, int endVertex) {
        try {
            Iterator<Integer> path = graph.dfsPathTo(startVertex, endVertex);
            System.out.print("DFS Path from " + startVertex + " to " + endVertex + ": ");
            while (path.hasNext()) {
                System.out.print(path.next() + " ");
            }
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("No path found for DFS from " + startVertex + " to " + endVertex);
        }
    }

    private void demonstratePathToBFS(MyGraph graph, int startVertex, int endVertex) {
        try {
            Iterator<Integer> path = graph.bfsPathTo(startVertex, endVertex);
            System.out.print("BFS Path from " + startVertex + " to " + endVertex + ": ");
            while (path.hasNext()) {
                System.out.print(path.next() + " ");
            }
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("No path found for BFS from " + startVertex + " to " + endVertex);
        }
    }
}
