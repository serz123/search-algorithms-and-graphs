package com.example.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class MyGraph {

    protected List<List<Edge>> adjacencyList;
    private boolean[] marked;
    private int[] edgeTo;
    
    public MyGraph(int numberOfVertex) {
        adjacencyList = new ArrayList<>(numberOfVertex);
        marked = new boolean[numberOfVertex];
        edgeTo = new int[numberOfVertex];
    
        for (int i = 0; i < numberOfVertex; i++) {
            marked[i] = false; 
            edgeTo[i] = -1; 
            adjacencyList.add(new ArrayList<>());
        }
    }

    public int getNumberOfVertex() {
        return adjacencyList.size();
    }

    public abstract int getNumberOfEdges();

    public abstract void addEdge(int source, int destination, double weight);

    public abstract void addEdge(int source, int destination);

    public abstract void removeEdge(int source, int destination);

    public List<Edge> adjacency(int vertex) {
        if (vertex < adjacencyList.size()) {
            return adjacencyList.get(vertex);
        }
        return null;
    }

    public int degree(int vertex) {
        if (vertex < adjacencyList.size()) {
            return adjacencyList.get(vertex).size();
        }
        return -1; // Return -1 if the vertex is out of bounds or has no connections
    }

    public abstract void printGraph();

    public Iterator<Integer> vertexIterator() {
        return new Iterator<Integer>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < getNumberOfVertex();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return current++;
            }
        };
    }

    public Iterator<Edge> edgeIterator() {
        return new Iterator<Edge>() {
            private int vertex = 0;
            private int edgeIndex = 0;

            @Override
            public boolean hasNext() {
                while (vertex < adjacencyList.size()) {
                    if (edgeIndex < adjacencyList.get(vertex).size()) {
                        return true;
                    } else {
                        vertex++;
                        edgeIndex = 0;
                    }
                }
                return false;
            }

            @Override
            public Edge next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return adjacencyList.get(vertex).get(edgeIndex++);
            }
        };
    }

    public Iterator<Edge> adjacencyIterator(int vertex) {
        if (vertex < 0 || vertex >= adjacencyList.size()) {
            throw new IllegalArgumentException("Čvor je izvan granica grafa");
        }
        return adjacencyList.get(vertex).iterator();
    }

    private void dfs(int vertex) {
        marked[vertex] = true;
        
        for (Edge edge : adjacencyList.get(vertex)) { 
            int w = edge.other(vertex);
    
            if (!marked[w]) {
                edgeTo[w] = vertex;
                dfs(w); 
            }
        }
    }

    private void bfs(int vertex) {
        List<Integer> queue = new ArrayList<>(); 
        queue.add(vertex); 
        marked[vertex] = true; 

        while (!queue.isEmpty()) {
            int visitedVertex = queue.remove(0); 
            for (Edge w : adjacency(visitedVertex)) { 
                if (!marked[w.other(visitedVertex)]) { 
                    queue.add(0, w.other(visitedVertex)); 
                    marked[w.other(visitedVertex)] = true; 
                    edgeTo[w.other(visitedVertex)] = visitedVertex; 
                }
            }
        }
    }

    public boolean dfsHasPathTo(int startVertex, int vertex) {
        resetMarked();
        resetEdgeTO();
        dfs(startVertex);
        return hasPathTo(vertex);
    }

    public boolean bfsHasPathTo(int startVertex, int vertex) {
        resetMarked();
        resetEdgeTO();
        bfs(startVertex);
        return hasPathTo(vertex);
    }

    private boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public Iterator<Integer> dfsPathTo(int startVertex, int vertex) {
        dfs(startVertex);
        if (!dfsHasPathTo(startVertex, vertex)) {
            throw new IllegalArgumentException("Vertex does not exsist in the graph.");
        }
        return pathTo(startVertex, vertex); 
    } 

    public Iterator<Integer> bfsPathTo(int startVertex, int vertex) {
        bfs(startVertex);
        if (!bfsHasPathTo(startVertex, vertex)) {
            throw new IllegalArgumentException("Vertex does not exsist in the graph.");
        }
        return pathTo(startVertex, vertex); 
    } 

    private Iterator<Integer> pathTo (int startVertex, int vertex) {
        List<Integer> path = new ArrayList<>();
    
        for (int x = vertex; x != startVertex;x = edgeTo[x]) { 
            path.add(0, x); 
        }
        path.add(0, startVertex); 
    
        return path.iterator(); 
    } 

    private void resetMarked() {
        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }
    }

    private void resetEdgeTO() {
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = -1;
        }
    }
}
