class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;
        // Store (number, count) pairs in non-increasing order.
        // The queue represents what nums[i..j] look like after adjustments.
        Deque<int[]> dq = new ArrayDeque<>();

        for (int i = nums.length - 1, j = nums.length - 1; i >= 0; --i) {
            final int num = nums[i];
            int count = 1;
            while (!dq.isEmpty() && dq.getLast()[0] < num) {
                final int nextNum = dq.getLast()[0];
                final int nextCount = dq.removeLast()[1];
                count += nextCount;
                // Adjust nextNum's to num.
                cost += (long) (num - nextNum) * nextCount;
            }
            dq.offerLast(new int[]{num, count});
            while (cost > k) {
                final int rightmostNum = dq.getFirst()[0];
                final int rightmostCount = dq.removeFirst()[1];
                cost -= (long) (rightmostNum - nums[j--]);
                if (rightmostCount > 1)
                    dq.offerFirst(new int[]{rightmostNum, rightmostCount - 1});
            }
            ans += j - i + 1;
        }

        return ans;
    }
}   