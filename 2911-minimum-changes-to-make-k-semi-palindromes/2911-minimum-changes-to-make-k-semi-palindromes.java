class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        // Precompute divisors for each length
        List<Integer>[] factors = getFactors(n);
        // cost[i][j] = min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minCost = len;
                for (int d : factors[len])
                    minCost = Math.min(minCost, getCost(s, i, j, d));
                cost[i][j] = minCost;
            }
        // dp[i][j] = min changes to split s[i:] into j semi-palindromic parts
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) Arrays.fill(row, n);
        dp[n][0] = 0;
        for (int i = n - 1; i >= 0; i--)
            for (int j = 1; j <= k; j++)
                for (int l = i + 1; l < n; l++)
                    dp[i][j] = Math.min(dp[i][j], dp[l + 1][j - 1] + cost[i][l]);
        return dp[0][k];
    }

    private List<Integer>[] getFactors(int n) {
        List<Integer>[] factors = new List[n + 1];
        for (int i = 1; i <= n; i++)
            factors[i] = new ArrayList<>(List.of(1));
        for (int d = 2; d < n; d++)
            for (int i = d * 2; i <= n; i += d)
                factors[i].add(d);
        return factors;
    }

    // Min changes to make s[i..j] a semi-palindrome with period d
    private int getCost(String s, int i, int j, int d) {
        int cost = 0;
        for (int offset = 0; offset < d; offset++) {
            int l = i + offset, r = j - d + 1 + offset;
            while (l < r) {
                if (s.charAt(l) != s.charAt(r)) cost++;
                l += d; r -= d;
            }
        }
        return cost;
    }
}   