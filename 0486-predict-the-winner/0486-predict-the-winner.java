class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        
        // Base optimization: If the array length is even, 
        // Player 1 can always guarantee a win by picking all odd or all even indices.
        if (n % 2 == 0) {
            return true;
        }
        
        int[][] dp = new int[n][n];
        
        // Base case: When only 1 element is left, the player takes that element.
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        
        // Fill the DP table by sub-array length moving upwards
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        
        // If Player 1's max relative score over Player 2 is >= 0, Player 1 wins.
        return dp[0][n - 1] >= 0;
    }
}
