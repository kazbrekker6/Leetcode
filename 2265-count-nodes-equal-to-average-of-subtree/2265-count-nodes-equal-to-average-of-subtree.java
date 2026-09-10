/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int matchingNodes = 0;
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return matchingNodes;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0}; // {sum, count}
        }

        // Post-order traversal: Postpone processing until children finish
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        // Accumulate totals for the current node's subtree
        int currentSum = node.val + left[0] + right[0];
        int currentCount = 1 + left[1] + right[1];

        // Java integer division automatically rounds down toward zero
        if (currentSum / currentCount == node.val) {
            matchingNodes++;
        }

        return new int[]{currentSum, currentCount};
    }
}