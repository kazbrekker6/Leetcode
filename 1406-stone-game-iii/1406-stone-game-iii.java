class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        
        // These represent dp[i+1], dp[i+2], and dp[i+3]
        // Base case: out-of-bound states yield 0 points
        int next1 = 0, next2 = 0, next3 = 0;
        
        // Loop backwards from the last stone
        for (int i = n - 1; i >= 0; i--) {
            int currentI = Integer.MIN_VALUE;
            int takeSum = 0;
            
            // Try taking 1, 2, or 3 stones
            for (int k = 1; k <= 3; k++) {
                if (i + k - 1 < n) {
                    takeSum += stoneValue[i + k - 1];
                    
                    // Select the opponent state depending on how many stones we took
                    int opponentScore = (k == 1) ? next1 : (k == 2) ? next2 : next3;
                    
                    currentI = Math.max(currentI, takeSum - opponentScore);
                }
            }
            
            // Shift the values backwards to prepare for the next iteration
            next3 = next2;
            next2 = next1;
            next1 = currentI;
        }
        
        // next1 now holds the net score advantage for Alice starting at index 0
        if (next1 > 0) return "Alice";
        if (next1 < 0) return "Bob";
        return "Tie";
    }
}
