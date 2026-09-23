class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int target = totalSum - x;
        if (target < 0) return -1; // If target is negative, x is greater than total sum
        
        int left = 0, currentSum = 0;
        int maxLen = -1;
        int n = nums.length;
        
        for (int right = 0; right < n; right++) {
            currentSum += nums[right];
            
            // Shrink the window from the left if current sum exceeds target
            while (currentSum > target) {
                currentSum -= nums[left];
                left++;
            }
            
            // Check if we found a valid subarray matching the target sum
            if (currentSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        
        // If maxLen is -1, no such subarray exists; otherwise, total length - maxLen
        return maxLen == -1 ? -1 : n - maxLen;
    }
}
