class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        int moves = 0;

        Arrays.sort(nums);

        int mid = nums[n/2];

        for(int i = 0; i < n/2; i++){
            moves += Math.abs(mid - nums[i]);
            moves += Math.abs(mid - nums[n-i-1]);
        }
        return moves;
    }
}