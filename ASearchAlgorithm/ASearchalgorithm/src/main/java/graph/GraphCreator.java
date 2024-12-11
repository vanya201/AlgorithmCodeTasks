package graph;

import entitys.ComplexEdge;
import entitys.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GraphCreator {

    public Graph<Vertex, ComplexEdge> initGraph()
    {
        Graph<Vertex, ComplexEdge> graph = new SimpleWeightedGraph<>(ComplexEdge.class);
        int numberOfVertices = 1000;
        Random random = new Random();
        Map<Integer, Vertex> vertexMap = new HashMap<>();

        for (int i = 0; i < numberOfVertices; i++) {
            Vertex vertex = new Vertex(i, i, "v" + i);
            graph.addVertex(vertex);
            vertexMap.put(i, vertex);
        }

        for (int i = 0; i < numberOfVertices; i++) {
            Vertex source = vertexMap.get(i);

            for (int j = i + 1; j < numberOfVertices; j++) {
                Vertex target = vertexMap.get(j);

                double distance = getDistance(source, target);
                double noise = random.nextDouble() * 5;
                double edgeWeight = distance + noise;

                ComplexEdge edge = new ComplexEdge(0, edgeWeight);

                graph.addEdge(source, target, edge);
                graph.setEdgeWeight(edge, edgeWeight);
                graph.addEdge(target, source, edge);
                graph.setEdgeWeight(edge, edgeWeight);

            }
        }
        return graph;
    }

    private double getDistance(Vertex v1, Vertex v2) {
        double dx = v2.getX() - v1.getX();
        double dy = v2.getY() - v1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
