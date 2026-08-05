class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }

        boolean[] suspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);
        suspicious[k] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph.get(u)) {
                if (!suspicious[v]) {
                    suspicious[v] = true;
                    queue.add(v);
                }
            }
        }

        for (int[] inv : invocations) {
            if (!suspicious[inv[0]] && suspicious[inv[1]]) {
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) allMethods.add(i);
                return allMethods;
            }
        }

        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                remaining.add(i);
            }
        }
        return remaining;
    }
}
