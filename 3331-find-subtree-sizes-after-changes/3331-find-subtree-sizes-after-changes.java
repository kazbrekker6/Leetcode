class Solution {
    private List<Integer>[] children;   // original tree adjacency
    private List<Integer>[] charStack;  // stack of nodes per character on current path
    private char[] s;
    private int[] ans;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = s.length();
        this.s = s.toCharArray();
        children = new List[n];
        charStack = new List[26];
        Arrays.setAll(children, i -> new ArrayList<>());
        Arrays.setAll(charStack, i -> new ArrayList<>());

        // Build adjacency list from parent array
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        ans = new int[n];
        dfs(0, -1);
        return ans;
    }

    private void dfs(int node, int parent) {
        ans[node] = 1;  // count self

        int c = s[node] - 'a';
        charStack[c].add(node);  // push onto this character's stack

        // Process all children first (post-order)
        for (int child : children[node]) {
            dfs(child, node);
        }

        // Find new parent: closest same-char ancestor (2nd-to-last on stack),
        // or original parent if none exists
        int newParent = charStack[c].size() > 1
                ? charStack[c].get(charStack[c].size() - 2)
                : parent;

        // Add this node's subtree size to its new parent
        if (newParent >= 0) {
            ans[newParent] += ans[node];
        }

        charStack[c].remove(charStack[c].size() - 1);  // backtrack
    }
}   