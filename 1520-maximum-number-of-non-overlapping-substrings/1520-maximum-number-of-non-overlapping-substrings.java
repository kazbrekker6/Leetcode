public class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] leftmost = new int[26];
        int[] rightmost = new int[26];
        
        // Initialize boundaries
        Arrays.fill(leftmost, n);
        Arrays.fill(rightmost, -1);
        
        // Step 1: Record the leftmost and rightmost index for each character
        for (int i = 0; i < n; i++) {
            int charIdx = s.charAt(i) - 'a';
            leftmost[charIdx] = Math.min(leftmost[charIdx], i);
            rightmost[charIdx] = i;
        }
        
        List<String> result = new ArrayList<>();
        int lastRightBound = -1; // Tracks the right bound of the last accepted substring
        
        // Step 2: Iterate through the string. 
        // We only start validating substrings at the first occurrence of any character.
        for (int i = 0; i < n; i++) {
            int charIdx = s.charAt(i) - 'a';
            if (i == leftmost[charIdx]) {
                int newRightBound = checkValidInterval(s, i, leftmost, rightmost);
                
                // If it's a valid standalone or greedily better interval
                if (newRightBound != -1) {
                    // Case 1: The current substring overlaps with the previous one.
                    // Because we traverse left to right, if it overlaps, it must be completely 
                    // contained within the previous one. We replace it to minimize total length.
                    if (i <= lastRightBound && !result.isEmpty()) {
                        result.set(result.size() - 1, s.substring(i, newRightBound + 1));
                    } else {
                        // Case 2: No overlap, safely append a new substring.
                        result.add(s.substring(i, newRightBound + 1));
                    }
                    lastRightBound = newRightBound;
                }
            }
        }
        
        return result;
    }
    
    // Helper function to dynamically expand and check the interval validity
    private int checkValidInterval(String s, int left, int[] leftmost, int[] rightmost) {
        int right = rightmost[s.charAt(left) - 'a'];
        
        for (int j = left; j <= right; j++) {
            int charIdx = s.charAt(j) - 'a';
            
            // If any character inside our current boundary appeared before 'left',
            // this starting index 'left' is invalid.
            if (leftmost[charIdx] < left) {
                return -1;
            }
            // Dynamically extend the right boundary to include all occurrences of character 'j'
            right = Math.max(right, rightmost[charIdx]);
        }
        
        return right;
    }
}
