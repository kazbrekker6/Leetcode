class Solution {
    public int numberOfSets(int n, int k) {
         if (n - 1 < k) {
            return 0;
        }
        int mod = 1_000_000_007;
        int[] dp = new int[k];
        int[] sums = new int[k];

        for (int diff = 1; diff < n - k + 1; diff++) {
            dp[0] = ((diff + 1) * diff) >> 1;
            sums[0] = (sums[0] + dp[0]) % mod;

            for (int segments = 2; segments <= k; segments++) {
                dp[segments - 1] = (sums[segments - 2] + dp[segments - 1]) % mod;
                sums[segments - 1] = (sums[segments - 1] + dp[segments - 1]) % mod;
            }
        }
        return dp[k - 1];
    }
}