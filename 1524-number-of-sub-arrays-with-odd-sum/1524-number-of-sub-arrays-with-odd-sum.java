class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int[] cnt = {1, 0}; // cnt[0] = even prefix count, cnt[1] = odd prefix count
        int ans = 0, s = 0;
        for (int x : arr) {
            s += x;
            ans = (ans + cnt[s & 1 ^ 1]) % MOD; // add count of opposite-parity prefixes
            ++cnt[s & 1];
        }
        return ans;
    }
}   