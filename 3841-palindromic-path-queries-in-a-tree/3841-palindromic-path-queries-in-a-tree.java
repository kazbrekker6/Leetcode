class Solution {
    int n;
    List<Integer>[] tree;
    int[] parent, depth, heavy, head, pos;
    int[] value;
    int curPos;
    SegmentTree segTree;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }
        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        value = new int[n];
        Arrays.fill(heavy, -1);

        dfs(0, -1);
        curPos = 0;
        decompose(0, 0);
        segTree = new SegmentTree(n);

        for (int i = 0; i < n; i++) {
            value[i] = 1 << (s.charAt(i) - 'a');
            segTree.update(pos[i], value[i]);
        }

        List<Boolean> answer = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int node = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                value[node] = 1 << (c - 'a');
                segTree.update(pos[node], value[node]);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int mask = queryPath(u, v);
                answer.add(mask == 0 || (mask & (mask - 1)) == 0);
            }
        }
        return answer;
    }
    int dfs(int v, int p) {
        parent[v] = p;
        int size = 1, maxSub = 0;
        for (int c : tree[v]) {
            if (c != p) {
                depth[c] = depth[v] + 1;
                int subSize = dfs(c, v);
                size += subSize;
                if (subSize > maxSub) {
                    maxSub = subSize;
                    heavy[v] = c;
                }
            }
        }
        return size;
    }

    void decompose(int v, int h) {
        head[v] = h;
        pos[v] = curPos++;
        if (heavy[v] != -1) decompose(heavy[v], h);
        for (int c : tree[v]) {
            if (c != parent[v] && c != heavy[v]) decompose(c, c);
        }
    }

    int queryPath(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            res ^= segTree.query(pos[head[u]], pos[u]);
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= segTree.query(pos[u], pos[v]);
        return res;
    }

    static class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int idx, int val) { update(1, 0, n - 1, idx, val); }
        void update(int node, int l, int r, int idx, int val) {
            if (l == r) { tree[node] = val; return; }
            int mid = (l + r) / 2;
            if (idx <= mid) update(node * 2, l, mid, idx, val);
            else update(node * 2 + 1, mid + 1, r, idx, val);
            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        int query(int ql, int qr) { return query(1, 0, n - 1, ql, qr); }
        int query(int node, int l, int r, int ql, int qr) {
            if (qr < l || ql > r) return 0;
            if (ql <= l && r <= qr) return tree[node];
            int mid = (l + r) / 2;
            return query(node * 2, l, mid, ql, qr) ^
                   query(node * 2 + 1, mid + 1, r, ql, qr);
        }
    }
}