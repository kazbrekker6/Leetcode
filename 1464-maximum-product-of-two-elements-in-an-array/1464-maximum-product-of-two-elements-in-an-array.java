class Solution {
    public int maxProduct(int[] nums) {
        int maxDigit = 0;
        int secondMaxDigit = 0;

        for(int i = 0; i < nums.length; i++){
            int currentDigit = nums[i];

            if(currentDigit > maxDigit){
                secondMaxDigit = maxDigit;
                maxDigit = currentDigit;
            }
            else if(currentDigit > secondMaxDigit){
                secondMaxDigit = currentDigit;
            }
        }
        return (maxDigit - 1) * (secondMaxDigit - 1);
    }
}