class Solution {
    public int[] validSequence(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[] ans = new int[n2];
        int[] last = new int[n2];
        Arrays.fill(last, -1);

        // Precompute the last matching index in word1 for each suffix of word2
        int i = n1 - 1;
        int j = n2 - 1;
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }

        boolean canSkip = true;
        j = 0;
        // Greedily match word1 and word2
        for (i = 0; i < n1; ++i) {
            if (j == n2) break;
            
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j++] = i;
            } else if (canSkip && (j == n2 - 1 || i < last[j + 1])) {
                canSkip = false; // Use our single allowed change
                ans[j++] = i;
            }
        }

        return j == n2 ? ans : new int[0];
    }
}