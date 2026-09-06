class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[i][j] stores the number of distinct subsequences of s[0...i-1] which equals t[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Base case: An empty string t can always be formed by deleting all characters of s.
        // There is exactly 1 way to do this for any prefix of s.
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match, we have two choices:
                // 1. Match s.charAt(i-1) with t.charAt(j-1) -> dp[i-1][j-1]
                // 2. Ignore s.charAt(i-1) and look for t.charAt(j-1) in earlier parts of s -> dp[i-1][j]
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // If characters don't match, we must ignore s.charAt(i-1)
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[m][n];
    }
}
