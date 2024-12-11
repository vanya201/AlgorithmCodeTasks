import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.BestSolution;
import task.MySolution;
import task.Solution;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestNQueens {

    private Solution mySolution;
    private Solution bestSolution;

    @BeforeEach
    public void setUp() {
        this.mySolution = new MySolution();
        this.bestSolution = new BestSolution();
    }

    @Test
    public void testMySolution() {
        int n = 8;
        long startTime = System.nanoTime();
        List<List<String>> results = mySolution.solveNQueens(n);
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;

        assertNotNull(results, "Result should not be null");
        assertEquals(92, results.size(), "Number of solutions for n=4 should be 2");
        System.out.printf("Time execution: %.3f ms%n for testMySolution", executionTime);
    }

    @Test
    public void testBestSolution() {
        int n = 8;
        long startTime = System.nanoTime();
        List<List<String>> results = bestSolution.solveNQueens(n);
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;

        assertNotNull(results, "Result should not be null");
        assertEquals(92, results.size(), "Number of solutions for n=4 should be 2");
        System.out.printf("Time execution: %.3f ms%n for testBestSolution",  executionTime);
    }


    @AfterEach
    public void tearDown() {
        this.mySolution = null;
        this.bestSolution = null;
    }

}
