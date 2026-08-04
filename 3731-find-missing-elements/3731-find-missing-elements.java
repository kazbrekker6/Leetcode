class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> res = new ArrayList<> ();
        Set<Integer> presenceSet = new HashSet<> ();
        int min = nums[0];
        int max = nums[0];
        
        // Loop through the array to update min and max
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
            presenceSet.add(nums[i]);
        }

        for(int i = min; i <= max; i++){
            if(!presenceSet.contains(i)){
                res.add(i);
            }
        }
    return res;
    }
}