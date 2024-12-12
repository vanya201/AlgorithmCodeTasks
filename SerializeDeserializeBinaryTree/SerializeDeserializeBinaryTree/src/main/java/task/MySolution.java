package task;

public class MySolution implements Solution {

    //O(n)
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfsSerialize(root, sb);
        return sb.toString();
    }

    //O(n)
    private void dfsSerialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("n ");
            return;
        }

        sb.append(node.val).append(" ");
        dfsSerialize(node.left, sb);
        dfsSerialize(node.right, sb);
    }

    //O(n)
    public TreeNode deserialize(String data) {
        if (data.equals(""))
            return null;

        String[] values = data.split(" ");
        int[] index = { 0 };
        return dfsDeserialize(values, index);
    }

    //O(n)
    private TreeNode dfsDeserialize(String[] values, int[] index) {
        if (index[0] >= values.length || values[index[0]].equals("n")) {
            index[0]++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(values[index[0]++]));
        node.left = dfsDeserialize(values, index);
        node.right = dfsDeserialize(values, index);
        return node;
    }
}
