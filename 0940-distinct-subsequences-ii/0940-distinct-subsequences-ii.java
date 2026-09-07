class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        // endsIn[i] stores the number of distinct subsequences that end with ('a' + i)
        long[] endsIn = new long[26];
        long currentTotal = 0;

        for (char c : s.toCharArray()) {
            int index = c - 'a';
            
            // New distinct subsequences ending in 'c' = (all existing ones + 1)
            long newSubseqCount = (currentTotal + 1) % MOD;
            
            // Update the total running count
            // We subtract what endsIn[index] used to hold to avoid double counting,
            // and add the newly formed subsequences.
            currentTotal = (currentTotal - endsIn[index] + newSubseqCount + MOD) % MOD;
            
            // Store the updated count for this character
            endsIn[index] = newSubseqCount;
        }

        return (int) currentTotal;
    }
}
