class Solution {
    public int minOperations(int[] nums) {
        int ans = -1;
        int zeroIdx = 0;
        int prev = nums[0];
        boolean forward = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                // new start
            } else if (nums[i] != (prev + 1)) {
                forward = false;
                break;
            }
            prev = nums[i];
        }
        if (forward) {
            ans = Math.min(zeroIdx, nums.length - zeroIdx + 2);
        }
        zeroIdx = nums.length - 1;
        prev = nums[nums.length - 1];
        boolean backward = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                zeroIdx = i;
                // new start
            } else if (nums[i] != (prev + 1)) {
                backward = false;
                break;
            }
            prev = nums[i];
        }
        if (backward) {
            int b = Math.min(nums.length - zeroIdx, zeroIdx + 2);
            if (ans == -1) {
                ans = b;
            } else {
                ans = Math.min(ans, b);
            }
        }
        return ans;
    }
}