class Solution {
    static class Node {
        int[] remain;
        int prod;

        Node(int k) {
            remain = new int[k];
            prod = 1;
        }
    }

    private int n;
    private int k;
    private Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];
        
        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node(k);
        }

        build(nums, 0, 0, n - 1);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Persistent Update
            update(0, 0, n - 1, idx, val % k);

            // 2. Query range from [start, n - 1]
            Node resNode = query(0, 0, n - 1, start, n - 1);
            ans[i] = resNode.remain[x];
        }

        return ans;
    }

    private void build(int[] nums, int cur, int left, int right) {
        if (left == right) {
            int val = nums[left] % k;
            Arrays.fill(tree[cur].remain, 0);
            tree[cur].remain[val] = 1;
            tree[cur].prod = val;
            return;
        }
        int mid = left + (right - left) / 2;
        build(nums, 2 * cur + 1, left, mid);
        build(nums, 2 * cur + 2, mid + 1, right);
        merge(tree[cur], tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private void update(int cur, int left, int right, int idx, int val) {
        if (left == right) {
            Arrays.fill(tree[cur].remain, 0);
            tree[cur].remain[val] = 1;
            tree[cur].prod = val;
            return;
        }
        int mid = left + (right - left) / 2;
        if (idx <= mid) {
            update(2 * cur + 1, left, mid, idx, val);
        } else {
            update(2 * cur + 2, mid + 1, right, idx, val);
        }
        merge(tree[cur], tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private Node query(int cur, int left, int right, int ql, int qr) {
        if (ql <= left && right <= qr) {
            return tree[cur];
        }
        int mid = left + (right - left) / 2;
        if (qr <= mid) {
            return query(2 * cur + 1, left, mid, ql, qr);
        }
        if (ql > mid) {
            return query(2 * cur + 2, mid + 1, right, ql, qr);
        }
        
        Node leftResult = query(2 * cur + 1, left, mid, ql, qr);
        Node rightResult = query(2 * cur + 2, mid + 1, right, ql, qr);
        Node parent = new Node(k);
        merge(parent, leftResult, rightResult);
        return parent;
    }

    private void merge(Node parent, Node left, Node right) {
        parent.prod = (left.prod * right.prod) % k;
        
        // Copy left child's prefix options directly
        for (int j = 0; j < k; j++) {
            parent.remain[j] = left.remain[j];
        }
        
        // Add right child's prefix options shifted by the total product of the left child
        for (int j = 0; j < k; j++) {
            if (right.remain[j] > 0) {
                int combinedMod = (left.prod * j) % k;
                parent.remain[combinedMod] += right.remain[j];
            }
        }
    }
}
