class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        
        // Commas appear at intervals of 1,000 (10^3, 10^6, 10^9...)
        // We use a long loop to avoid integer overflow due to the 10^15 constraint.
        for (long threshold = 1000; threshold <= n; threshold *= 1000) {
            // Numbers from 'threshold' up to 'n' each contribute at least one comma 
            // for the current comma position.
            totalCommas += (n - threshold + 1);
        }
        
        return totalCommas;
    }
}