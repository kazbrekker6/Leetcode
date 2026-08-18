class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[51]; // Fixed-size frequency array
        
        // 1. Count the occurrences of each number globally
        for (int num : nums) {
            count[num]++;
        }
        
        // CASE 1: k is equal to the full array size
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }
        
        // CASE 2: k equals 1 (Return the largest globally unique number)
        if (k == 1) {
            for (int i = 50; i >= 1; i--) {
                if (count[i] == 1) {
                    return i; // Found the largest unique element
                }
            }
            return -1;
        }
        
        // CASE 3: 1 < k < n
        // Only the very first or very last elements can appear in exactly one window
        int firstElement = nums[0];
        int lastElement = nums[n - 1];
        int result = -1;
        
        if (count[firstElement] == 1) {
            result = Math.max(result, firstElement);
        }
        if (count[lastElement] == 1) {
            result = Math.max(result, lastElement);
        }
        
        return result;
    }
}
