class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        
        // Step 1: Find the smallest odd number
        for (int x : nums1) {
            if (x % 2 != 0) {
                minOdd = Math.min(minOdd, x);
            }
        }
        
        // If no odd numbers exist, all elements are even (Uniform)
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }
        // Step 2: Ensure no even number is smaller than the minimum odd number
        for (int x : nums1) {
            if (x % 2 == 0 && x < minOdd) {
                return false;
            }
        }
        
        return true;
    }
}