class Solution {
    public int maximumProduct(int[] nums) {
        int maxDigit = Integer.MIN_VALUE;
        int secondMaxDigit = Integer.MIN_VALUE;
        int thirdMaxDigit = Integer.MIN_VALUE;

        int minDigit = Integer.MAX_VALUE;
        int secondMinDigit = Integer.MAX_VALUE;
        
        for(int i = 0; i < nums.length; i++){
            int currentDigit = nums[i];
            if(currentDigit > maxDigit){
                thirdMaxDigit = secondMaxDigit;
                secondMaxDigit = maxDigit;
                maxDigit = currentDigit;
            }
            else if(currentDigit > secondMaxDigit){
                thirdMaxDigit = secondMaxDigit;
                secondMaxDigit = currentDigit;
            }
            else if(currentDigit > thirdMaxDigit){
                thirdMaxDigit = currentDigit;
            }

            if(currentDigit < minDigit){
                secondMinDigit = minDigit;
                minDigit = currentDigit;
            }
            else if(currentDigit < secondMinDigit){
                secondMinDigit = currentDigit;
            }
        }
        return Math.max(maxDigit*secondMaxDigit*thirdMaxDigit, minDigit*secondMinDigit*maxDigit);
    }
}