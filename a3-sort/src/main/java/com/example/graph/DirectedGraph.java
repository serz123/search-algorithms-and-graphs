package com.example.graph;

import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class DirectedGraph extends MyGraph {

    public DirectedGraph(int numberOfVertex) {
        super(numberOfVertex);
    }

    @Override
    public int getNumberOfEdges() {
        int sum = 0;
        for (List<Edge> list : adjacencyList) {
            sum += list.size();
        }
        return sum;
    }

    // Method to add an edge with specified source, destination, and weight
    @Override
    public void addEdge(int source, int destination, double weight) {
        Edge edge = new Edge(source, destination, weight);
        addEdge(edge);
    }

    // Method to add an edge with specified source and destination (default weight of 1.0)
    @Override
    public void addEdge(int source, int destination) {
        Edge edge = new Edge(source, destination);
        addEdge(edge);
    }

    private void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);

        if (v < adjacencyList.size() && w < adjacencyList.size()) {
            adjacencyList.get(v).add(edge); // Only add edge to v's adjacency list
        }
    }

    @Override
    public void removeEdge(int source, int destination) {
        Edge edge = new Edge(source, destination);
        removeEdge(edge);
    }

    private void removeEdge(Edge edge) {
        int v = edge.either();
        adjacencyList.get(v).remove(edge);
    }

    @Override
    public void printGraph() {
        String styleSheet
                = "node {"
                + "   text-alignment: center;"
                + "   text-offset: 0px, 0px;"
                + "   size: 20px, 20px;"
                + "   fill-color: white;"
                + "}"
                + "node.marked {"
                + "   fill-color: red;"
                + "}"
                + "edge {"
                + "   fill-color: gray;"
                + "   arrow-shape: arrow;"
                + "   arrow-size: 10px, 7px;"
                + "   text-alignment: above;" // Align the weight label above the edge
                + "   text-size: 12px;" // Adjust the size of the text
                + "}";

        // Set up GraphStream
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Directed Graph Visualization");
        graph.setAttribute("ui.stylesheet", styleSheet);

        // Add nodes to the graph
        for (int i = 0; i < getNumberOfVertex(); i++) {
            graph.addNode(String.valueOf(i)).setAttribute("ui.label", "Node " + i);

            // Assign position attributes (e.g., circular layout)
            double angle = 2 * Math.PI * i / getNumberOfVertex();
            double radius = 100; // Adjust as needed
            double x = Math.cos(angle) * radius;
            double y = Math.sin(angle) * radius;
            graph.getNode(String.valueOf(i)).setAttribute("x", x);
            graph.getNode(String.valueOf(i)).setAttribute("y", y);
        }

        // Add directed edges based on adjacency list
        for (int i = 0; i < getNumberOfVertex(); i++) {
            for (Edge neighbor : adjacency(i)) {
                String edgeId = i + "-" + neighbor.other(i); // Get the destination vertex
                // Set the edge's label to show its weight
                graph.addEdge(edgeId, String.valueOf(i), String.valueOf(neighbor.other(i)), true) // Directed edge
                        .setAttribute("ui.label", String.valueOf(neighbor.getWeight())); // Set the weight as the label
            }
        }

        // Display the graph
        Viewer viewer = graph.display();
        viewer.disableAutoLayout(); // Disable auto-layout for better positioning
    }

}
