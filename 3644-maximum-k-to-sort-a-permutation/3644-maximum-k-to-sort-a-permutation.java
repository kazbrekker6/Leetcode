class Solution {
    public int sortPermutation(int[] nums) {
        // Initialize result with -1 (all bits set to 1)
        int result = -1;

        // Iterate through each element in the array
        for (int i = 0; i < nums.length; ++i) {
            // Check if current element is not in its expected sorted position
            // (assuming sorted position means nums[i] should equal i)
            if (i != nums[i]) {
                // Perform bitwise AND with the misplaced element
                result &= nums[i];
            }
        }

        // Return the maximum of result and 0
        // This ensures non-negative output (returns 0 if result is negative)
        return Math.max(result, 0);
    }
}