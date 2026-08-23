class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        double leftSum = 0;
        double rightSum = 0;
        double leftQ = 0;
        double rightQ = 0;

        // Process the first half
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        // Process the second half
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        // Bob wins if the differences perfectly balance out
        // Alice wins if they do not match
        return (leftSum - rightSum) + (leftQ - rightQ) * 4.5 != 0;
    }
}
