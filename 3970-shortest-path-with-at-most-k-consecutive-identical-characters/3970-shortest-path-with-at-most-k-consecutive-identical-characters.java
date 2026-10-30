class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        char[] label = labels.toCharArray();
        long INF = Long.MAX_VALUE / 2;
        // dist[node][run] = min cost to reach node with 'run' consecutive same chars
        long[][] dist = new long[n][k + 1];
        for (long[] d : dist) Arrays.fill(d, INF);

        // Priority queue: (cost, node, run)
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long cost = cur[0];
            int node = (int) cur[1];
            int run  = (int) cur[2];

            if (cost > dist[node][run]) continue;
            if (node == n - 1) return (int) cost;

            for (int[] next : adj[node]) {
                int v = next[0], w = next[1];
                int newRun = (label[node] == label[v]) ? run + 1 : 1;

                if (newRun <= k && cost + w < dist[v][newRun]) {
                    dist[v][newRun] = cost + w;
                    pq.offer(new long[]{cost + w, v, newRun});
                }
            }
        }

        return -1;
    }
}