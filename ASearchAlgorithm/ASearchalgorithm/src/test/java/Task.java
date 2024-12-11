import entitys.ComplexEdge;
import entitys.Vertex;
import org.jgrapht.Graph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.ASearchSolution;
import task.DijkstraSearchSolution;
import task.Solution;


public class Task {
    private Solution aSearchSolution;
    private Solution dijkstraSearchSolution;
    private Graph<Vertex, ComplexEdge> graph;


    @BeforeEach
    public void setUp() {
        this.aSearchSolution = new ASearchSolution();
        this.dijkstraSearchSolution = new DijkstraSearchSolution();
        initGraph();
    }

    private void initGraph() {

    }

    @Test
    public void testASearchSolution() {

    }

    @Test
    public void testDijkstraSolution() {

    }


    @AfterEach
    public void tearDown() {
        this.aSearchSolution = null;
        this.dijkstraSearchSolution = null;
        graph = null;
    }
}
