class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] cnt = new int[k];
        // Min-heap of (endTime, serverId)
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[0])
        );
        // Sorted set of available server IDs
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; i++) free.add(i);

        for (int i = 0; i < arrival.length; i++) {
            int start = arrival[i];
            int end = start + load[i];

            // Free all servers that finished by now
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                free.add(busy.poll()[1]);
            }

            // Drop request if no server is available
            if (free.isEmpty()) continue;

            // Pick smallest available server >= (i % k), else wrap to first
            Integer server = free.ceiling(i % k);
            if (server == null) server = free.first();

            cnt[server]++;
            busy.offer(new int[]{end, server});
            free.remove(server);
        }

        // Collect all servers with the max count
        int max = 0;
        for (int c : cnt) max = Math.max(max, c);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++)
            if (cnt[i] == max) res.add(i);
        return res;
    }
}   