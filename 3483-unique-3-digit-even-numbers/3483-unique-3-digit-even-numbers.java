class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        int n = digits.length;

        // Loop through all possible combinations using distinct indices
        for (int i = 0; i < n; i++) {
            // The hundreds place cannot be 0
            if (digits[i] == 0) {
                continue;
            }

            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue; 
                }

                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue; 
                    }

                    // The units place must be even
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    // Form the 3-digit number
                    int currentNum = digits[i] * 100 + digits[j] * 10 + digits[k];
                    uniqueNumbers.add(currentNum);
                }
            }
        }

        // Return the count of unique valid numbers found
        return uniqueNumbers.size();
    }
}
