package task;

import entitys.ComplexEdge;
import entitys.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

public class ASearchSolution implements Solution {

    private Solution dijkstraSearchSolution = new DijkstraSearchSolution();

    private double getDistanceFromTo(Vertex start, Vertex end) {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    //O(nlog(n))
    public GraphPath<Vertex, ComplexEdge> search(Graph<Vertex, ComplexEdge> graph, Vertex start, Vertex end) {
        //O(m)
        for (ComplexEdge edge : graph.edgeSet()) {
            Vertex source = graph.getEdgeSource(edge);
            Vertex target = graph.getEdgeTarget(edge);

            double potential =  getDistanceFromTo(target, end) - getDistanceFromTo(source, end);
            int reDistance = edge.getReDistance();
            edge.setImDistance(reDistance);

            reDistance = reDistance + (int)potential;
            edge.setReDistance(reDistance);
            graph.setEdgeWeight(edge, reDistance);
        }

        //O(nlog(n))
        GraphPath<Vertex, ComplexEdge> graphPath = dijkstraSearchSolution.search(graph, start, end);

        //O(m)
        for (ComplexEdge edge : graph.edgeSet()) {
            int imDistance = edge.getImDistance();
            edge.setReDistance(imDistance);
            graph.setEdgeWeight(edge, imDistance);
        }

        return graphPath;
    }
}
