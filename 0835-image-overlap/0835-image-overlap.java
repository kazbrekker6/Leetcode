public class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        
        // Lists to store the coordinates of 1s in both images
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    list1.add(new int[]{r, c});
                }
                if (img2[r][c] == 1) {
                    list2.add(new int[]{r, c});
                }
            }
        }
        
        // Map to count the frequencies of each unique shift vector
        // Key format: "deltaRow_deltaCol"
        Map<String, Integer> shiftCounts = new HashMap<>();
        int maxOverlap = 0;
        
        // Compare every pair of 1s between the two images
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                int dr = p1[0] - p2[0];
                int dc = p1[1] - p2[1];
                
                String vectorKey = dr + "_" + dc;
                shiftCounts.put(vectorKey, shiftCounts.getOrDefault(vectorKey, 0) + 1);
                
                maxOverlap = Math.max(maxOverlap, shiftCounts.get(vectorKey));
            }
        }
        
        return maxOverlap;
    }
}
