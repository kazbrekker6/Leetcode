class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        
        // Count frequencies of each character in s
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        
        // Step 1: Greedily try to match the prefix of target
        int matched = 0;
        while (matched < n) {
            int c = target.charAt(matched) - 'a';
            if (count[c] == 0) {
                break;
            }
            count[c]--;
            matched++;
        }
        
        // Step 2: Backtrack from the rightmost matched index to find 
        // the first position where we can insert a strictly larger character.
        for (int pos = Math.min(matched, n - 1); pos >= 0; pos--) {
            // If we are backtracking past the matched prefix, 
            // restore the character that was consumed from count map.
            if (pos < matched) {
                count[target.charAt(pos) - 'a']++;
            }
            
            int targetChar = target.charAt(pos) - 'a';
            
            // Try to find the smallest available character strictly greater than target[pos]
            for (int j = targetChar + 1; j < 26; j++) {
                if (count[j] > 0) {
                    // Found a valid character to break the tie and make it strictly greater
                    count[j]--;
                    
                    // Construct the final string up to this point
                    StringBuilder sb = new StringBuilder();
                    // Append the unchanged matching target prefix
                    sb.append(target.substring(0, pos));
                    // Append the strictly greater character
                    sb.append((char) ('a' + j));
                    
                    // Fill the remainder with the remaining elements in sorted (smallest) order
                    for (int k = 0; k < 26; k++) {
                        while (count[k] > 0) {
                            sb.append((char) ('a' + k));
                            count[k]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }
        
        // If no valid permutation can be formed that is greater than target
        return "";
    }
}
