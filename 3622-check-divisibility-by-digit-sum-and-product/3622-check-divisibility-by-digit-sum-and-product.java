class Solution {
    public boolean checkDivisibility(int n) {
        int sum = 0;
        int product = 1;
        int i = n;

        while (i > 0){
            int last = i % 10;

            sum += last;
            product *= last;

            i = i / 10;
        }
        
        int s = sum + product;
        if(n % s == 0){
            return true;
        }
        return false;
    }
}