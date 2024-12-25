package task;

public interface Solution {
    String serialize(TreeNode root);
    TreeNode deserialize(String data);
    // Not SOLID, but it is task
}
