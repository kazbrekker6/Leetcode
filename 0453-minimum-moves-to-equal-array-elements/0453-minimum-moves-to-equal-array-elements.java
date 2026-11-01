class Solution {
    public int minMoves(int[] nums) {
        int minEle = Integer.MAX_VALUE;
        int sum = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] < minEle){
                minEle = nums[i];
            }
            sum += nums[i];
        }
        return sum - (nums.length * minEle);
    }
}