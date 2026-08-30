class Solution {
    public int minimumDeletions(int[] nums) {
       int n = nums.length;

       // 1. Initialize indices to 0 to prevent compilation errors
       int minEIdx = 0;
       int maxEIdx = 0;

       int min = nums[0];
       int max = nums[0];

       for (int i = 0; i < nums.length; i++) {
           // 2. Separate if statements to handle duplicates/single elements correctly
           if (nums[i] < min) {
               min = nums[i];  // 3. Critically update the tracking values
               minEIdx = i;
            } 
           if (nums[i] > max) {
               max = nums[i];  // 3. Critically update the tracking values
               maxEIdx = i;
            }
        }

        int left = Math.min(minEIdx, maxEIdx);
        int right = Math.max(minEIdx, maxEIdx);

        // This line is now perfectly safe and correct!
        return Math.min(left + 1 + n - right, Math.min(right + 1, n - left));
    }
}