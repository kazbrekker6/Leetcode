class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int m = queries.length;
        int[][] qs = new int[m][2];
        for (int i = 0; i < m; ++i)
            qs[i] = new int[] {queries[i], i};
        Arrays.sort(qs, (a, b) -> a[0] - b[0]);

        int[] count = new int[n + 1];
        int[] ans = new int[m];
        int activeServers = 0;
        int i = 0, j = 0;

        for (int[] q : qs) {
            int r = q[0], idx = q[1];
            int l = r - x;

            while (j < logs.length && logs[j][1] <= r) {
                if (++count[logs[j][0]] == 1)
                    ++activeServers;
                ++j;
            }
            while (i < logs.length && logs[i][1] < l) {
                if (--count[logs[i][0]] == 0)
                    --activeServers;
                ++i;
            }
            ans[idx] = n - activeServers;
        }
        return ans;
    }
}   