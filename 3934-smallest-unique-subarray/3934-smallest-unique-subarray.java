class Solution {
    static final long M1 = 1_000_000_007L;
    static final long M2 = 1_000_000_009L;
    static final long B  = 131;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] p1 = new long[n + 1], p2 = new long[n + 1];
        long[] h1 = new long[n + 1], h2 = new long[n + 1];
        p1[0] = p2[0] = 1;
        for (int i = 0; i < n; i++) {
            p1[i + 1] = p1[i] * B % M1;
            p2[i + 1] = p2[i] * B % M2;
            h1[i + 1] = (h1[i] * B + nums[i]) % M1;
            h2[i + 1] = (h2[i] * B + nums[i]) % M2;
        }

        int lo = 1, hi = n, ans = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (check(mid, h1, h2, p1, p2, n)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    private boolean check(int len, long[] h1, long[] h2, long[] p1, long[] p2, int n) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int i = 0; i + len <= n; i++) {
            long a = (h1[i + len] - h1[i] * p1[len] % M1 + M1) % M1;
            long b = (h2[i + len] - h2[i] * p2[len] % M2 + M2) % M2;
            long key = a * M2 + b;          // pair (a, b) packed into one long
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }
        for (int v : freq.values())
            if (v == 1) return true;
        return false;
    }
}   