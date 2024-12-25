import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.MySolution;
import task.Solution;
import task.TreeNode;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSerializeDesererialize {

    private final Solution solution = new MySolution();
    private TreeNode testRoot;

    @BeforeEach
    public void setUp() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        this.testRoot = root;
    }

    @Test
    void testSerializeDeserialize() {
        String serialized = solution.serialize(testRoot);
        assertNotNull(serialized);
        System.out.println("Serialized tree: " + serialized);
        TreeNode deserializedRoot = solution.deserialize(serialized);

        assertTrue(testRoot.equals(deserializedRoot));
    }

    @AfterEach
    public void tearDown() {
        this.testRoot = null;
    }
}
