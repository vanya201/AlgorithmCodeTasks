import entitys.ComplexEdge;
import entitys.Vertex;
import graph.GraphCreator;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.ASearchSolution;
import task.DijkstraSearchSolution;
import task.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Task {
    private Solution aSearchSolution;
    private Solution dijkstraSearchSolution;
    private Graph<Vertex, ComplexEdge> graph;
    Vertex start;
    Vertex end;

    @BeforeEach
    public void setUp() {
        this.aSearchSolution = new ASearchSolution();
        this.dijkstraSearchSolution = new DijkstraSearchSolution();
        graph = new GraphCreator().initGraph();
        List<Vertex> vertexList = new ArrayList<>(graph.vertexSet());
        Random random = new Random();
        start = vertexList.get(999);
        end = vertexList.get(500);
    }

    @Test
    public void testASearchSolution() {
        long startTime = System.nanoTime();
        GraphPath<Vertex, ComplexEdge> path = aSearchSolution.search(graph, start, end); //TODO replace method
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;

        List<Vertex> vertexList = path.getVertexList();
        double w = path.getWeight();
        System.out.printf("Time execution: %.3f ms%n for testASearchSolution",  executionTime);
    }

    @Test
    public void testDijkstraSolution() {
        long startTime = System.nanoTime();
        GraphPath<Vertex, ComplexEdge> path = dijkstraSearchSolution.search(graph, start, end);
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;

        List<Vertex> vertexList = path.getVertexList();
        double w = path.getWeight();
        System.out.printf("Time execution: %.3f ms%n for testDijkstraSolution",  executionTime);
    }


    @AfterEach
    public void tearDown() {
        this.aSearchSolution = null;
        this.dijkstraSearchSolution = null;
        graph = null;
    }
}
