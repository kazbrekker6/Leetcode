class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        final long INF = Long.MAX_VALUE / 4;

        // Step 1: Sliding window median → cost for each size-x window
        long[] cost = new long[n - x + 1];

        TreeMap<Integer, Integer> lo = new TreeMap<>(Collections.reverseOrder());
        TreeMap<Integer, Integer> hi = new TreeMap<>();
        long loSum = 0, hiSum = 0;
        int loSize = 0, hiSize = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];

            // Insert into appropriate half
            if (loSize == 0 || val <= lo.firstKey()) {
                lo.merge(val, 1, Integer::sum);
                loSize++; loSum += val;
            } else {
                hi.merge(val, 1, Integer::sum);
                hiSize++; hiSum += val;
            }

            // Rebalance: lo holds ceil(x/2) elements
            int targetLo = (x + 1) / 2;
            while (loSize > targetLo) {
                int mv = lo.firstKey();
                decrement(lo, mv); loSize--; loSum -= mv;
                hi.merge(mv, 1, Integer::sum); hiSize++; hiSum += mv;
            }
            while (loSize < targetLo && hiSize > 0) {
                int mv = hi.firstKey();
                decrement(hi, mv); hiSize--; hiSum -= mv;
                lo.merge(mv, 1, Integer::sum); loSize++; loSum += mv;
            }

            // Slide out the oldest element
            if (i >= x) {
                int out = nums[i - x];
                if (lo.containsKey(out)) {
                    decrement(lo, out); loSize--; loSum -= out;
                } else {
                    decrement(hi, out); hiSize--; hiSum -= out;
                }
                while (loSize > targetLo) {
                    int mv = lo.firstKey();
                    decrement(lo, mv); loSize--; loSum -= mv;
                    hi.merge(mv, 1, Integer::sum); hiSize++; hiSum += mv;
                }
                while (loSize < targetLo && hiSize > 0) {
                    int mv = hi.firstKey();
                    decrement(hi, mv); hiSize--; hiSum -= mv;
                    lo.merge(mv, 1, Integer::sum); loSize++; loSum += mv;
                }
            }

            // Window is full → record cost
            if (i >= x - 1) {
                int median = lo.firstKey();
                cost[i - x + 1] = (long) median * loSize - loSum
                                + hiSum - (long) median * hiSize;
            }
        }

        // Step 2: DP — pick k non-overlapping windows
        // dp[j][i] = min cost to pick j windows from first i elements
        long[][] dp = new long[k + 1][n + 1];
        for (int j = 0; j <= k; j++) Arrays.fill(dp[j], INF);
        for (int i = 0; i <= n; i++) dp[0][i] = 0;

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                dp[j][i] = dp[j][i - 1]; // skip
                if (i >= x && dp[j - 1][i - x] < INF) {
                    dp[j][i] = Math.min(dp[j][i], dp[j - 1][i - x] + cost[i - x]);
                }
            }
        }

        return dp[k][n];
    }

    private void decrement(TreeMap<Integer, Integer> map, int key) {
        int cnt = map.get(key);
        if (cnt == 1) map.remove(key);
        else map.put(key, cnt - 1);
    }
}   