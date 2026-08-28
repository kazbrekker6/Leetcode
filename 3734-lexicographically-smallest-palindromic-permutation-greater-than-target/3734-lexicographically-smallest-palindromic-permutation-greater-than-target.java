public class Solution {
    private String result = null;
    private int n;
    private String targetStr;
    private char oddChar = 0;

    public String lexPalindromicPermutation(String s, String target) {
        this.n = s.length();
        this.targetStr = target;
        this.result = null;
        this.oddChar = 0;

        // 1. Calculate character frequencies
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        // 2. Validate palindrome capability and extract odd character
        int oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] % 2 != 0) {
                oddCount++;
                oddChar = (char) ('a' + i);
            }
        }
        if (oddCount > 1) {
            return "";
        }

        // 3. Prepare the half-frequencies array for the left side
        int[] halfCounts = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCounts[i] = counts[i] / 2;
        }

        // 4. Backtrack to find the smallest valid left half
        int halfLen = n / 2;
        char[] currentLeft = new char[halfLen];
        
        if (backtrack(0, halfLen, halfCounts, currentLeft, true)) {
            return result;
        }

        return "";
    }

    private boolean backtrack(int index, int halfLen, int[] halfCounts, char[] currentLeft, boolean isSameAsPrefix) {
        // Base Case: Left half is fully constructed
        if (index == halfLen) {
            String candidate = constructFullPalindrome(currentLeft);
            if (candidate.compareTo(targetStr) > 0) {
                result = candidate;
                return true; // Stop on the first lexicographically smallest match
            }
            return false;
        }

        char targetChar = targetStr.charAt(index);
        
        // Loop through all lowercase English characters in alphabetical order
        for (int i = 0; i < 26; i++) {
            if (halfCounts[i] == 0) continue;

            char c = (char) ('a' + i);

            // If we are currently tracking the target's prefix matching string
            if (isSameAsPrefix) {
                if (c < targetChar) {
                    continue; // Skip characters that would make the candidate smaller
                }
                
                halfCounts[i]--;
                currentLeft[index] = c;
                
                // Track if this choice continues the prefix match or deviates to a larger value
                boolean nextIsSame = (c == targetChar);
                if (backtrack(index + 1, halfLen, halfCounts, currentLeft, nextIsSame)) {
                    return true;
                }
                
                // Backtrack step
                halfCounts[i]++;
            } else {
                // Greedily take the smallest available character if we've already exceeded the target prefix
                halfCounts[i]--;
                currentLeft[index] = c;
                
                if (backtrack(index + 1, halfLen, halfCounts, currentLeft, false)) {
                    return true;
                }
                
                halfCounts[i]++;
            }
        }
        return false;
    }

    private String constructFullPalindrome(char[] leftHalf) {
        StringBuilder sb = new StringBuilder();
        sb.append(leftHalf);
        
        String left = sb.toString();
        String right = sb.reverse().toString();
        
        if (n % 2 != 0) {
            return left + oddChar + right;
        } else {
            return left + right;
        }
    }
}
