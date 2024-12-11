package task;

import entitys.ComplexEdge;
import entitys.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

public interface Solution {
    GraphPath<Vertex, ComplexEdge> search(Graph<Vertex, ComplexEdge> graph, Vertex start, Vertex end);
}
