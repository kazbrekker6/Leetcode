class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        java.util.Arrays.fill(best, Integer.MAX_VALUE);
        
        int sum = 0;
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            
            if (sum == target) {
                int currLen = right - left + 1;
                // Check if there is a valid non-overlapping subarray before 'left'
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, best[left - 1] + currLen);
                }
                // Update the minimum length ending at or before 'right'
                minLen = Math.min(minLen, currLen);
            }
            
            best[right] = minLen;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
