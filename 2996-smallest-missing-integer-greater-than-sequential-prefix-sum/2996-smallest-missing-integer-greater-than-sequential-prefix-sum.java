class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: Calculate the longest sequential prefix sum
        int prefixSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                prefixSum += nums[i];
            } else {
                break; // Sequence broke, stop adding
            }
        }
        // Step 2: Put all elements in a HashSet for O(1) lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        // Step 3: Find the smallest missing integer >= prefixSum
        while (numSet.contains(prefixSum)) {
            prefixSum++;
        }
        
        return prefixSum;

    }
}