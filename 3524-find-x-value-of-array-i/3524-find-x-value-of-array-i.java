class Solution {
    public long[] resultArray(int[] nums, int k) {
         long[] ans = new long[k];
        // dp[r] stores the count of subarrays ending at the current position with product % k == r
        long[] dp = new long[k];
        
        for (int num : nums) {
            long[] newDp = new long[k];
            int numMod = num % k;
            
            // Option 1: Start a new subarray with only the current element `num`
            newDp[numMod] = 1;
            
            // Option 2: Extend all previous subarrays ending right before `num`
            for (int i = 0; i < k; ++i) {
                int newMod = (int) (1L * i * numMod % k);
                newDp[newMod] += dp[i];
            }
            
            // Accumulate counts for each remainder into the final answer array
            for (int i = 0; i < k; ++i) {
                ans[i] += newDp[i];
            }
            
            dp = newDp;
        }
        
        return ans;
    }
}