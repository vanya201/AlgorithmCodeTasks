package task;

import entitys.ComplexEdge;
import entitys.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class DijkstraSearchSolution implements Solution {

    @Override
    //O(nlog(n))
    public GraphPath<Vertex, ComplexEdge> search(Graph<Vertex, ComplexEdge> graph, Vertex start, Vertex end) {
        DijkstraShortestPath<Vertex, ComplexEdge> dijkstra = new DijkstraShortestPath<>(graph);
        return dijkstra.getPath(start, end);
    }
}
