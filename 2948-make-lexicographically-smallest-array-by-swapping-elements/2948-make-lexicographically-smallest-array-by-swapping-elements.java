public class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // 1. Store elements along with their original indices
        int[][] paired = new int[n][2];
        for (int i = 0; i < n; i++) {
            paired[i][0] = nums[i];
            paired[i][1] = i;
        }
        
        // 2. Sort pairs by their numerical values
        Arrays.sort(paired, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        int i = 0;
        
        // 3. Group elements where adjacent sorted values have a difference <= limit
        while (i < n) {
            int j = i;
            // Find the boundary of the current connected component
            while (j + 1 < n && paired[j + 1][0] - paired[j][0] <= limit) {
                j++;
            }
            
            // Extract the original indices for this component group
            int[] indices = new int[j - i + 1];
            for (int k = i; k <= j; k++) {
                indices[k - i] = paired[k][1];
            }
            
            // Sort the original indices to place values in leftmost available slots
            Arrays.sort(indices);
            
            // Place the sorted values back into the sorted positions
            for (int k = i; k <= j; k++) {
                result[indices[k - i]] = paired[k][0];
            }
            
            // Move to the next component
            i = j + 1;
        }
        
        return result;
    }
}
