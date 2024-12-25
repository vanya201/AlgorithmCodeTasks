package task;

import entitys.ComplexEdge;
import entitys.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

import java.util.*;

public class ASearchSolution implements Solution {

    // O(n log n)
    public GraphPath<Vertex, ComplexEdge> search(Graph<Vertex, ComplexEdge> graph, Vertex start, Vertex end) {
        // O(m)
        for (ComplexEdge edge : graph.edgeSet()) {
            Vertex source = graph.getEdgeSource(edge);
            Vertex target = graph.getEdgeTarget(edge);

            // Calculate the heuristic distance to the goal (end vertex)
            double potential = getDistanceFromTo(target, end) - getDistanceFromTo(source, end);
            double reDistance = edge.getReDistance();
            edge.setImDistance(reDistance);

            reDistance = reDistance + (int) potential; // Update the edge's real distance
            edge.setReDistance(reDistance);
            graph.setEdgeWeight(edge, reDistance); // Set updated weight in the graph
        }

        // O(n log n)
        List<Vertex> path = ASearch(graph, 3, start, end); // Perform A* search

        // O(m)
        for (ComplexEdge edge : graph.edgeSet()) {
            // Restore the original distances after the A* search
            double imDistance = edge.getImDistance();
            edge.setReDistance(imDistance);
            graph.setEdgeWeight(edge, imDistance);
        }

        // Convert the found path into a custom GraphPath object
        List<ComplexEdge> edgeList = getEdgeListFromPath(graph, path);
        return new CustomGraphPath(start, end, path, edgeList);
    }

    // O(1)
    private double getDistanceFromTo(Vertex start, Vertex end) {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        return Math.sqrt(dx * dx + dy * dy); // Euclidean distance
    }

    // A* search implementation with heuristic
    public List<Vertex> ASearch(Graph<Vertex, ComplexEdge> graph, final int n, Vertex startVertex, Vertex endVertex) {
        Map<Vertex, Double> distances = new HashMap<>();
        Map<Vertex, Vertex> predecessors = new HashMap<>();

        for (Vertex vertex : graph.vertexSet())
            distances.put(vertex, Double.MAX_VALUE);

        distances.put(startVertex, 0.0); // Distance from start to itself is 0

        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(Comparator.comparing(distances::get));
        priorityQueue.add(startVertex);

        Set<Vertex> visited = new HashSet<>();

        while (!priorityQueue.isEmpty()) {
            Vertex currentVertex = priorityQueue.poll();

            if (visited.contains(currentVertex)) {
                continue;
            }
            visited.add(currentVertex);

            for (ComplexEdge edge : graph.edgesOf(currentVertex)) {
                // Get the neighbor vertex
                Vertex neighbor = graph.getEdgeTarget(edge);
                if (neighbor.equals(currentVertex)) {
                    neighbor = graph.getEdgeSource(edge);
                }

                if (!visited.contains(neighbor)) {
                    double newDist = distances.get(currentVertex) + graph.getEdgeWeight(edge);
                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        predecessors.put(neighbor, currentVertex); // Store the predecessor
                        priorityQueue.add(neighbor);
                    }
                }

                // If the end vertex is reached, return the path if it meets the heuristic condition
                if (neighbor.equals(endVertex) && distances.get(neighbor) < n * getDistanceFromTo(startVertex, endVertex)) {
                    return reconstructPath(predecessors, startVertex, endVertex);
                }
            }
        }

        return reconstructPath(predecessors, startVertex, endVertex);
    }

    // Reconstruct the path by following the predecessors map
    private List<Vertex> reconstructPath(Map<Vertex, Vertex> predecessors, Vertex startVertex, Vertex endVertex) {
        List<Vertex> path = new ArrayList<>();
        Vertex currentVertex = endVertex;
        while (currentVertex != null) {
            path.add(currentVertex);
            currentVertex = predecessors.get(currentVertex);
        }
        Collections.reverse(path); // Reverse to get the path from start to end
        return path;
    }

    // Convert a list of vertices into a list of edges corresponding to the graph
    private List<ComplexEdge> getEdgeListFromPath(Graph<Vertex, ComplexEdge> graph, List<Vertex> path) {
        List<ComplexEdge> edgeList = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            Vertex source = path.get(i);
            Vertex target = path.get(i + 1);
            ComplexEdge edge = graph.getEdge(source, target);
            if (edge != null) {
                edgeList.add(edge);
            }
        }
        return edgeList;
    }

    // Calculate the total weight of a path
    private double getPathWeight(Graph<Vertex, ComplexEdge> graph, List<ComplexEdge> edgeList) {
        double totalWeight = 0.0;
        for (ComplexEdge edge : edgeList) {
            totalWeight += graph.getEdgeWeight(edge); // Sum up the weights of the edges in the path
        }
        return totalWeight;
    }

    // Custom GraphPath implementation
    public static class CustomGraphPath implements GraphPath<Vertex, ComplexEdge> {
        private final Vertex startVertex;
        private final Vertex endVertex;
        private final List<Vertex> vertexList;
        private final List<ComplexEdge> edgeList;

        public CustomGraphPath(Vertex startVertex, Vertex endVertex, List<Vertex> vertexList, List<ComplexEdge> edgeList) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.vertexList = vertexList;
            this.edgeList = edgeList;
        }

        @Override
        public Graph<Vertex, ComplexEdge> getGraph() {
            //TODO
            return null; // or throw new UnsupportedOperationException("Graph not implemented");
        }

        @Override
        public Vertex getStartVertex() {
            return startVertex;
        }

        @Override
        public Vertex getEndVertex() {
            return endVertex;
        }

        @Override
        public List<ComplexEdge> getEdgeList() {
            return edgeList;
        }

        @Override
        public List<Vertex> getVertexList() {
            return vertexList;
        }

        @Override
        public double getWeight() {
            return edgeList.stream().mapToDouble(edge -> edge.getReDistance()).sum(); // Sum up edge weights for total weight
        }

        @Override
        public int getLength() {
            return edgeList.size();
        }
    }
}
