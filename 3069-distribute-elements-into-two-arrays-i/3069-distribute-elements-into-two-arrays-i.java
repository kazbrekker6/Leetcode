import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] resultArray(int[] nums) {
        // 1. Create two dynamic lists
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        
        // 2. Add the first two elements as required by the rules
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        
        // 3. Loop through the rest of the numbers and distribute them
        for (int i = 2; i < nums.length; i++) {
            int last1 = arr1.get(arr1.size() - 1);
            int last2 = arr2.get(arr2.size() - 1);
            
            if (last1 > last2) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        
        // 4. Concatenate arr1 and arr2 into the final primitive int[] array
        int[] result = new int[nums.length];
        int index = 0;
        
        for (int num : arr1) {
            result[index++] = num;
        }
        for (int num : arr2) {
            result[index++] = num;
        }
        
        return result;
    }
}
