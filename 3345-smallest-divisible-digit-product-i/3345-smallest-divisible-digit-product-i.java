class Solution {
    public int smallestNumber(int n, int t) {
        // Start from n and keep increasing until we find a match
        while (true) {
            int product = getProduct(n);
            if (product % t == 0) {
                return n; // Found the smallest valid number
            }
            n++; // Move to the next number
        }
    }

    private int getProduct(int num) {
        // Edge case: if the number is 0, the product of its digits is 0
        if (num == 0) return 0; 
        
        int product = 1;
        while (num > 0) {
            int digit = num % 10;
            product = product * digit;
            num = num / 10;
        }
        return product;
    }
}
