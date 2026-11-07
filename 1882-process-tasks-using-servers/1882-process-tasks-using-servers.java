class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length, m = tasks.length;
        int[] ans = new int[m];

        // Idle: [weight, index]
        PriorityQueue<int[]> idle = new PriorityQueue<>((a, b) ->
            a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        // Busy: [freeTime, weight, index]
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) ->
            a[0] != b[0] ? a[0] - b[0] :
            a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]);

        for (int i = 0; i < n; i++)
            idle.offer(new int[] { servers[i], i });

        for (int j = 0; j < m; j++) {
            // Free up servers whose time has passed
            while (!busy.isEmpty() && busy.peek()[0] <= j) {
                int[] s = busy.poll();
                idle.offer(new int[] { s[1], s[2] });
            }

            if (!idle.isEmpty()) {
                int[] s = idle.poll();
                ans[j] = s[1];
                busy.offer(new int[] { j + tasks[j], s[0], s[1] });
            } else {
                // All servers busy — chain onto the earliest one
                int[] s = busy.poll();
                ans[j] = s[2];
                busy.offer(new int[] { s[0] + tasks[j], s[1], s[2] });
            }
        }
        return ans;
    }
}   