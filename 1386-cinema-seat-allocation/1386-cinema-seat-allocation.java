class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowReservations = new HashMap<>();
        
        // Mark reserved seats using bitwise flags
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            rowReservations.put(row, rowReservations.getOrDefault(row, 0) | (1 << (col - 1)));
        }
        
        // Start assuming every row can fit 2 families
        int ans = 2 * n;
        
        // Masks for the three 4-seat blocks (using 0-indexed column shifts)
        // seats 2,3,4,5 -> indices 1,2,3,4 -> 0b0000011110 -> 30
        int leftMask = 30; 
        // seats 6,7,8,9 -> indices 5,6,7,8 -> 0b0111100000 -> 480
        int rightMask = 480; 
        // seats 4,5,6,7 -> indices 3,4,5,6 -> 0b0001111000 -> 120
        int middleMask = 120; 
        
        for (int bitmask : rowReservations.values()) {
            boolean leftFree = (bitmask & leftMask) == 0;
            boolean rightFree = (bitmask & rightMask) == 0;
            boolean middleFree = (bitmask & middleMask) == 0;
            
            if (leftFree && rightFree) {
                // Both blocks are free, fits 2 families (no reduction)
                continue; 
            } else if (leftFree || rightFree || middleFree) {
                // At least one block is free, fits 1 family
                ans -= 1;
            } else {
                // No blocks are free, fits 0 families
                ans -= 2;
            }
        }
        
        return ans;
    }
}
