class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        
        // Pair each number with its digit sum
        for (int i = 0; i < n; i++) {
            arr[i][0] = getDigitSum(nums[i]);
            arr[i][1] = nums[i];
        }
        
        // Sort by digit sum, then by value
        java.util.Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        
        // Map each value to its target index in the sorted array
        java.util.Map<Integer, Integer> targetIndex = new java.util.HashMap<>();
        for (int i = 0; i < n; i++) {
            targetIndex.put(arr[i][1], i);
        }
        
        // Count cycles to determine minimum swaps
        boolean[] visited = new boolean[n];
        int swaps = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            
            int cycleSize = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = targetIndex.get(nums[j]);
                cycleSize++;
            }
            
            // A cycle of size k requires k-1 swaps
            if (cycleSize > 0) {
                swaps += (cycleSize - 1);
            }
        }
        
        return swaps;
    }
    
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}   