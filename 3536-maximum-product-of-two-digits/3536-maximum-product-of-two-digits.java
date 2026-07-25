class Solution {
    public int maxProduct(int n) {
        int maxDigit = 0;
        int secondMaxDigit = 0;

        while(n > 0){
            int currentDigit = n % 10;

            if(currentDigit > maxDigit){
                secondMaxDigit = maxDigit;
                maxDigit = currentDigit;
            }
            else if(currentDigit > secondMaxDigit){
                secondMaxDigit = currentDigit;
            }
            n = n/10;
        }
        return maxDigit * secondMaxDigit;
    }
}