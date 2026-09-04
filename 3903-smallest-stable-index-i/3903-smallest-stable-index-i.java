class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int max = Arrays.stream(nums, 0, i+1).max().getAsInt();
            int min = Arrays.stream(nums, i, n).min().getAsInt();
            int instability_score = max - min;
            if(instability_score <= k){
                return i;
            }
        }
        return -1;
    }
}