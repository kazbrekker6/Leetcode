class Solution {
    private int[][] memo;
    private int[] suffixSum;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // memo[i][M] stores the max stones a player can get starting at index i with multiplier M
        memo = new int[n][n + 1]; 
        
        // suffixSum[i] stores the total stones from index i to the end
        suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        return getmaxStones(0, 1, n);
    }

    private int getmaxStones(int i, int M, int n) {
        // Base Case: No piles left
        if (i >= n) return 0;
        
        // If we can take all remaining piles, do it!
        if (i + 2 * M >= n) return suffixSum[i];

        // Return cached result if already calculated
        if (memo[i][M] != 0) return memo[i][M];

        int maxStones = 0;

        // Try taking X piles, where 1 <= X <= 2M
        for (int X = 1; X <= 2 * M; X++) {
            // Opponent's score from the next turn
            int opponentScore = getmaxStones(i + X, Math.max(M, X), n);
            
            // Current player's score is total remaining stones minus what the opponent gets
            int currentScore = suffixSum[i] - opponentScore;
            
            maxStones = Math.max(maxStones, currentScore);
        }

        return memo[i][M] = maxStones;
    }
}
